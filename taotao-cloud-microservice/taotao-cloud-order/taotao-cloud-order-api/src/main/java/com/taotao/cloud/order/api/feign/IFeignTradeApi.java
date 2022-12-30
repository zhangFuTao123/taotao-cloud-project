package com.taotao.cloud.order.api.feign;

import com.taotao.cloud.common.constant.ServiceName;
import com.taotao.cloud.order.api.feign.fallback.FeignTradeApiFallback;
import com.taotao.cloud.order.api.model.vo.trade.TradeVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 远程调用订单模块
 *
 * @author shuigedeng
 * @since 2020/5/2 16:42
 */
@FeignClient(value = ServiceName.TAOTAO_CLOUD_ORDER, fallbackFactory = FeignTradeApiFallback.class)
public interface IFeignTradeApi {

	@GetMapping(value = "/trade")
	TradeVO getBySn(String sn);

	@PostMapping(value = "/payTrade")
	boolean payTrade(String sn, String paymentMethod, String receivableNo);

}

