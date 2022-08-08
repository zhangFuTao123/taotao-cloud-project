/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
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
package com.taotao.cloud.logger.logging.config;

import com.taotao.cloud.common.utils.lang.ObjectUtil;
import com.taotao.cloud.logger.logging.appender.Appender;
import com.taotao.cloud.logger.logging.appender.ILoggingAppender;
import com.taotao.cloud.logger.logging.appender.LoggingFileAppender;
import com.taotao.cloud.logger.logging.appender.LoggingJsonFileAppender;
import com.taotao.cloud.logger.logging.appender.LoggingLogStashAppender;
import com.taotao.cloud.logger.logging.appender.LoggingLokiAppender;
import com.taotao.cloud.logger.logging.listener.LogbackLoggerContextListener;
import com.taotao.cloud.logger.logging.listener.LoggingStartedEventListener;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * logging 日志配置
 *
 * @author shuigedeng
 * @version 2022.06
 * @since 2022-07-31 15:42:47
 */
@AutoConfiguration
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties(MicaLoggingProperties.class)
public class MicaLoggingConfiguration {

	@Bean
	public LoggingStartedEventListener loggingStartedEventListener(MicaLoggingProperties loggingProperties) {
		return new LoggingStartedEventListener(loggingProperties);
	}

	@Bean
	public LogbackLoggerContextListener logbackLoggerContextListener(ObjectProvider<List<ILoggingAppender>> provider) {
		List<ILoggingAppender> loggingAppenderList = provider.getIfAvailable(ArrayList::new);
		return new LogbackLoggerContextListener(loggingAppenderList);
	}

	@Configuration(proxyBeanMethods = false)
	@ConditionalOnAppender(Appender.FILE)
	public static class LoggingFileConfiguration {

		@Bean
		public LoggingFileAppender loggingFileAppender(Environment environment,
													   MicaLoggingProperties properties) {
			return new LoggingFileAppender(environment, properties);
		}
	}

	@Configuration(proxyBeanMethods = false)
	@ConditionalOnAppender(Appender.FILE_JSON)
	public static class LoggingJsonFileConfiguration {

		@Bean
		public LoggingJsonFileAppender loggingJsonFileAppender(Environment environment,
															   MicaLoggingProperties properties) {
			return new LoggingJsonFileAppender(environment, properties);
		}
	}

	@Configuration(proxyBeanMethods = false)
	@ConditionalOnAppender(Appender.LOG_STASH)
	public static class LoggingLogStashConfiguration {

		@Bean
		public LoggingLogStashAppender loggingLogStashAppender(Environment environment,
															   MicaLoggingProperties properties) {
			return new LoggingLogStashAppender(environment, properties);
		}
	}

	@Configuration(proxyBeanMethods = false)
	@ConditionalOnAppender(Appender.LOKI)
	public static class LoggingLokiConfiguration {

		@Bean
		public LoggingLokiAppender loggingLokiAppender(Environment environment,
													   MicaLoggingProperties properties) {
			return new LoggingLokiAppender(environment, properties);
		}
	}

	@Target({ElementType.TYPE, ElementType.METHOD})
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@Conditional(LoggingCondition.class)
	public @interface ConditionalOnAppender {

		/**
		 * Appender
		 *
		 * @return Appender
		 */
		Appender value();

	}

	@Order(Ordered.HIGHEST_PRECEDENCE)
	private static class LoggingCondition extends SpringBootCondition {
		private static final String LOG_STASH_CLASS_NAME = "net.logstash.logback.encoder.LoggingEventCompositeJsonEncoder";
		private static final String LOKI_CLASS_NAME = "com.github.loki4j.logback.Loki4jAppender";

		@Override
		public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
			Map<String, Object> attributes = metadata.getAnnotationAttributes(ConditionalOnAppender.class.getName());
			Object value = Objects.requireNonNull(attributes).get("value");
			Appender appender = Appender.valueOf(value.toString());
			Environment environment = context.getEnvironment();
			ClassLoader classLoader = context.getClassLoader();

			Boolean fileEnabled = environment.getProperty(MicaLoggingProperties.Files.PREFIX + ".enabled", Boolean.class, Boolean.TRUE);
			Boolean logStashEnabled = environment.getProperty(MicaLoggingProperties.Logstash.PREFIX + ".enabled", Boolean.class, Boolean.FALSE);
			Boolean lokiEnabled = environment.getProperty(MicaLoggingProperties.Loki.PREFIX + ".enabled", Boolean.class, Boolean.FALSE);

			if (Appender.LOKI == appender) {
				if (ObjectUtil.isFalse(lokiEnabled)) {
					return ConditionOutcome.noMatch("Logging loki is not enabled.");
				}
				if (hasLokiDependencies(classLoader)) {
					return ConditionOutcome.match();
				}
				throw new IllegalStateException("Logging loki is enabled, please add com.github.loki4j loki-logback-appender dependencies.");
			} else if (Appender.LOG_STASH == appender) {
				if (ObjectUtil.isFalse(logStashEnabled)) {
					return ConditionOutcome.noMatch("Logging logstash is not enabled.");
				}
				if (hasLogStashDependencies(classLoader)) {
					return ConditionOutcome.match();
				}
				throw new IllegalStateException("Logging logstash is enabled, please add logstash-logback-encoder dependencies.");
			} else if (Appender.FILE_JSON == appender) {
				Boolean isUseJsonFormat = environment.getProperty(MicaLoggingProperties.Files.PREFIX + ".use-json-format", Boolean.class, Boolean.FALSE);
				// 没有开启文件或者没有开启 json 格式化
				if (ObjectUtil.isFalse(fileEnabled) || ObjectUtil.isFalse(isUseJsonFormat)) {
					return ConditionOutcome.noMatch("Logging json file is not enabled.");
				}
				if (hasLogStashDependencies(classLoader)) {
					return ConditionOutcome.match();
				}
				throw new IllegalStateException("Logging file json format is enabled, please add logstash-logback-encoder dependencies.");
			} else if (Appender.FILE == appender) {
				if (ObjectUtil.isFalse(fileEnabled)) {
					return ConditionOutcome.noMatch("Logging logstash is not enabled.");
				}
				return ConditionOutcome.match();
			} else {
				return ConditionOutcome.match();
			}
		}

		private static boolean hasLogStashDependencies(ClassLoader classLoader) {
			return ClassUtils.isPresent(LOG_STASH_CLASS_NAME, classLoader);
		}

		private static boolean hasLokiDependencies(ClassLoader classLoader) {
			return ClassUtils.isPresent(LOKI_CLASS_NAME, classLoader);
		}
	}

}