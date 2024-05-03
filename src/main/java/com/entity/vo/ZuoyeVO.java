package com.entity.vo;

import com.entity.ZuoyeEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 实训模块
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("zuoye")
public class ZuoyeVO implements Serializable {
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
     * 实训模块名称
     */

    @TableField(value = "zuoye_name")
    private String zuoyeName;


    /**
     * 实训模块编号
     */

    @TableField(value = "zuoye_uuid_number")
    private String zuoyeUuidNumber;


    /**
     * 实训模块地点
     */

    @TableField(value = "zuoye_address")
    private String zuoyeAddress;


    /**
     * 实训模块照片
     */

    @TableField(value = "zuoye_photo")
    private String zuoyePhoto;


    /**
     * 实训模块类型
     */

    @TableField(value = "zuoye_types")
    private Integer zuoyeTypes;


    /**
     * 视频
     */

    @TableField(value = "zuoye_video")
    private String zuoyeVideo;


    /**
     * 文件
     */

    @TableField(value = "zuoye_file")
    private String zuoyeFile;


    /**
     * 实训模块介绍
     */

    @TableField(value = "zuoye_content")
    private String zuoyeContent;


    /**
     * 逻辑删除
     */

    @TableField(value = "zuoye_delete")
    private Integer zuoyeDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 设置：实训模块名称
	 */
    public String getZuoyeName() {
        return zuoyeName;
    }


    /**
	 * 获取：实训模块名称
	 */

    public void setZuoyeName(String zuoyeName) {
        this.zuoyeName = zuoyeName;
    }
    /**
	 * 设置：实训模块编号
	 */
    public String getZuoyeUuidNumber() {
        return zuoyeUuidNumber;
    }


    /**
	 * 获取：实训模块编号
	 */

    public void setZuoyeUuidNumber(String zuoyeUuidNumber) {
        this.zuoyeUuidNumber = zuoyeUuidNumber;
    }
    /**
	 * 设置：实训模块地点
	 */
    public String getZuoyeAddress() {
        return zuoyeAddress;
    }


    /**
	 * 获取：实训模块地点
	 */

    public void setZuoyeAddress(String zuoyeAddress) {
        this.zuoyeAddress = zuoyeAddress;
    }
    /**
	 * 设置：实训模块照片
	 */
    public String getZuoyePhoto() {
        return zuoyePhoto;
    }


    /**
	 * 获取：实训模块照片
	 */

    public void setZuoyePhoto(String zuoyePhoto) {
        this.zuoyePhoto = zuoyePhoto;
    }
    /**
	 * 设置：实训模块类型
	 */
    public Integer getZuoyeTypes() {
        return zuoyeTypes;
    }


    /**
	 * 获取：实训模块类型
	 */

    public void setZuoyeTypes(Integer zuoyeTypes) {
        this.zuoyeTypes = zuoyeTypes;
    }
    /**
	 * 设置：视频
	 */
    public String getZuoyeVideo() {
        return zuoyeVideo;
    }


    /**
	 * 获取：视频
	 */

    public void setZuoyeVideo(String zuoyeVideo) {
        this.zuoyeVideo = zuoyeVideo;
    }
    /**
	 * 设置：文件
	 */
    public String getZuoyeFile() {
        return zuoyeFile;
    }


    /**
	 * 获取：文件
	 */

    public void setZuoyeFile(String zuoyeFile) {
        this.zuoyeFile = zuoyeFile;
    }
    /**
	 * 设置：实训模块介绍
	 */
    public String getZuoyeContent() {
        return zuoyeContent;
    }


    /**
	 * 获取：实训模块介绍
	 */

    public void setZuoyeContent(String zuoyeContent) {
        this.zuoyeContent = zuoyeContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getZuoyeDelete() {
        return zuoyeDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setZuoyeDelete(Integer zuoyeDelete) {
        this.zuoyeDelete = zuoyeDelete;
    }
    /**
	 * 设置：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
