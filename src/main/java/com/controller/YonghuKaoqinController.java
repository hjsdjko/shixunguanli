
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 学生考勤
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yonghuKaoqin")
public class YonghuKaoqinController {
    private static final Logger logger = LoggerFactory.getLogger(YonghuKaoqinController.class);

    private static final String TABLE_NAME = "yonghuKaoqin";

    @Autowired
    private YonghuKaoqinService yonghuKaoqinService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private ChengjiService chengjiService;//成绩
    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private GonggaoService gonggaoService;//公告信息
    @Autowired
    private LaoshiService laoshiService;//老师
    @Autowired
    private QingjiaService qingjiaService;//请假
    @Autowired
    private YonghuService yonghuService;//学生
    @Autowired
    private YonghuKaoqinListService yonghuKaoqinListService;//学生考勤详情
    @Autowired
    private ZuoyeService zuoyeService;//实训模块
    @Autowired
    private ZuoyeTijiaoService zuoyeTijiaoService;//实训成果
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("学生".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("老师".equals(role))
            params.put("laoshiId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = yonghuKaoqinService.queryPage(params);

        //字典表数据转换
        List<YonghuKaoqinView> list =(List<YonghuKaoqinView>)page.getList();
        for(YonghuKaoqinView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YonghuKaoqinEntity yonghuKaoqin = yonghuKaoqinService.selectById(id);
        if(yonghuKaoqin !=null){
            //entity转view
            YonghuKaoqinView view = new YonghuKaoqinView();
            BeanUtils.copyProperties( yonghuKaoqin , view );//把实体数据重构到view中
            //级联表 老师
            //级联表
            LaoshiEntity laoshi = laoshiService.selectById(yonghuKaoqin.getLaoshiId());
            if(laoshi != null){
            BeanUtils.copyProperties( laoshi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "laoshiId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setLaoshiId(laoshi.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody YonghuKaoqinEntity yonghuKaoqin, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yonghuKaoqin:{}",this.getClass().getName(),yonghuKaoqin.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("老师".equals(role))
            yonghuKaoqin.setLaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<YonghuKaoqinEntity> queryWrapper = new EntityWrapper<YonghuKaoqinEntity>()
            .eq("laoshi_id", yonghuKaoqin.getLaoshiId())
            .eq("yonghu_kaoqin_name", yonghuKaoqin.getYonghuKaoqinName())
            .eq("yonghu_kaoqin_types", yonghuKaoqin.getYonghuKaoqinTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YonghuKaoqinEntity yonghuKaoqinEntity = yonghuKaoqinService.selectOne(queryWrapper);
        if(yonghuKaoqinEntity==null){
            yonghuKaoqin.setInsertTime(new Date());
            yonghuKaoqin.setCreateTime(new Date());
            yonghuKaoqinService.insert(yonghuKaoqin);
            List<YonghuEntity> yonghuEntityList = yonghuService.selectList(new EntityWrapper<YonghuEntity>()
            );
            if(yonghuEntityList == null){
            return R.error("没有被考勤的人群,请核实后再添加");
            }
            List<YonghuKaoqinListEntity> yonghuKaoqinListList = new ArrayList<>();
            for(YonghuEntity x:yonghuEntityList){
                YonghuKaoqinListEntity yonghuKaoqinListEntity = new YonghuKaoqinListEntity();
                yonghuKaoqinListEntity.setYonghuId(x.getId());//注册表
                yonghuKaoqinListEntity.setYonghuKaoqinId(yonghuKaoqin.getId());//考勤表
                yonghuKaoqinListEntity.setYonghuKaoqinListTypes(1);
                yonghuKaoqinListEntity.setCreateTime(new Date());
                yonghuKaoqinListEntity.setInsertTime(new Date());
                yonghuKaoqinListList.add(yonghuKaoqinListEntity);

            }
            yonghuKaoqinListService.insertBatch(yonghuKaoqinListList);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YonghuKaoqinEntity yonghuKaoqin, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,yonghuKaoqin:{}",this.getClass().getName(),yonghuKaoqin.toString());
        YonghuKaoqinEntity oldYonghuKaoqinEntity = yonghuKaoqinService.selectById(yonghuKaoqin.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("老师".equals(role))
//            yonghuKaoqin.setLaoshiId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(yonghuKaoqin.getYonghuKaoqinContent()) || "null".equals(yonghuKaoqin.getYonghuKaoqinContent())){
                yonghuKaoqin.setYonghuKaoqinContent(null);
        }

            yonghuKaoqinService.updateById(yonghuKaoqin);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<YonghuKaoqinEntity> oldYonghuKaoqinList =yonghuKaoqinService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        yonghuKaoqinService.deleteBatchIds(Arrays.asList(ids));
        yonghuKaoqinListService.delete(new EntityWrapper<YonghuKaoqinListEntity>().in("yonghu_kaoqin_id",ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<YonghuKaoqinEntity> yonghuKaoqinList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            YonghuKaoqinEntity yonghuKaoqinEntity = new YonghuKaoqinEntity();
//                            yonghuKaoqinEntity.setLaoshiId(Integer.valueOf(data.get(0)));   //老师 要改的
//                            yonghuKaoqinEntity.setYonghuKaoqinUuidNumber(data.get(0));                    //考勤唯一编号 要改的
//                            yonghuKaoqinEntity.setYonghuKaoqinName(data.get(0));                    //考勤标题 要改的
//                            yonghuKaoqinEntity.setYonghuKaoqinTypes(Integer.valueOf(data.get(0)));   //学生考勤类型 要改的
//                            yonghuKaoqinEntity.setYonghuKaoqinContent("");//详情和图片
//                            yonghuKaoqinEntity.setInsertTime(date);//时间
//                            yonghuKaoqinEntity.setJiezhiTime(sdf.parse(data.get(0)));          //考勤截止时间 要改的
//                            yonghuKaoqinEntity.setCreateTime(date);//时间
                            yonghuKaoqinList.add(yonghuKaoqinEntity);


                            //把要查询是否重复的字段放入map中
                                //考勤唯一编号
                                if(seachFields.containsKey("yonghuKaoqinUuidNumber")){
                                    List<String> yonghuKaoqinUuidNumber = seachFields.get("yonghuKaoqinUuidNumber");
                                    yonghuKaoqinUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> yonghuKaoqinUuidNumber = new ArrayList<>();
                                    yonghuKaoqinUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("yonghuKaoqinUuidNumber",yonghuKaoqinUuidNumber);
                                }
                        }

                        //查询是否重复
                         //考勤唯一编号
                        List<YonghuKaoqinEntity> yonghuKaoqinEntities_yonghuKaoqinUuidNumber = yonghuKaoqinService.selectList(new EntityWrapper<YonghuKaoqinEntity>().in("yonghu_kaoqin_uuid_number", seachFields.get("yonghuKaoqinUuidNumber")));
                        if(yonghuKaoqinEntities_yonghuKaoqinUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YonghuKaoqinEntity s:yonghuKaoqinEntities_yonghuKaoqinUuidNumber){
                                repeatFields.add(s.getYonghuKaoqinUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [考勤唯一编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        yonghuKaoqinService.insertBatch(yonghuKaoqinList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = yonghuKaoqinService.queryPage(params);

        //字典表数据转换
        List<YonghuKaoqinView> list =(List<YonghuKaoqinView>)page.getList();
        for(YonghuKaoqinView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YonghuKaoqinEntity yonghuKaoqin = yonghuKaoqinService.selectById(id);
            if(yonghuKaoqin !=null){


                //entity转view
                YonghuKaoqinView view = new YonghuKaoqinView();
                BeanUtils.copyProperties( yonghuKaoqin , view );//把实体数据重构到view中

                //级联表
                    LaoshiEntity laoshi = laoshiService.selectById(yonghuKaoqin.getLaoshiId());
                if(laoshi != null){
                    BeanUtils.copyProperties( laoshi , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "laoshiId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setLaoshiId(laoshi.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody YonghuKaoqinEntity yonghuKaoqin, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,yonghuKaoqin:{}",this.getClass().getName(),yonghuKaoqin.toString());
        Wrapper<YonghuKaoqinEntity> queryWrapper = new EntityWrapper<YonghuKaoqinEntity>()
            .eq("laoshi_id", yonghuKaoqin.getLaoshiId())
            .eq("yonghu_kaoqin_uuid_number", yonghuKaoqin.getYonghuKaoqinUuidNumber())
            .eq("yonghu_kaoqin_name", yonghuKaoqin.getYonghuKaoqinName())
            .eq("yonghu_kaoqin_types", yonghuKaoqin.getYonghuKaoqinTypes())
//            .notIn("yonghu_kaoqin_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YonghuKaoqinEntity yonghuKaoqinEntity = yonghuKaoqinService.selectOne(queryWrapper);
        if(yonghuKaoqinEntity==null){
            yonghuKaoqin.setInsertTime(new Date());
            yonghuKaoqin.setCreateTime(new Date());
        yonghuKaoqinService.insert(yonghuKaoqin);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

