package com.taotao.cloud.member.biz.roketmq.event.impl;


import com.google.gson.Gson;
import com.taotao.cloud.member.api.enums.PointTypeEnum;
import com.taotao.cloud.member.biz.entity.Member;
import com.taotao.cloud.member.biz.roketmq.event.MemberRegisterEvent;
import com.taotao.cloud.member.biz.service.MemberService;
import com.taotao.cloud.sys.api.enums.SettingEnum;
import com.taotao.cloud.sys.api.setting.PointSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 会员积分
 */
@Service
public class MemberPointExecute implements MemberRegisterEvent {

	/**
	 * 配置
	 */
	@Autowired
	private SettingService settingService;
	/**
	 * 会员
	 */
	@Autowired
	private MemberService memberService;
	/**
	 * 订单
	 */
	@Autowired
	private OrderService orderService;

	/**
	 * 会员注册赠送积分
	 *
	 * @param member 会员
	 */
	@Override
	public void memberRegister(Member member) {
		//获取积分设置
		PointSetting pointSetting = getPointSetting();
		//赠送会员积分
		memberService.updateMemberPoint(pointSetting.getRegister().longValue(),
			PointTypeEnum.INCREASE.name(), member.getId(),
			"会员注册，赠送积分" + pointSetting.getRegister() + "分");
	}

	/**
	 * 获取积分设置
	 *
	 * @return 积分设置
	 */
	private PointSetting getPointSetting() {
		Setting setting = settingService.get(SettingEnum.POINT_SETTING.name());
		return new Gson().fromJson(setting.getSettingValue(), PointSetting.class);
	}
}