/*
 * Copyright 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
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

package com.taotao.cloud.common.utils.reflect;

/**
 * 反射数组具类
 */
public final class ReflectArrayUtil {

    private ReflectArrayUtil() {}

    /**
     * 获取数组的元素类型
     * @param objects 数组
     * @return 元素类型
     */
    public static Class getComponentType(final Object[] objects) {
        Class arrayClass = objects.getClass();
        return arrayClass.getComponentType();
    }

}
