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
package com.taotao.cloud.redis.configuration;

import com.taotao.cloud.common.constant.StarterName;
import com.taotao.cloud.common.lock.DistributedLock;
import com.taotao.cloud.common.utils.LogUtil;
import com.taotao.cloud.redis.lock.RedissonDistributedLock;
import com.taotao.cloud.redis.properties.RedisLockProperties;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RedisLockAutoConfiguration
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-07 21:17:02
 */
@Configuration
@ConditionalOnBean(RedissonClient.class)
@AutoConfigureAfter(RedisAutoConfiguration.class)
@EnableConfigurationProperties({RedisLockProperties.class})
@ConditionalOnProperty(prefix = RedisLockProperties.PREFIX, name = "enabled", havingValue = "true")
public class RedisLockAutoConfiguration implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		LogUtil.started(RedisLockAutoConfiguration.class, StarterName.REDIS_STARTER);
	}

	@Bean
	@ConditionalOnMissingBean
	public DistributedLock redissonDistributedLock(RedissonClient redissonClient) {
		return new RedissonDistributedLock(redissonClient);
	}

}