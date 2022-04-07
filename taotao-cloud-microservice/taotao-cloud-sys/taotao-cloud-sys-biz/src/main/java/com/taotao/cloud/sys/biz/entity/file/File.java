package com.taotao.cloud.sys.biz.entity.file;


import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.cloud.web.base.entity.BaseSuperEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 文件表
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2020/11/12 15:33
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = File.TABLE_NAME)
@TableName(File.TABLE_NAME)
@org.hibernate.annotations.Table(appliesTo = File.TABLE_NAME, comment = "文件表")
public class File extends BaseSuperEntity<File, Long> {

	public static final String TABLE_NAME = "tt_file";

	/**
	 * 业务ID
	 */
	@Column(name = "biz_id", columnDefinition = "bigint comment '业务ID'")
	private Long bizId;

	/**
	 * 业务类型
	 *
	 * @see BizType
	 */
	@Column(name = "biz_type", nullable = false, columnDefinition = "varchar(32) not null comment '业务类型'")
	private String bizType;

	/**
	 * 数据类型
	 *
	 * @see DataType {IMAGE:图片;VIDEO:视频;AUDIO:音频;DOC:文档;OTHER:其他}
	 */
	@Column(name = "data_type", nullable = false, columnDefinition = "varchar(32) not null comment '数据类型'")
	private String dataType;

	/**
	 * 原始文件名
	 */
	@Column(name = "original_file_name", nullable = false, columnDefinition = "varchar(255) not null comment '原始文件名'")
	private String originalFileName;

	/**
	 * 文件访问链接
	 */
	@Column(name = "url", nullable = false, columnDefinition = "varchar(255) not null comment '文件访问链接'")
	private String url;

	/**
	 * 文件md5值
	 */
	@Column(name = "file_md5", nullable = false, columnDefinition = "varchar(255) not null comment '文件md5值'")
	private String fileMd5;

	/**
	 * 文件上传类型 取上传文件的值
	 */
	@Column(name = "context_type", nullable = false, columnDefinition = "varchar(255) not null comment '文件上传类型'")
	private String contextType;

	/**
	 * 唯一文件名
	 */
	@Column(name = "filename", nullable = false, columnDefinition = "varchar(255) not null comment '唯一文件名'")
	private String filename;

	/**
	 * 后缀(没有.)
	 */
	@Column(name = "ext", nullable = false, columnDefinition = "varchar(64) not null comment '后缀'")
	private String ext;

	/**
	 * 大小
	 */
	@Column(name = "size", nullable = false, columnDefinition = "bigint not null comment '大小'")
	private Long size;

}
