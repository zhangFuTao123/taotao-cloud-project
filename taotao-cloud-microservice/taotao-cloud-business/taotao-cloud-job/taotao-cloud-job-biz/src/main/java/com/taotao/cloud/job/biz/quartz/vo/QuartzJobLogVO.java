package com.taotao.cloud.job.biz.quartz.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 任务执行日志
 */
@Data
@Schema(title = "定时任务执行日志")
public class QuartzJobLogVO {

	@Schema(description = "处理器名称")
	private String handlerName;

	@Schema(description = "处理器全限定名")
	private String className;

	@Schema(description = "是否执行成功")
	private Boolean success;

	@Schema(description = "错误信息")
	private String errorMessage;

	@Schema(description = "开始时间")
	private LocalDateTime startTime;

	@Schema(description = "结束时间")
	private LocalDateTime endTime;

	@Schema(description = "执行时长")
	private Long duration;

	@Schema(description = "创建时间")
	private LocalDateTime createTime;


}