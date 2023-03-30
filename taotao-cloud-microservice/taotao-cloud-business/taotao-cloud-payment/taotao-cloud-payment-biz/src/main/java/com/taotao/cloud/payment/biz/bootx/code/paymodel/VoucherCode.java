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

package com.taotao.cloud.payment.biz.bootx.code.paymodel;

/**
 * 储值卡常量
 *
 * @author xxm
 * @date 2022/3/14
 */
public interface VoucherCode {

    /** 状态-启用 */
    int STATUS_NORMAL = 1;

    /** 状态-停用 */
    int STATUS_FORBIDDEN = 2;

    /** 储值卡日志-开通 */
    int LOG_ACTIVE = 1;

    /** 储值卡日志-支付 */
    int LOG_PAY = 2;

    /** 储值卡日志-退款 */
    int LOG_CLOSE = 3;

    /** 储值卡日志-退款 */
    int LOG_REFUND = 4;

    /** 储值卡日志-Admin余额变动 */
    int LOG_ADMIN_CHANGER = 4;
}
