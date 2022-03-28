/**
 * Copyright (C) 2018-2020 All rights reserved, Designed By www.yixiang.co 注意：
 * 本软件为www.yixiang.co开发研制
 */
package com.taotao.cloud.sys.biz.entity.config;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.cloud.web.base.entity.BaseSuperEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 生成器配置表
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2022-02-15 09:25:26
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = GenConfig.TABLE_NAME)
@TableName(GenConfig.TABLE_NAME)
@org.hibernate.annotations.Table(appliesTo = GenConfig.TABLE_NAME, comment = "生成器配置表")
public class GenConfig extends BaseSuperEntity<GenConfig, Long> {

	public static final String TABLE_NAME = "tt_gen_config";

	/**
	 * 表明
	 **/
	@Column(name = "table_name", nullable = false, columnDefinition = "varchar(64) not null comment '表名称'")
	private String tableName;

	/**
	 * 接口名称
	 **/
	@Column(name = "api_alias", nullable = false, columnDefinition = "varchar(64) not null comment '接口名称'")
	private String apiAlias;

	/**
	 * 包路径
	 */
	@Column(name = "pack", nullable = false, columnDefinition = "varchar(64) not null comment '包路径'")
	private String pack;

	/**
	 * 模块名
	 */
	@Column(name = "module_name", nullable = false, columnDefinition = "varchar(64) not null comment '模块名'")
	private String moduleName;

	/**
	 * 前端文件路径
	 */
	@Column(name = "path", nullable = false, columnDefinition = "varchar(64) not null comment '前端文件路径'")
	private String path;

	/**
	 * 前端文件路径
	 */
	@Column(name = "api_path", nullable = false, columnDefinition = "varchar(64) not null comment '前端文件路径'")
	private String apiPath;

	/**
	 * 作者
	 */
	@Column(name = "author", nullable = false, columnDefinition = "varchar(64) not null comment '作者'")
	private String author;

	/**
	 * 表前缀
	 */
	@Column(name = "prefix", nullable = false, columnDefinition = "varchar(64) not null comment '表前缀'")
	private String prefix;

	/**
	 * 是否覆盖
	 */
	@Column(name = "cover", nullable = false, columnDefinition = "boolean default false comment '是否覆盖'")
	private Boolean cover;

}