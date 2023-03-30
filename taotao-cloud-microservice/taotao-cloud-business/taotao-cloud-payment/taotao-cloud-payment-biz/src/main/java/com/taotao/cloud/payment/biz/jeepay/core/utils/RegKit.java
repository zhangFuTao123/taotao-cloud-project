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

package com.taotao.cloud.payment.biz.jeepay.core.utils;

/*
 *
 * 正则验证kit
 * @author terrfly
 * @site https://www.jeequan.com
 * @date 2021/6/8 16:56
 */
public class RegKit {

    public static final String REG_MOBILE = "^1\\d{10}$"; // 判断是否是手机号
    public static final String REG_ALIPAY_USER_ID = "^2088\\d{12}$"; // 判断是支付宝用户Id 以2088开头的纯16位数字

    public static boolean isMobile(String str) {
        return match(str, REG_MOBILE);
    }

    public static boolean isAlipayUserId(String str) {
        return match(str, REG_ALIPAY_USER_ID);
    }

    /** 正则验证 */
    public static boolean match(String text, String reg) {
        if (text == null) {
            return false;
        }
        return text.matches(reg);
    }
}
