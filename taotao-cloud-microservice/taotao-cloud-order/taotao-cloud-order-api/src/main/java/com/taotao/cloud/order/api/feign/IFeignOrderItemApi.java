package com.taotao.cloud.order.api.feign;

import com.taotao.cloud.common.constant.ServiceName;
import com.taotao.cloud.order.api.enums.order.CommentStatusEnum;
import com.taotao.cloud.order.api.feign.fallback.FeignOrderItemApiFallback;
import com.taotao.cloud.order.api.model.dto.order_item.OrderItemSaveDTO;
import com.taotao.cloud.order.api.model.vo.order.OrderItemVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 远程调用订单项模块
 *
 * @author shuigedeng
 * @since 2020/5/2 16:42
 */
@FeignClient(value = ServiceName.TAOTAO_CLOUD_ORDER, fallbackFactory = FeignOrderItemApiFallback.class)
public interface IFeignOrderItemApi {

	@PostMapping(value = "/order/item")
	Boolean saveOrderItem(@RequestBody OrderItemSaveDTO orderItemSaveDTO);

	@PutMapping(value = "/order/item")
	Boolean updateById(@RequestBody OrderItemVO orderItem);

	@GetMapping(value = "/order/item")
	OrderItemVO getByOrderSnAndSkuId(String orderSn, String skuId);

	@GetMapping(value = "/order/item")
	OrderItemVO getBySn(String orderItemSn);

	@PutMapping(value = "/order/item")
	Boolean updateCommentStatus(String sn, CommentStatusEnum finished);
}
