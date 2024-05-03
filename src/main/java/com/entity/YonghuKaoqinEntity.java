package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 学生考勤
 *
 * @author 
 * @email
 */
@TableName("yonghu_kaoqin")
public class YonghuKaoqinEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public YonghuKaoqinEntity() {

	}

	public YonghuKaoqinEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 老师
     */
    @ColumnInfo(comment="老师",type="int(11)")
    @TableField(value = "laoshi_id")

    private Integer laoshiId;


    /**
     * 考勤唯一编号
     */
    @ColumnInfo(comment="考勤唯一编号",type="varchar(200)")
    @TableField(value = "yonghu_kaoqin_uuid_number")

    private String yonghuKaoqinUuidNumber;


    /**
     * 考勤标题
     */
    @ColumnInfo(comment="考勤标题",type="varchar(200)")
    @TableField(value = "yonghu_kaoqin_name")

    private String yonghuKaoqinName;


    /**
     * 学生考勤类型
     */
    @ColumnInfo(comment="学生考勤类型",type="int(11)")
    @TableField(value = "yonghu_kaoqin_types")

    private Integer yonghuKaoqinTypes;


    /**
     * 考勤详情
     */
    @ColumnInfo(comment="考勤详情",type="longtext")
    @TableField(value = "yonghu_kaoqin_content")

    private String yonghuKaoqinContent;


    /**
     * 考勤发起时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="考勤发起时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 考勤截止时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="考勤截止时间",type="timestamp")
    @TableField(value = "jiezhi_time")

    private Date jiezhiTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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

    @Override
    public String toString() {
        return "YonghuKaoqin{" +
            ", id=" + id +
            ", laoshiId=" + laoshiId +
            ", yonghuKaoqinUuidNumber=" + yonghuKaoqinUuidNumber +
            ", yonghuKaoqinName=" + yonghuKaoqinName +
            ", yonghuKaoqinTypes=" + yonghuKaoqinTypes +
            ", yonghuKaoqinContent=" + yonghuKaoqinContent +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", jiezhiTime=" + DateUtil.convertString(jiezhiTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
