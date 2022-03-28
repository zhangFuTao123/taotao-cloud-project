package com.taotao.cloud.member.biz.roketmq.event;


import com.taotao.cloud.member.biz.entity.Member;

/**
 * 会员登录消息
 */
public interface MemberLoginEvent {

	/**
	 * 会员登录
	 *
	 * @param member 会员
	 */
	void memberLogin(Member member);
}