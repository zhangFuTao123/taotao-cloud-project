package com.taotao.cloud.distribution.api.model.query;

import com.taotao.cloud.common.model.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * 分销员对象
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "分销订单查询对象")
public class DistributionOrderPageQuery extends PageQuery {

	@Serial
	private static final long serialVersionUID = -8736018687663645064L;

	@Schema(description = "分销员名称")
	private String distributionName;

	@Schema(description = "订单sn")
	private String orderSn;

	@Schema(description = "分销员ID", hidden = true)
	private String distributionId;

	@Schema(description = "分销订单状态")
	private String distributionOrderStatus;

	@Schema(description = "店铺ID")
	private String storeId;

	@Schema(description = "开始时间")
	private LocalDateTime startTime;

	@Schema(description = "结束时间")
	private LocalDateTime endTime;

	// public <T> QueryWrapper<T> queryWrapper() {
	// 	QueryWrapper<T> queryWrapper = Wrappers.query();
	// 	queryWrapper.like(StringUtils.isNotBlank(distributionName), "distribution_name",
	// 		distributionName);
	// 	queryWrapper.eq(StringUtils.isNotBlank(distributionOrderStatus), "distribution_order_status",
	// 		distributionOrderStatus);
	// 	queryWrapper.eq(StringUtils.isNotBlank(orderSn), "order_sn", orderSn);
	// 	queryWrapper.eq(StringUtils.isNotBlank(distributionId), "distribution_id", distributionId);
	// 	queryWrapper.eq(StringUtils.isNotBlank(storeId), "store_id", storeId);
	// 	if (endTime != null && startTime != null) {
	// 		queryWrapper.between("create_time", startTime, endTime);
	// 	}
	// 	return queryWrapper;
	// }

}