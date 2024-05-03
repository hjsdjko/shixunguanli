package com.entity.vo;

import com.entity.YonghuKaoqinEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 学生考勤
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("yonghu_kaoqin")
public class YonghuKaoqinVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 老师
     */

    @TableField(value = "laoshi_id")
    private Integer laoshiId;


    /**
     * 考勤唯一编号
     */

    @TableField(value = "yonghu_kaoqin_uuid_number")
    private String yonghuKaoqinUuidNumber;


    /**
     * 考勤标题
     */

    @TableField(value = "yonghu_kaoqin_name")
    private String yonghuKaoqinName;


    /**
     * 学生考勤类型
     */

    @TableField(value = "yonghu_kaoqin_types")
    private Integer yonghuKaoqinTypes;


    /**
     * 考勤详情
     */

    @TableField(value = "yonghu_kaoqin_content")
    private String yonghuKaoqinContent;


    /**
     * 考勤发起时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 考勤截止时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "jiezhi_time")
    private Date jiezhiTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：老师
	 */
    public Integer getLaoshiId() {
        return laoshiId;
    }


    /**
	 * 获取：老师
	 */

    public void setLaoshiId(Integer laoshiId) {
        this.laoshiId = laoshiId;
    }
    /**
	 * 设置：考勤唯一编号
	 */
    public String getYonghuKaoqinUuidNumber() {
        return yonghuKaoqinUuidNumber;
    }


    /**
	 * 获取：考勤唯一编号
	 */

    public void setYonghuKaoqinUuidNumber(String yonghuKaoqinUuidNumber) {
        this.yonghuKaoqinUuidNumber = yonghuKaoqinUuidNumber;
    }
    /**
	 * 设置：考勤标题
	 */
    public String getYonghuKaoqinName() {
        return yonghuKaoqinName;
    }


    /**
	 * 获取：考勤标题
	 */

    public void setYonghuKaoqinName(String yonghuKaoqinName) {
        this.yonghuKaoqinName = yonghuKaoqinName;
    }
    /**
	 * 设置：学生考勤类型
	 */
    public Integer getYonghuKaoqinTypes() {
        return yonghuKaoqinTypes;
    }


    /**
	 * 获取：学生考勤类型
	 */

    public void setYonghuKaoqinTypes(Integer yonghuKaoqinTypes) {
        this.yonghuKaoqinTypes = yonghuKaoqinTypes;
    }
    /**
	 * 设置：考勤详情
	 */
    public String getYonghuKaoqinContent() {
        return yonghuKaoqinContent;
    }


    /**
	 * 获取：考勤详情
	 */

    public void setYonghuKaoqinContent(String yonghuKaoqinContent) {
        this.yonghuKaoqinContent = yonghuKaoqinContent;
    }
    /**
	 * 设置：考勤发起时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：考勤发起时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：考勤截止时间
	 */
    public Date getJiezhiTime() {
        return jiezhiTime;
    }


    /**
	 * 获取：考勤截止时间
	 */

    public void setJiezhiTime(Date jiezhiTime) {
        this.jiezhiTime = jiezhiTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
