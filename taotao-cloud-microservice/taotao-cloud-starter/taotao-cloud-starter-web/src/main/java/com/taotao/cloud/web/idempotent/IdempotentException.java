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
package com.taotao.cloud.web.idempotent;

import com.taotao.cloud.common.exception.BaseException;

/**
 * 幂等校验异常
 *
 * @version 1.0.0
 * @author shuigedeng
 * @since 2021/8/24 23:42
 */
public class IdempotentException extends BaseException {

	private static final long serialVersionUID = -851115183208290929L;

	public IdempotentException(String message) {
		super(message);
	}
}
