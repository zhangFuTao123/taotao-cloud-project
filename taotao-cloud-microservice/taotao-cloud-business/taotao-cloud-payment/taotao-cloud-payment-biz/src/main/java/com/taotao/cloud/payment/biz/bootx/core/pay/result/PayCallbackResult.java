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

package com.taotao.cloud.payment.biz.bootx.core.pay.result;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 支付回调处理结果
 *
 * @author xxm
 * @date 2021/6/22
 */
@Data
@Accessors(chain = true)
public class PayCallbackResult {

    /**
     * 处理状态
     *
     * @see PayStatusCode#NOTIFY_PROCESS_SUCCESS
     */
    private int code;

    /** 提示信息 */
    private String msg;
}
