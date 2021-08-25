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
package com.taotao.cloud.web.mvc.validator;

import cn.hutool.core.util.StrUtil;
import com.taotao.cloud.web.enums.YesOrNotEnum;
import com.taotao.cloud.web.mvc.constraints.FlagValue;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验标识，只有Y和N两种状态的标识
 *
 * @author shuigedeng
 * @version 1.0.0
 * @since 2021/8/24 23:29
 */
public class FlagValueValidator implements ConstraintValidator<FlagValue, String> {

	private Boolean required;

	@Override
	public void initialize(FlagValue constraintAnnotation) {
		this.required = constraintAnnotation.required();
	}

	@Override
	public boolean isValid(String flagValue, ConstraintValidatorContext context) {

		// 如果是必填的
		if (required) {
			return YesOrNotEnum.Y.getCode().equals(flagValue) || YesOrNotEnum.N.getCode()
				.equals(flagValue);
		} else {
			//如果不是必填，可以为空
			if (StrUtil.isEmpty(flagValue)) {
				return true;
			} else {
				return YesOrNotEnum.Y.getCode().equals(flagValue) || YesOrNotEnum.N.getCode()
					.equals(flagValue);
			}
		}
	}
}
