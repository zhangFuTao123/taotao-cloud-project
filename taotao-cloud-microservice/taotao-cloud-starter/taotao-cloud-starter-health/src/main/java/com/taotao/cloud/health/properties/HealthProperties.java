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
package com.taotao.cloud.health.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * HealthProperties
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-10 17:05:24
 */
@RefreshScope
@ConfigurationProperties(prefix = HealthProperties.PREFIX)
public class HealthProperties {

	public static final String PREFIX = "taotao.cloud.health";

	/**
	 * 是否开启健康检查
	 */
	private boolean enabled = true;

	/**
	 * 健康检查时间间隔 秒
	 */
	private int healthTimeSpan = 10;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getHealthTimeSpan() {
		return healthTimeSpan;
	}

	public void setHealthTimeSpan(int healthTimeSpan) {
		this.healthTimeSpan = healthTimeSpan;
	}
}