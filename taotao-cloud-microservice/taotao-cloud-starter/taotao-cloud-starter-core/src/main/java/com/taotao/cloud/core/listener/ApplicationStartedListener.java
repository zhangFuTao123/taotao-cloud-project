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
package com.taotao.cloud.core.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * application 启动后状态的监听器
 */
public abstract class ApplicationStartedListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {

        // 如果是配置中心的上下文略过，spring cloud环境environment会读取不到
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        if (applicationContext instanceof AnnotationConfigApplicationContext) {
            return;
        }

        // 执行具体业务
        this.eventCallback(event);
    }

    /**
     * 监听器具体的业务逻辑
     */
    public abstract void eventCallback(ApplicationStartedEvent event);

}
