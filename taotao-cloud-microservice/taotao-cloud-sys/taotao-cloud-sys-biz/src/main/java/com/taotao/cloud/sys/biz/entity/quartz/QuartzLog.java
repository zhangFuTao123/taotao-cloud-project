/**
 * Copyright (C) 2018-2020 All rights reserved, Designed By www.yixiang.co 注意：
 * 本软件为www.yixiang.co开发研制
 */
package com.taotao.cloud.sys.biz.entity.quartz;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.cloud.web.base.entity.BaseSuperEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Quartz日志表
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2021-10-09 21:10:22
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = QuartzLog.TABLE_NAME)
@TableName(QuartzLog.TABLE_NAME)
@org.hibernate.annotations.Table(appliesTo = QuartzLog.TABLE_NAME, comment = "Quartz日志表")
public class QuartzLog extends BaseSuperEntity<QuartzLog, Long> {

	public static final String TABLE_NAME = "tt_quartz_log";


	/**
	 * Spring Bean名称
	 */
	@Column(name = "bean_name", nullable = false, columnDefinition = "varchar(64) not null comment ' Spring Bean名称'")
	private String beanName;

	/**
	 * cron 表达式
	 */
	@Column(name = "cron_expression", nullable = false, columnDefinition = "varchar(64) not null comment 'cron 表达式'")
	private String cronExpression;


	/**
	 * 异常详细
	 */
	@Column(name = "exception_detail", nullable = false, columnDefinition = "varchar(4096) not null comment 'cron 异常详细'")
	private String exceptionDetail;

	/**
	 * 状态
	 */
	@Column(name = "is_success", nullable = false, columnDefinition = "boolean DEFAULT false comment '状态'")
	private Boolean isSuccess;

	/**
	 * 任务名称
	 */
	@Column(name = "job_name", nullable = false, columnDefinition = "varchar(64) not null comment '任务名称'")
	private String jobName;

	/**
	 * 方法名称
	 */
	@Column(name = "method_name", nullable = false, columnDefinition = "varchar(64) not null comment '方法名称'")
	private String methodName;

	/**
	 * 参数
	 */
	@Column(name = "params", nullable = false, columnDefinition = "varchar(64) not null comment '参数'")
	private String params;

	/**
	 * 耗时（毫秒）
	 */
	@Column(name = "time", nullable = false, columnDefinition = "bigint not null default 1 comment '耗时（毫秒）'")
	private Long time;

}