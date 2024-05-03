package com.entity.model;

import com.entity.YonghuKaoqinEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 学生考勤
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class YonghuKaoqinModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 老师
     */
    private Integer laoshiId;


    /**
     * 考勤唯一编号
     */
    private String yonghuKaoqinUuidNumber;


    /**
     * 考勤标题
     */
    private String yonghuKaoqinName;


    /**
     * 学生考勤类型
     */
    private Integer yonghuKaoqinTypes;


    /**
     * 考勤详情
     */
    private String yonghuKaoqinContent;


    /**
     * 考勤发起时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 考勤截止时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date jiezhiTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：老师
	 */
    public Integer getLaoshiId() {
        return laoshiId;
    }


    /**
	 * 设置：老师
	 */
    public void setLaoshiId(Integer laoshiId) {
        this.laoshiId = laoshiId;
    }
    /**
	 * 获取：考勤唯一编号
	 */
    public String getYonghuKaoqinUuidNumber() {
        return yonghuKaoqinUuidNumber;
    }


    /**
	 * 设置：考勤唯一编号
	 */
    public void setYonghuKaoqinUuidNumber(String yonghuKaoqinUuidNumber) {
        this.yonghuKaoqinUuidNumber = yonghuKaoqinUuidNumber;
    }
    /**
	 * 获取：考勤标题
	 */
    public String getYonghuKaoqinName() {
        return yonghuKaoqinName;
    }


    /**
	 * 设置：考勤标题
	 */
    public void setYonghuKaoqinName(String yonghuKaoqinName) {
        this.yonghuKaoqinName = yonghuKaoqinName;
    }
    /**
	 * 获取：学生考勤类型
	 */
    public Integer getYonghuKaoqinTypes() {
        return yonghuKaoqinTypes;
    }


    /**
	 * 设置：学生考勤类型
	 */
    public void setYonghuKaoqinTypes(Integer yonghuKaoqinTypes) {
        this.yonghuKaoqinTypes = yonghuKaoqinTypes;
    }
    /**
	 * 获取：考勤详情
	 */
    public String getYonghuKaoqinContent() {
        return yonghuKaoqinContent;
    }


    /**
	 * 设置：考勤详情
	 */
    public void setYonghuKaoqinContent(String yonghuKaoqinContent) {
        this.yonghuKaoqinContent = yonghuKaoqinContent;
    }
    /**
	 * 获取：考勤发起时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：考勤发起时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：考勤截止时间
	 */
    public Date getJiezhiTime() {
        return jiezhiTime;
    }


    /**
	 * 设置：考勤截止时间
	 */
    public void setJiezhiTime(Date jiezhiTime) {
        this.jiezhiTime = jiezhiTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
