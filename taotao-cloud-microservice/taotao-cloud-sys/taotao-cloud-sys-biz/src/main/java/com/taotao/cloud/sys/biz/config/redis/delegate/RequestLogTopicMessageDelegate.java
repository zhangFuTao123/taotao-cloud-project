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
package com.taotao.cloud.sys.biz.config.redis.delegate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.taotao.cloud.sys.biz.entity.Log;
import com.taotao.cloud.sys.biz.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * SensitiveWordsTopicMessageDelegate
 *
 * @author shuigedeng
 * @version 1.0.0
 * @since 2022/02/09 20:46
 */
@Component
public class RequestLogTopicMessageDelegate {

	@Autowired
	private ILogService logService;

	public void handleRequestLog(String message, String channel) {
		ObjectMapper mapper = new ObjectMapper();
		// 使用Jackson转换带下划线的属性为驼峰属性
		mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
		try {
			Log log = mapper.readValue(message, Log.class);
			logService.save(log);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}