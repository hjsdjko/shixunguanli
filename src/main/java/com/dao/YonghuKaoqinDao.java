package com.dao;

import com.entity.YonghuKaoqinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YonghuKaoqinView;

/**
 * 学生考勤 Dao 接口
 *
 * @author 
 */
public interface YonghuKaoqinDao extends BaseMapper<YonghuKaoqinEntity> {

   List<YonghuKaoqinView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
