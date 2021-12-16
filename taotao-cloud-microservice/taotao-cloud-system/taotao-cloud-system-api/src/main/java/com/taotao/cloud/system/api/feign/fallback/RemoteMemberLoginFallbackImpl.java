package com.taotao.cloud.system.api.feign.fallback;

import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.common.utils.LogUtil;
import com.taotao.cloud.system.api.feign.RemoteMemberLoginService;
import com.taotao.cloud.system.api.vo.MemberLoginVO;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * RemoteLogFallbackImpl
 *
 * @author shuigedeng
 * @since 2020/4/29 21:43
 */
public class RemoteMemberLoginFallbackImpl implements FallbackFactory<RemoteMemberLoginService> {

	@Override
	public RemoteMemberLoginService create(Throwable throwable) {
		return new RemoteMemberLoginService() {
			@Override
			public Result<MemberLoginVO> findMemberLoginById(Long id) {
				LogUtil.error("调用findMemberLoginById异常：{}", throwable, id);
				return Result.fail(null, 500);
			}
		};
	}
}