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

package com.taotao.cloud.message.biz.austin.handler.alipay;

import com.alipay.api.AlipayApiException;
import com.taotao.cloud.message.biz.austin.handler.domain.alipay.AlipayMiniProgramParam;

/**
 * @author jwq 支付宝小程序发送订阅消息接口
 */
public interface AlipayMiniProgramAccountService {

    /**
     * 发送订阅消息
     *
     * @param miniProgramParam 订阅消息参数
     * @throws AlipayApiException alipay异常
     */
    void send(AlipayMiniProgramParam miniProgramParam) throws AlipayApiException;
}
