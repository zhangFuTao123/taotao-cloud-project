/*
 * Copyright 2018-2022 the original author or authors.
 *
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.gnu.org/licenses/lgpl-3.0.html
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.taotao.cloud.sms.channel.jdcloud;

import com.taotao.cloud.sms.configuration.SmsAutoConfiguration;
import com.taotao.cloud.sms.loadbalancer.SmsSenderLoadBalancer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 京东云发送端点自动配置
 *
 * @author shuigedeng
 */
@Configuration
@ConditionalOnProperty(prefix = JdCloudProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties(JdCloudProperties.class)
@AutoConfigureAfter(SmsAutoConfiguration.class)
public class JdCloudAutoConfiguration {

	/**
	 * 构造京东云发送处理
	 *
	 * @param properties     配置对象
	 * @param loadbalancer   负载均衡器
	 * @param eventPublisher spring应用事件发布器
	 * @return 京东云发送处理
	 */
	@Bean
	@ConditionalOnBean(SmsSenderLoadBalancer.class)
	public JdCloudSendHandler jdCloudSendHandler(JdCloudProperties properties,
		SmsSenderLoadBalancer loadbalancer,
		ApplicationEventPublisher eventPublisher) {
		JdCloudSendHandler handler = new JdCloudSendHandler(properties, eventPublisher);
		loadbalancer.addTarget(handler, true);
		loadbalancer.setWeight(handler, properties.getWeight());
		return handler;
	}

	public static class JdCloudSendHandlerCondition implements Condition {

		@Override
		public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
			Boolean enable = context.getEnvironment()
				.getProperty(JdCloudProperties.PREFIX + ".enable", Boolean.class);
			return enable == null || enable;
		}
	}

}