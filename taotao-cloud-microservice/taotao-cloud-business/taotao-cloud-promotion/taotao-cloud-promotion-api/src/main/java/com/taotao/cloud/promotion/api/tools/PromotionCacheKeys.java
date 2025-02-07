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

package com.taotao.cloud.promotion.api.tools;

import com.taotao.cloud.common.enums.CachePrefix;

/** 满额活动缓存Key */
public class PromotionCacheKeys {

    /**
     * 读取满优惠redis key
     *
     * @param activityId 活动ID
     * @return 满优惠redis key
     */
    public static String getFullDiscountKey(String activityId) {
        return CachePrefix.STORE_ID_FULL_DISCOUNT + "::" + activityId;
    }

    /**
     * 读取满优惠redis key
     *
     * @param id id
     * @return 满优惠redis key
     */
    public static String getPromotionGoodsKey(String id) {
        return CachePrefix.PROMOTION_GOODS + "::" + id;
    }

    /**
     * 读取秒杀活动redis key
     *
     * @param timeStr 时间字符串（格式为 yyyyMMdd）
     * @return 满优惠redis key
     */
    public static String getSeckillTimelineKey(String timeStr) {
        return CachePrefix.STORE_ID_SECKILL + "::" + timeStr;
    }
}
