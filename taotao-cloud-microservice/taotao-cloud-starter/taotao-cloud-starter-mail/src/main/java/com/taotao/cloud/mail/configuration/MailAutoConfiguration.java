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
package com.taotao.cloud.mail.configuration;

import com.taotao.cloud.common.constant.StarterNameConstant;
import com.taotao.cloud.common.utils.LogUtil;
import com.taotao.cloud.mail.template.JavaMailTemplate;
import com.taotao.cloud.mail.template.MailTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * MailAutoConfiguration
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-09 11:38:47
 */
@Configuration
@ConditionalOnProperty(prefix = com.taotao.cloud.mail.properties.MailProperties.PREFIX, name = "enabled", havingValue = "true")
@AutoConfigureAfter(MailSenderAutoConfiguration.class)
public class MailAutoConfiguration implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		LogUtil.started(MailAutoConfiguration.class, StarterNameConstant.MAIL_STARTER);
	}

	@Bean
	@ConditionalOnBean({MailProperties.class, JavaMailSender.class})
	public MailTemplate mailTemplate(JavaMailSender mailSender, MailProperties mailProperties) {
		LogUtil.started(MailTemplate.class, StarterNameConstant.MAIL_STARTER);

		return new JavaMailTemplate(mailSender, mailProperties);
	}
}