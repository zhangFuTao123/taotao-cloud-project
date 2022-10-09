package com.taotao.cloud.goods.api.feign;

import com.taotao.cloud.common.constant.ServiceName;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.goods.api.feign.fallback.FeignCategoryApiFallback;
import com.taotao.cloud.goods.api.model.vo.CategoryTreeVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 远程调用订单模块
 *
 * @author shuigedeng
 * @since 2020/5/2 16:42
 */
@FeignClient(value = ServiceName.TAOTAO_CLOUD_GOODS, fallbackFactory = FeignCategoryApiFallback.class)
public interface IFeignCategoryApi {

	@GetMapping(value = "/category/first/id/{id:[0-9]*}")
	Result<List<CategoryTreeVO>> firstCategory();

}
