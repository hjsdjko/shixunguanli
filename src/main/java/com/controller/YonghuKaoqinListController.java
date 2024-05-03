
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
 * 学生考勤详情
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yonghuKaoqinList")
public class YonghuKaoqinListController {
    private static final Logger logger = LoggerFactory.getLogger(YonghuKaoqinListController.class);

    private static final String TABLE_NAME = "yonghuKaoqinList";

    @Autowired
    private YonghuKaoqinListService yonghuKaoqinListService;


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
    private YonghuKaoqinService yonghuKaoqinService;//学生考勤
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
        PageUtils page = yonghuKaoqinListService.queryPage(params);

        //字典表数据转换
        List<YonghuKaoqinListView> list =(List<YonghuKaoqinListView>)page.getList();
        for(YonghuKaoqinListView c:list){
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
        YonghuKaoqinListEntity yonghuKaoqinList = yonghuKaoqinListService.selectById(id);
        if(yonghuKaoqinList !=null){
            //entity转view
            YonghuKaoqinListView view = new YonghuKaoqinListView();
            BeanUtils.copyProperties( yonghuKaoqinList , view );//把实体数据重构到view中
            //级联表 学生
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(yonghuKaoqinList.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //级联表 学生考勤
            //级联表
            YonghuKaoqinEntity yonghuKaoqin = yonghuKaoqinService.selectById(yonghuKaoqinList.getYonghuKaoqinId());
            if(yonghuKaoqin != null){
            BeanUtils.copyProperties( yonghuKaoqin , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuKaoqinId(yonghuKaoqin.getId());
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
    public R save(@RequestBody YonghuKaoqinListEntity yonghuKaoqinList, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yonghuKaoqinList:{}",this.getClass().getName(),yonghuKaoqinList.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("学生".equals(role))
            yonghuKaoqinList.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<YonghuKaoqinListEntity> queryWrapper = new EntityWrapper<YonghuKaoqinListEntity>()
            .eq("yonghu_id", yonghuKaoqinList.getYonghuId())
            .eq("yonghu_kaoqin_id", yonghuKaoqinList.getYonghuKaoqinId())
            .eq("yonghu_kaoqin_list_types", yonghuKaoqinList.getYonghuKaoqinListTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YonghuKaoqinListEntity yonghuKaoqinListEntity = yonghuKaoqinListService.selectOne(queryWrapper);
        if(yonghuKaoqinListEntity==null){
            yonghuKaoqinList.setInsertTime(new Date());
            yonghuKaoqinList.setCreateTime(new Date());
            yonghuKaoqinListService.insert(yonghuKaoqinList);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YonghuKaoqinListEntity yonghuKaoqinList, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,yonghuKaoqinList:{}",this.getClass().getName(),yonghuKaoqinList.toString());
        YonghuKaoqinListEntity oldYonghuKaoqinListEntity = yonghuKaoqinListService.selectById(yonghuKaoqinList.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("学生".equals(role))
//            yonghuKaoqinList.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        yonghuKaoqinList.setUpdateTime(new Date());

            yonghuKaoqinListService.updateById(yonghuKaoqinList);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<YonghuKaoqinListEntity> oldYonghuKaoqinListList =yonghuKaoqinListService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        yonghuKaoqinListService.deleteBatchIds(Arrays.asList(ids));

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
            List<YonghuKaoqinListEntity> yonghuKaoqinListList = new ArrayList<>();//上传的东西
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
                            YonghuKaoqinListEntity yonghuKaoqinListEntity = new YonghuKaoqinListEntity();
//                            yonghuKaoqinListEntity.setYonghuId(Integer.valueOf(data.get(0)));   //学生 要改的
//                            yonghuKaoqinListEntity.setYonghuKaoqinId(Integer.valueOf(data.get(0)));   //考勤 要改的
//                            yonghuKaoqinListEntity.setYonghuKaoqinListTypes(Integer.valueOf(data.get(0)));   //打卡状态 要改的
//                            yonghuKaoqinListEntity.setInsertTime(date);//时间
//                            yonghuKaoqinListEntity.setUpdateTime(sdf.parse(data.get(0)));          //打卡时间 要改的
//                            yonghuKaoqinListEntity.setCreateTime(date);//时间
                            yonghuKaoqinListList.add(yonghuKaoqinListEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        yonghuKaoqinListService.insertBatch(yonghuKaoqinListList);
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
        PageUtils page = yonghuKaoqinListService.queryPage(params);

        //字典表数据转换
        List<YonghuKaoqinListView> list =(List<YonghuKaoqinListView>)page.getList();
        for(YonghuKaoqinListView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        YonghuKaoqinListEntity yonghuKaoqinList = yonghuKaoqinListService.selectById(id);
            if(yonghuKaoqinList !=null){


                //entity转view
                YonghuKaoqinListView view = new YonghuKaoqinListView();
                BeanUtils.copyProperties( yonghuKaoqinList , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(yonghuKaoqinList.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                    YonghuKaoqinEntity yonghuKaoqin = yonghuKaoqinService.selectById(yonghuKaoqinList.getYonghuKaoqinId());
                if(yonghuKaoqin != null){
                    BeanUtils.copyProperties( yonghuKaoqin , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuKaoqinId(yonghuKaoqin.getId());
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
    public R add(@RequestBody YonghuKaoqinListEntity yonghuKaoqinList, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,yonghuKaoqinList:{}",this.getClass().getName(),yonghuKaoqinList.toString());
        Wrapper<YonghuKaoqinListEntity> queryWrapper = new EntityWrapper<YonghuKaoqinListEntity>()
            .eq("yonghu_id", yonghuKaoqinList.getYonghuId())
            .eq("yonghu_kaoqin_id", yonghuKaoqinList.getYonghuKaoqinId())
            .eq("yonghu_kaoqin_list_types", yonghuKaoqinList.getYonghuKaoqinListTypes())
//            .notIn("yonghu_kaoqin_list_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YonghuKaoqinListEntity yonghuKaoqinListEntity = yonghuKaoqinListService.selectOne(queryWrapper);
        if(yonghuKaoqinListEntity==null){
            yonghuKaoqinList.setInsertTime(new Date());
            yonghuKaoqinList.setCreateTime(new Date());
        yonghuKaoqinListService.insert(yonghuKaoqinList);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

