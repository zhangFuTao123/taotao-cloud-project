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
package com.taotao.cloud.gateway.configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import java.time.Duration;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CustomizeCircuitBreakerConfiguration
 *
 * @author shuigedeng
 * @version 1.0.0
 * @since 2021/11/23 14:28
 */
@Configuration
public class CustomizeCircuitBreakerConfiguration {

	@Bean
	public ReactiveResilience4JCircuitBreakerFactory defaultCustomizer() {

		CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom() //
			.slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED) // 滑动窗口的类型为时间窗口
			.slidingWindowSize(10) // 时间窗口的大小为60秒
			.minimumNumberOfCalls(5) // 在单位时间窗口内最少需要5次调用才能开始进行统计计算
			.failureRateThreshold(50) // 在单位时间窗口内调用失败率达到50%后会启动断路器
			.enableAutomaticTransitionFromOpenToHalfOpen() // 允许断路器自动由打开状态转换为半开状态
			.permittedNumberOfCallsInHalfOpenState(5) // 在半开状态下允许进行正常调用的次数
			.waitDurationInOpenState(Duration.ofSeconds(5)) // 断路器打开状态转换为半开状态需要等待60秒
			.recordExceptions(Throwable.class) // 所有异常都当作失败来处理
			.build();

		ReactiveResilience4JCircuitBreakerFactory factory = new ReactiveResilience4JCircuitBreakerFactory();
		factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
			.timeLimiterConfig(
				TimeLimiterConfig.custom().timeoutDuration(Duration.ofMillis(200)).build())
			.circuitBreakerConfig(circuitBreakerConfig).build());

		return factory;
	}
}