/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.taotao.cloud.order.biz.controller;

import com.taotao.cloud.common.model.BaseQuery;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.log.annotation.RequestOperateLog;
import com.taotao.cloud.order.api.dto.order_info.OrderSaveDTO;
import com.taotao.cloud.order.api.dto.order_info.OrderUpdateDTO;
import com.taotao.cloud.order.api.service.IOrderInfoService;
import com.taotao.cloud.order.api.vo.order_info.OrderVO;
import com.taotao.cloud.order.biz.entity.OrderInfo;
import com.taotao.cloud.web.base.controller.SuperController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.constraints.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单管理API
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2021-10-09 16:23:29
 */
@Validated
@RestController
@RequestMapping("/order")
@Tag(name = "订单管理API", description = "订单管理API")
public class OrderInfoController
	extends
	SuperController<IOrderInfoService<OrderInfo, Long>, OrderInfo, Long, BaseQuery, OrderSaveDTO, OrderUpdateDTO, OrderVO> {

	/**
	 * 根据用户id查询用户是否存在
	 *
	 * @param userId 用户id
	 * @return {@link Result &lt;java.lang.Boolean&gt; }
	 * @author shuigedeng
	 * @since 2021-10-09 15:15:05
	 */
	@Operation(summary = "根据用户id查询用户是否存在", description = "根据用户id查询用户是否存在")
	@RequestOperateLog(description = "根据用户id查询用户是否存在")
	//@PreAuthorize("hasAuthority('sys:user:exists:id')")
	@GetMapping("/exists/id/{code}")
	public Result<Boolean> existsByUserId(
		@Parameter(description = "用户id", required = true) @NotNull(message = "用户id不能为空")
		@PathVariable(name = "code") String code) {
		return success(service().existByCode(code));
	}
}

