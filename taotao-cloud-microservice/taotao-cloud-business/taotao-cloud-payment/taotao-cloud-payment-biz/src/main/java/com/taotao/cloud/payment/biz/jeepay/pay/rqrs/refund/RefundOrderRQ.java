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

package com.taotao.cloud.payment.biz.jeepay.pay.rqrs.refund;

import com.taotao.cloud.payment.biz.jeepay.pay.rqrs.AbstractMchAppRQ;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/*
 * 创建退款订单请求参数对象
 *
 * @author terrfly
 * @site https://www.jeequan.com
 * @date 2021/6/16 15:41
 */
@Data
public class RefundOrderRQ extends AbstractMchAppRQ {

    /** 商户订单号 */
    private String mchOrderNo;

    /** 支付系统订单号 */
    private String payOrderId;

    /** 商户系统生成的退款单号 */
    @NotBlank(message = "商户退款单号不能为空")
    private String mchRefundNo;

    /** 退款金额， 单位：分 */
    @NotNull(message = "退款金额不能为空")
    @Min(value = 1, message = "退款金额请大于1分")
    private Long refundAmount;

    /** 货币代码 */
    @NotBlank(message = "货币代码不能为空")
    private String currency;

    /** 退款原因 */
    @NotBlank(message = "退款原因不能为空")
    private String refundReason;

    /** 客户端IP地址 */
    private String clientIp;

    /** 异步通知地址 */
    private String notifyUrl;

    /** 特定渠道发起额外参数 */
    private String channelExtra;

    /** 商户扩展参数 */
    private String extParam;
}
