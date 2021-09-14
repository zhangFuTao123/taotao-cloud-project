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
package com.taotao.cloud.dingtalk;

import com.taotao.cloud.dingtalk.properties.DingerProperties;
import com.taotao.cloud.dingtalk.properties.HttpClientProperties;
import com.taotao.cloud.dingtalk.properties.ThreadPoolProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * PropertiesAutoConfiguration
 *
 * @author shuigedeng
 * @version v1.0.0
 * @since 2020/7/29 14:18
 */
@EnableConfigurationProperties({ThreadPoolProperties.class,
	HttpClientProperties.class,
	DingerProperties.class})
public class PropertiesAutoConfiguration {

}