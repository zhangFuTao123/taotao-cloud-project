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
package com.taotao.cloud.rabbitmq.producer;

import com.taotao.cloud.rabbitmq.common.DetailResponse;

/**
 * 消息发送者
 *
 * @author dengtao
 * @date 2020/9/29 15:54
 * @since v1.0
 */
public interface MessageSender {

    /**
     * 发送数据
     *
     * @param message message
     * @author dengtao
     * @date 2020/9/29 15:55
     * @since v1.0
     */
    DetailResponse send(Object message);

    /**
     * 发送数据
     *
     * @param messageWithTime messageWithTime
     * @author dengtao
     * @date 2020/9/29 15:55
     * @since v1.0
     */
    DetailResponse send(MessageWithTime messageWithTime);
}
