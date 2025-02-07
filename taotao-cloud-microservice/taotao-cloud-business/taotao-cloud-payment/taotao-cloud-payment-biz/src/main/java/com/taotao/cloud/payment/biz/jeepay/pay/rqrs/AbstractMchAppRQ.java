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

package com.taotao.cloud.payment.biz.jeepay.pay.rqrs;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/*
 *
 * 通用RQ, 包含mchNo和appId 必填项
 *
 * @author terrfly
 * @site https://www.jeequan.com
 * @date 2021/6/16 10:30
 */
@Data
public class AbstractMchAppRQ extends AbstractRQ {

    /** 商户号 */
    @NotBlank(message = "商户号不能为空")
    private String mchNo;

    /** 商户应用ID */
    @NotBlank(message = "商户应用ID不能为空")
    private String appId;
}
