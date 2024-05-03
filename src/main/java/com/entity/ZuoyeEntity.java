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
 * 实训模块
 *
 * @author 
 * @email
 */
@TableName("zuoye")
public class ZuoyeEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ZuoyeEntity() {

	}

	public ZuoyeEntity(T t) {
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
     * 实训模块名称
     */
    @ColumnInfo(comment="实训模块名称",type="varchar(200)")
    @TableField(value = "zuoye_name")

    private String zuoyeName;


    /**
     * 实训模块编号
     */
    @ColumnInfo(comment="实训模块编号",type="varchar(200)")
    @TableField(value = "zuoye_uuid_number")

    private String zuoyeUuidNumber;


    /**
     * 实训模块地点
     */
    @ColumnInfo(comment="实训模块地点",type="varchar(200)")
    @TableField(value = "zuoye_address")

    private String zuoyeAddress;


    /**
     * 实训模块照片
     */
    @ColumnInfo(comment="实训模块照片",type="varchar(200)")
    @TableField(value = "zuoye_photo")

    private String zuoyePhoto;


    /**
     * 实训模块类型
     */
    @ColumnInfo(comment="实训模块类型",type="int(11)")
    @TableField(value = "zuoye_types")

    private Integer zuoyeTypes;


    /**
     * 视频
     */
    @ColumnInfo(comment="视频",type="varchar(200)")
    @TableField(value = "zuoye_video")

    private String zuoyeVideo;


    /**
     * 文件
     */
    @ColumnInfo(comment="文件",type="varchar(200)")
    @TableField(value = "zuoye_file")

    private String zuoyeFile;


    /**
     * 实训模块介绍
     */
    @ColumnInfo(comment="实训模块介绍",type="longtext")
    @TableField(value = "zuoye_content")

    private String zuoyeContent;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "zuoye_delete")

    private Integer zuoyeDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


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
	 * 获取：实训模块名称
	 */
    public String getZuoyeName() {
        return zuoyeName;
    }
    /**
	 * 设置：实训模块名称
	 */

    public void setZuoyeName(String zuoyeName) {
        this.zuoyeName = zuoyeName;
    }
    /**
	 * 获取：实训模块编号
	 */
    public String getZuoyeUuidNumber() {
        return zuoyeUuidNumber;
    }
    /**
	 * 设置：实训模块编号
	 */

    public void setZuoyeUuidNumber(String zuoyeUuidNumber) {
        this.zuoyeUuidNumber = zuoyeUuidNumber;
    }
    /**
	 * 获取：实训模块地点
	 */
    public String getZuoyeAddress() {
        return zuoyeAddress;
    }
    /**
	 * 设置：实训模块地点
	 */

    public void setZuoyeAddress(String zuoyeAddress) {
        this.zuoyeAddress = zuoyeAddress;
    }
    /**
	 * 获取：实训模块照片
	 */
    public String getZuoyePhoto() {
        return zuoyePhoto;
    }
    /**
	 * 设置：实训模块照片
	 */

    public void setZuoyePhoto(String zuoyePhoto) {
        this.zuoyePhoto = zuoyePhoto;
    }
    /**
	 * 获取：实训模块类型
	 */
    public Integer getZuoyeTypes() {
        return zuoyeTypes;
    }
    /**
	 * 设置：实训模块类型
	 */

    public void setZuoyeTypes(Integer zuoyeTypes) {
        this.zuoyeTypes = zuoyeTypes;
    }
    /**
	 * 获取：视频
	 */
    public String getZuoyeVideo() {
        return zuoyeVideo;
    }
    /**
	 * 设置：视频
	 */

    public void setZuoyeVideo(String zuoyeVideo) {
        this.zuoyeVideo = zuoyeVideo;
    }
    /**
	 * 获取：文件
	 */
    public String getZuoyeFile() {
        return zuoyeFile;
    }
    /**
	 * 设置：文件
	 */

    public void setZuoyeFile(String zuoyeFile) {
        this.zuoyeFile = zuoyeFile;
    }
    /**
	 * 获取：实训模块介绍
	 */
    public String getZuoyeContent() {
        return zuoyeContent;
    }
    /**
	 * 设置：实训模块介绍
	 */

    public void setZuoyeContent(String zuoyeContent) {
        this.zuoyeContent = zuoyeContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getZuoyeDelete() {
        return zuoyeDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setZuoyeDelete(Integer zuoyeDelete) {
        this.zuoyeDelete = zuoyeDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
        return "Zuoye{" +
            ", id=" + id +
            ", laoshiId=" + laoshiId +
            ", zuoyeName=" + zuoyeName +
            ", zuoyeUuidNumber=" + zuoyeUuidNumber +
            ", zuoyeAddress=" + zuoyeAddress +
            ", zuoyePhoto=" + zuoyePhoto +
            ", zuoyeTypes=" + zuoyeTypes +
            ", zuoyeVideo=" + zuoyeVideo +
            ", zuoyeFile=" + zuoyeFile +
            ", zuoyeContent=" + zuoyeContent +
            ", zuoyeDelete=" + zuoyeDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
