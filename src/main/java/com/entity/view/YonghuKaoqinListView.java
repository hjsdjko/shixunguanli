package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.YonghuKaoqinListEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 学生考勤详情
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("yonghu_kaoqin_list")
public class YonghuKaoqinListView extends YonghuKaoqinListEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 打卡状态的值
	*/
	@ColumnInfo(comment="打卡状态的字典表值",type="varchar(200)")
	private String yonghuKaoqinListValue;

	//级联表 学生
		/**
		* 学生编号
		*/

		@ColumnInfo(comment="学生编号",type="varchar(200)")
		private String yonghuUuidNumber;
		/**
		* 学生姓名
		*/

		@ColumnInfo(comment="学生姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 学生手机号
		*/

		@ColumnInfo(comment="学生手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 学生身份证号
		*/

		@ColumnInfo(comment="学生身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 学生头像
		*/

		@ColumnInfo(comment="学生头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 学生邮箱
		*/

		@ColumnInfo(comment="学生邮箱",type="varchar(200)")
		private String yonghuEmail;
	//级联表 学生考勤
					 
		/**
		* 学生考勤 的 老师
		*/
		@ColumnInfo(comment="老师",type="int(11)")
		private Integer yonghuKaoqinLaoshiId;
		/**
		* 考勤唯一编号
		*/

		@ColumnInfo(comment="考勤唯一编号",type="varchar(200)")
		private String yonghuKaoqinUuidNumber;
		/**
		* 考勤标题
		*/

		@ColumnInfo(comment="考勤标题",type="varchar(200)")
		private String yonghuKaoqinName;
		/**
		* 学生考勤类型
		*/
		@ColumnInfo(comment="学生考勤类型",type="int(11)")
		private Integer yonghuKaoqinTypes;
			/**
			* 学生考勤类型的值
			*/
			@ColumnInfo(comment="学生考勤类型的字典表值",type="varchar(200)")
			private String yonghuKaoqinValue;
		/**
		* 考勤详情
		*/

		@ColumnInfo(comment="考勤详情",type="longtext")
		private String yonghuKaoqinContent;
		/**
		* 考勤截止时间
		*/
		@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
		@DateTimeFormat
		@ColumnInfo(comment="考勤截止时间",type="timestamp")
		private Date jiezhiTime;



	public YonghuKaoqinListView() {

	}

	public YonghuKaoqinListView(YonghuKaoqinListEntity yonghuKaoqinListEntity) {
		try {
			BeanUtils.copyProperties(this, yonghuKaoqinListEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 打卡状态的值
	*/
	public String getYonghuKaoqinListValue() {
		return yonghuKaoqinListValue;
	}
	/**
	* 设置： 打卡状态的值
	*/
	public void setYonghuKaoqinListValue(String yonghuKaoqinListValue) {
		this.yonghuKaoqinListValue = yonghuKaoqinListValue;
	}


	//级联表的get和set 学生

		/**
		* 获取： 学生编号
		*/
		public String getYonghuUuidNumber() {
			return yonghuUuidNumber;
		}
		/**
		* 设置： 学生编号
		*/
		public void setYonghuUuidNumber(String yonghuUuidNumber) {
			this.yonghuUuidNumber = yonghuUuidNumber;
		}

		/**
		* 获取： 学生姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 学生姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 学生手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 学生手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 学生身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 学生身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 学生头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 学生头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 学生邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 学生邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}
	//级联表的get和set 学生考勤
		/**
		* 获取：学生考勤 的 老师
		*/
		public Integer getYonghuKaoqinLaoshiId() {
			return yonghuKaoqinLaoshiId;
		}
		/**
		* 设置：学生考勤 的 老师
		*/
		public void setYonghuKaoqinLaoshiId(Integer yonghuKaoqinLaoshiId) {
			this.yonghuKaoqinLaoshiId = yonghuKaoqinLaoshiId;
		}

		/**
		* 获取： 考勤唯一编号
		*/
		public String getYonghuKaoqinUuidNumber() {
			return yonghuKaoqinUuidNumber;
		}
		/**
		* 设置： 考勤唯一编号
		*/
		public void setYonghuKaoqinUuidNumber(String yonghuKaoqinUuidNumber) {
			this.yonghuKaoqinUuidNumber = yonghuKaoqinUuidNumber;
		}

		/**
		* 获取： 考勤标题
		*/
		public String getYonghuKaoqinName() {
			return yonghuKaoqinName;
		}
		/**
		* 设置： 考勤标题
		*/
		public void setYonghuKaoqinName(String yonghuKaoqinName) {
			this.yonghuKaoqinName = yonghuKaoqinName;
		}
		/**
		* 获取： 学生考勤类型
		*/
		public Integer getYonghuKaoqinTypes() {
			return yonghuKaoqinTypes;
		}
		/**
		* 设置： 学生考勤类型
		*/
		public void setYonghuKaoqinTypes(Integer yonghuKaoqinTypes) {
			this.yonghuKaoqinTypes = yonghuKaoqinTypes;
		}


			/**
			* 获取： 学生考勤类型的值
			*/
			public String getYonghuKaoqinValue() {
				return yonghuKaoqinValue;
			}
			/**
			* 设置： 学生考勤类型的值
			*/
			public void setYonghuKaoqinValue(String yonghuKaoqinValue) {
				this.yonghuKaoqinValue = yonghuKaoqinValue;
			}

		/**
		* 获取： 考勤详情
		*/
		public String getYonghuKaoqinContent() {
			return yonghuKaoqinContent;
		}
		/**
		* 设置： 考勤详情
		*/
		public void setYonghuKaoqinContent(String yonghuKaoqinContent) {
			this.yonghuKaoqinContent = yonghuKaoqinContent;
		}

		/**
		* 获取： 考勤截止时间
		*/
		public Date getJiezhiTime() {
			return jiezhiTime;
		}
		/**
		* 设置： 考勤截止时间
		*/
		public void setJiezhiTime(Date jiezhiTime) {
			this.jiezhiTime = jiezhiTime;
		}


	@Override
	public String toString() {
		return "YonghuKaoqinListView{" +
			", yonghuKaoqinListValue=" + yonghuKaoqinListValue +
			", yonghuUuidNumber=" + yonghuUuidNumber +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			", yonghuKaoqinUuidNumber=" + yonghuKaoqinUuidNumber +
			", yonghuKaoqinName=" + yonghuKaoqinName +
			", yonghuKaoqinContent=" + yonghuKaoqinContent +
			", jiezhiTime=" + DateUtil.convertString(jiezhiTime,"yyyy-MM-dd") +
			"} " + super.toString();
	}
}
