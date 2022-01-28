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
package com.taotao.cloud.sms.channel.qiniu;

import com.qiniu.http.Response;
import com.qiniu.sms.SmsManager;
import com.qiniu.util.Auth;
import com.taotao.cloud.common.utils.LogUtil;
import com.taotao.cloud.sms.exception.SendFailedException;
import com.taotao.cloud.sms.handler.AbstractSendHandler;
import com.taotao.cloud.sms.model.NoticeData;
import java.util.Collection;
import org.springframework.context.ApplicationEventPublisher;

/**
 * 七牛云发送处理
 *
 * @author shuigedeng
 */
public class QiNiuSendHandler extends AbstractSendHandler<QiNiuProperties> {

	private final SmsManager smsManager;

	public QiNiuSendHandler(QiNiuProperties properties, ApplicationEventPublisher eventPublisher) {
		super(properties, eventPublisher);
		Auth auth = Auth.create(properties.getAccessKey(), properties.getSecretKey());
		smsManager = new SmsManager(auth);
	}

	@Override
	public String getChannelName() {
		return "qiNiu";
	}

	@Override
	public boolean send(NoticeData noticeData, Collection<String> phones) {
		String type = noticeData.getType();

		String templateId = properties.getTemplates(type);

		if (templateId == null) {
			LogUtil.debug("templateId invalid");
			publishSendFailEvent(noticeData, phones, new SendFailedException("templateId invalid"));
			return false;
		}

		try {
			Response response = smsManager
				.sendMessage(templateId, phones.toArray(new String[]{}), noticeData.getParams());

			if (response.isOK()) {
				publishSendSuccessEvent(noticeData, phones);
				return true;
			}

			LogUtil.debug("send fail, error: {}", response.error);
			publishSendFailEvent(noticeData, phones, new SendFailedException(response.error));
			return false;
		} catch (Exception e) {
			LogUtil.debug(e.getLocalizedMessage(), e);
			publishSendFailEvent(noticeData, phones, e);
		}

		return false;
	}
}