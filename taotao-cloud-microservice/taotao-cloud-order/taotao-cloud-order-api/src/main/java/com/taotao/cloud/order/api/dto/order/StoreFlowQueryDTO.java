package com.taotao.cloud.order.api.dto.order;

import cn.lili.common.vo.PageVO;
import cn.lili.modules.store.entity.dos.Bill;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 店铺流水查询DTO
 *
 * @version v1.0 2021-12-08 10:53
 */
@Schema(description = "店铺流水查询DTO")
public class StoreFlowQueryDTO {

	@Schema(description = "类型")
	private String type;

	@Schema(description = "售后编号")
	private String refundSn;

	@Schema(description = "售后编号")
	private String orderSn;

	@Schema(description = "过滤只看分销订单")
	private Boolean justDistribution;

	@ApiModelProperty("结算单")
	private Bill bill;

	@Schema(description = "分页")
	private PageVO pageVO;

}