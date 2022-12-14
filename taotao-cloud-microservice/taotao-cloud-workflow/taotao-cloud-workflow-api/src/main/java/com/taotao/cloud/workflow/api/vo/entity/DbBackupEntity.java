package com.taotao.cloud.workflow.api.vo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

@Data
@TableName("base_dbbackup")
public class DbBackupEntity {

	/**
	 * 备份主键
	 */
	@TableId("F_ID")
	private String id;

	/**
	 * 备份库名
	 */
	@TableField("F_BACKUPDBNAME")
	private String backupDbName;

	/**
	 * 备份时间
	 */
	@TableField("F_BACKUPTIME")
	private Date backupTime;

	/**
	 * 文件名称
	 */
	@TableField("F_FILENAME")
	private String fileName;

	/**
	 * 文件大小
	 */
	@TableField("F_FILESIZE")
	private String fileSize;

	/**
	 * 文件路径
	 */
	@TableField("F_FILEPATH")
	private String filePath;

	/**
	 * 描述
	 */
	@TableField("F_DESCRIPTION")
	private String description;

	/**
	 * 排序码
	 */
	@TableField("F_SORTCODE")
	private Long sortCode;

	/**
	 * 有效标志
	 */
	@TableField("F_ENABLEDMARK")
	private Integer enabledMark;

	/**
	 * 创建时间
	 */
	@TableField(value = "F_CREATORTIME", fill = FieldFill.INSERT)
	private Date creatorTime;

	/**
	 * 创建用户
	 */
	@TableField(value = "F_CREATORUSERID", fill = FieldFill.INSERT)
	private String creatorUserId;

	/**
	 * 修改时间
	 */
	@TableField(value = "F_LASTMODIFYTIME", fill = FieldFill.UPDATE)
	private Date lastModifyTime;

	/**
	 * 修改用户
	 */
	@TableField(value = "F_LASTMODIFYUSERID", fill = FieldFill.UPDATE)
	private String lastModifyUserId;

	/**
	 * 删除标志
	 */
	@TableField("F_DELETEMARK")
	private Integer deleteMark;

	/**
	 * 删除时间
	 */
	@TableField("F_DELETETIME")
	private Date deleteTime;

	/**
	 * 删除用户
	 */
	@TableField("F_DELETEUSERID")
	private String deleteUserId;
}