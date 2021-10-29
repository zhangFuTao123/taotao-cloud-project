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
package com.taotao.cloud.bulletin.api.feign.fallback;

import com.taotao.cloud.bulletin.api.feign.RemoteBulletinService;
import com.taotao.cloud.bulletin.api.vo.BulletinVO;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.common.utils.LogUtil;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * RemoteLogFallbackImpl
 *
 * @author shuigedeng
 * @since 2020/4/29 21:43
 */
public class RemoteBulletinFallbackImpl implements FallbackFactory<RemoteBulletinService> {
	@Override
	public RemoteBulletinService create(Throwable throwable) {
		return new RemoteBulletinService() {
			@Override
			public Result<BulletinVO> getMemberSecurityUser(Long id) {
				LogUtil.error("调用getMemberSecurityUser异常：{}", throwable, id);
				return Result.fail(null, 500);
			}
		};
	}
}