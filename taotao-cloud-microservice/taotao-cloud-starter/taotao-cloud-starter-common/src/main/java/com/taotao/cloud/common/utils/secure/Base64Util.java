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
package com.taotao.cloud.common.utils.secure;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.apache.commons.lang3.StringUtils;

/**
 * Base64工具类
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 17:52:02
 */
public class Base64Util {

	private Base64Util() {
	}

	/**
	 * Base64 编码
	 *
	 * @param text 明文
	 * @return 密文
	 * @author shuigedeng
	 * @since 2021-09-02 17:51:28
	 */
	public static String encode(String text) {
		if (StringUtils.isBlank(text)) {
			return null;
		}
		return Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * Base64 解码
	 *
	 * @param ciphertext 密文
	 * @return 明文
	 * @author shuigedeng
	 * @since 2021-09-02 17:51:28
	 */
	public static String decode(String ciphertext) {
		if (StringUtils.isBlank(ciphertext)) {
			return null;
		}
		final byte[] decode = Base64.getDecoder().decode(ciphertext);
		return new String(decode, StandardCharsets.UTF_8);
	}
}