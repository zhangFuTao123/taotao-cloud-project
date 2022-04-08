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
package com.taotao.cloud.order.biz.aop.order;

import com.taotao.cloud.logger.event.RequestLoggerEvent;
import com.taotao.cloud.logger.model.RequestLogger;
import com.taotao.cloud.logger.service.IRequestLoggerService;
import com.taotao.cloud.order.biz.entity.trade.OrderLog;
import com.taotao.cloud.order.biz.service.trade.OrderLogService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 异步监听订单日志事件
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-07 20:27:09
 */
@Component
public class OrderLogListener {

	@Autowired
	private OrderLogService orderLogService;

	@Async
	@EventListener(OrderLogEvent.class)
	public void saveOrderLog(OrderLogEvent event) {
		OrderLog orderLog = (OrderLog) event.getSource();
		orderLogService.save(orderLog);
	}
}