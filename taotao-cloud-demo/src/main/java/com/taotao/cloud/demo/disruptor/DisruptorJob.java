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
package com.taotao.cloud.demo.disruptor;

import com.taotao.cloud.disruptor.context.DisruptorTemplate;
import com.taotao.cloud.disruptor.event.DisruptorBindEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * DisruptorJob
 *
 * @author shuigedeng
 * @version 1.0.0
 * @since 2021/09/03 17:58
 */
@Configuration
@EnableScheduling
public class DisruptorJob {

	@Autowired
	private DisruptorTemplate disruptorTemplate;

	@Scheduled(fixedDelay = 1000000)
	public void send() {
		DisruptorBindEvent envent = new DisruptorBindEvent(this, "message" + Math.random());
		envent.setEvent("Event-Output");
		envent.setTag("TagA-Output");
		envent.setKey("id" + Math.random());

		disruptorTemplate.publishEvent(envent);
	}

	@Scheduled(fixedDelay = 1000000)
	public void send2() {
		DisruptorBindEvent envent = new DisruptorBindEvent(this, "message" + Math.random());
		envent.setEvent("Event-Output");
		envent.setTag("TagB-Output");
		envent.setKey("id" + Math.random());

		disruptorTemplate.publishEvent(envent);
	}
}