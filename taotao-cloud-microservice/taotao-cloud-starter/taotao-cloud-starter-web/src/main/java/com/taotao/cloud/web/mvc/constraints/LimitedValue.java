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
package com.taotao.cloud.web.mvc.constraints;

import com.taotao.cloud.web.mvc.validator.LimitedValueValidator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * LimitedValue
 *
 * @version 1.0.0
 * @author shuigedeng
 * @since 2021/8/24 23:25
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {LimitedValueValidator.class})
public @interface LimitedValue {

	//默认错误消息
	String message() default "必须为指定值";

	boolean allowNullValue() default false;

	String[] strValues() default {};

	int[] intValues() default {};

	//分组
	Class<?>[] groups() default {};

	//负载
	Class<? extends Payload>[] payload() default {};
}
