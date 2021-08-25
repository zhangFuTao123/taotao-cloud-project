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
package com.taotao.cloud.web.xss;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.taotao.cloud.common.utils.LogUtil;
import com.taotao.cloud.web.util.XssUtil;

/**
 * 基于xss的 json 序列化器 在本项目中，没有使用该类
 *
 * @author shuigedeng
 * @version 1.0.0
 * @since 2021/8/24 22:43
 */
public class XssStringJsonSerializer extends JsonSerializer<String> {

	@Override
	public Class<String> handledType() {
		return String.class;
	}

	@Override
	public void serialize(String value, JsonGenerator jsonGenerator,
		SerializerProvider serializerProvider) {
		if (StrUtil.isEmpty(value)) {
			return;
		}
		try {
			String encodedValue = XssUtil.xssClean(value, null);
			jsonGenerator.writeString(encodedValue);
		} catch (Exception e) {
			LogUtil.error("序列化失败:[{0}]", value, e);
		}
	}

}
