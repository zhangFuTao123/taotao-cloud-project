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
package com.taotao.cloud.data.jpa.properties;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;


/**
 * TenantProperties
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-04 07:32:13
 */
@RefreshScope
@ConfigurationProperties(prefix = HibernateProperties.PREFIX)
public class HibernateProperties {

	public static final String PREFIX = "taotao.cloud.hibernate";

	/**
	 * 是否开启多租户
	 */
	private Boolean enabled = true;

	/**
	 * basePackages
	 */
	private String packages = "com.taotao.cloud.*.biz.entity";

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getPackages() {
		return packages;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}
}