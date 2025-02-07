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

package com.taotao.cloud.message.biz.austin.cron.constants;

/**
 * @author 3y
 * @date 2022/2/13 延迟缓冲 pending 常量信息
 */
public class PendingConstant {

    /** 阻塞队列大小 */
    public static final Integer QUEUE_SIZE = 100;

    /** 触发执行的数量阈值 */
    public static final Integer NUM_THRESHOLD = 100;

    /** batch 触发执行的时间阈值，单位毫秒【必填】 */
    public static final Long TIME_THRESHOLD = 1000L;
}
