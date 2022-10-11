package com.taotao.cloud.member.api.feign;

import cn.hutool.core.date.DateTime;
import com.taotao.cloud.common.constant.ServiceName;
import com.taotao.cloud.member.api.feign.fallback.FeignMemberApiFallback;
import com.taotao.cloud.member.api.model.vo.MemberRechargeVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

/**
 * 远程调用会员用户模块
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-25 16:37:54
 */
@FeignClient(value = ServiceName.TAOTAO_CLOUD_MEMBER_CENTER, fallbackFactory = FeignMemberApiFallback.class)
public interface FeignMemberRechargeApi {

	@GetMapping(value = "/member/recharge/paySuccess")
	Boolean paySuccess(@RequestParam String sn, @RequestParam String receivableNo, @RequestParam String paymentMethod);

	@GetMapping(value = "/member/recharge/getRecharge")
	MemberRechargeVO getRecharge(@RequestParam String sn);

	@GetMapping(value = "/member/recharge/recharge")
	MemberRechargeVO recharge(@RequestParam BigDecimal price);

	/**
	 * LambdaQueryWrapper<Recharge> queryWrapper = new LambdaQueryWrapper<>();
	 * queryWrapper.eq(Recharge::getPayStatus, PayStatusEnum.UNPAID.name());
	 * //充值订单创建时间 <= 订单自动取消时间
	 * queryWrapper.le(Recharge::getCreateTime, cancelTime);
	 *
	 * @return
	 */
	@GetMapping(value = "/member/recharge/list")
	List<MemberRechargeVO> list(@RequestParam DateTime dateTime);

	@GetMapping(value = "/member/recharge/rechargeOrderCancel")
	Boolean rechargeOrderCancel(@RequestParam String sn);
}
