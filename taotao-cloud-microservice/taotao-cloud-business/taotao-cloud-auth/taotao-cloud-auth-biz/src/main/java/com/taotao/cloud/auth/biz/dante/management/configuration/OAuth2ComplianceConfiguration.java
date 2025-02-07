/*
 * Copyright (c) 2020-2030 ZHENGGENGWEI(码匠君)<herodotus@aliyun.com>
 *
 * Dante Engine licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * <http://www.apache.org/licenses/LICENSE-2.0>
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Dante Engine 采用APACHE LICENSE 2.0开源协议，您在使用过程中，需要注意以下几点：
 *
 * 1.请不要删除和修改根目录下的LICENSE文件。
 * 2.请不要删除和修改 Dante Cloud 源码头部的版权声明。
 * 3.请保留源码和相关描述文件的项目出处，作者声明等。
 * 4.分发源码时候，请注明软件出处 <https://gitee.com/herodotus/dante-engine>
 * 5.在修改包名，模块名称，项目代码等时，请注明软件出处 <https://gitee.com/herodotus/dante-engine>
 * 6.若您的项目无法满足以上几点，可申请商业授权
 */

package com.taotao.cloud.auth.biz.dante.management.configuration;

import com.taotao.cloud.auth.biz.dante.authentication.stamp.LockedUserDetailsStampManager;
import com.taotao.cloud.auth.biz.dante.authentication.stamp.SignInFailureLimitedStampManager;
import com.taotao.cloud.auth.biz.dante.management.compliance.OAuth2AccountStatusManager;
import com.taotao.cloud.auth.biz.dante.management.compliance.annotation.ConditionalOnAutoUnlockUserAccount;
import com.taotao.cloud.auth.biz.dante.management.compliance.event.AccountStatusChanger;
import com.taotao.cloud.auth.biz.dante.management.compliance.listener.AccountAutoEnableListener;
import com.taotao.cloud.auth.biz.dante.management.compliance.listener.AuthenticationFailureListener;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>Description: OAuth2 应用安全合规配置 </p>
 *
 * @author : gengwei.zheng
 * @date : 2022/7/11 10:20
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(AccountStatusChanger.class)
public class OAuth2ComplianceConfiguration {

	private static final Logger log = LoggerFactory.getLogger(OAuth2ComplianceConfiguration.class);

	@PostConstruct
	public void postConstruct() {
		log.debug("[Herodotus] |- SDK [OAuth2 Compliance] Auto Configure.");
	}

	@Bean
	public OAuth2AccountStatusManager accountStatusManager(UserDetailsService userDetailsService, AccountStatusChanger accountStatusChanger, LockedUserDetailsStampManager lockedUserDetailsStampManager) {
		OAuth2AccountStatusManager manager = new OAuth2AccountStatusManager(userDetailsService, accountStatusChanger, lockedUserDetailsStampManager);
		log.trace("[Herodotus] |- Bean [OAuth2 Account Status Manager] Auto Configure.");
		return manager;
	}

	@Bean
	@ConditionalOnAutoUnlockUserAccount
	public AccountAutoEnableListener accountLockStatusListener(RedisMessageListenerContainer redisMessageListenerContainer, OAuth2AccountStatusManager accountStatusManager) {
		AccountAutoEnableListener lockStatusListener = new AccountAutoEnableListener(redisMessageListenerContainer, accountStatusManager);
		log.trace("[Herodotus] |- Bean [OAuth2 Account Lock Status Listener] Auto Configure.");
		return lockStatusListener;
	}

	@Bean
	@ConditionalOnMissingBean
	public AuthenticationFailureListener authenticationFailureListener(SignInFailureLimitedStampManager stampManager, OAuth2AccountStatusManager accountLockService) {
		AuthenticationFailureListener authenticationFailureListener = new AuthenticationFailureListener(stampManager, accountLockService);
		log.trace("[Herodotus] |- Bean [OAuth2 Authentication Failure Listener] Auto Configure.");
		return authenticationFailureListener;
	}
}
