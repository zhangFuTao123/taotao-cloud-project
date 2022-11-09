package com.taotao.cloud.member.api.feign.fallback;

import cn.hutool.core.date.DateTime;
import com.taotao.cloud.member.api.feign.IFeignMemberRechargeApi;
import com.taotao.cloud.member.api.model.vo.MemberRechargeVO;
import org.springframework.cloud.openfeign.FallbackFactory;

import java.math.BigDecimal;
import java.util.List;

/**
 * RemoteMemberFallbackImpl
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2020/11/20 下午4:10
 */
public class FeignMemberRechargeApiFallback implements FallbackFactory<IFeignMemberRechargeApi> {

	@Override
	public IFeignMemberRechargeApi create(Throwable throwable) {
		return new IFeignMemberRechargeApi() {
			@Override
			public Boolean paySuccess(String sn, String receivableNo, String paymentMethod) {
				return null;
			}

			@Override
			public MemberRechargeVO getRecharge(String sn) {
				return null;
			}

			@Override
			public MemberRechargeVO recharge(BigDecimal price) {
				return null;
			}

			@Override
			public List<MemberRechargeVO> list(DateTime dateTime) {
				return null;
			}

			@Override
			public Boolean rechargeOrderCancel(String sn) {
				return null;
			}
		};
	}
}
