package com.taotao.cloud.promotion.biz.event.email;

import org.springframework.context.ApplicationEvent;

public class EmailSendEvent extends ApplicationEvent {
	public EmailSendEvent(Object source) {
		super(source);
	}
}