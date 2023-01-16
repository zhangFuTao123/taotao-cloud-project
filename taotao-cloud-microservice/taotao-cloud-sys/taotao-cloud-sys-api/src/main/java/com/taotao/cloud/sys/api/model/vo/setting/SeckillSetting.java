package com.taotao.cloud.sys.api.model.vo.setting;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;

/**
 * 秒杀活动设置
 */
@Data
public class SeckillSetting implements Serializable {

	/**
	 * 开启几点场 例如：6,8,12
	 */
	@NotNull(message = "活动时间段不能为空")
	private String hours;

	/**
	 * 秒杀规则
	 */
	@NotNull(message = "秒杀规则不能为空")
	private String seckillRule;
}
