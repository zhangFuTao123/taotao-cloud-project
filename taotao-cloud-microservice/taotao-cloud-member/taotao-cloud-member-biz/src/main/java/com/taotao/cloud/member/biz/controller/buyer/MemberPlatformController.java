package com.taotao.cloud.member.biz.controller.buyer;

import com.taotao.cloud.member.biz.service.IMemberPlatformService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 第三方用户信息管理API
 *
 * @author shuigedeng
 * @since 2020-10-16 16:23:49
 * @since 1.0
 */
@Validated
@RestController
@RequestMapping("/member/platform")
@Tag(name = "第三方用户信息管理API", description = "第三方用户信息管理API")
public class MemberPlatformController {

	private final IMemberPlatformService memberPlatformService;

	public MemberPlatformController(
		IMemberPlatformService memberPlatformService) {
		this.memberPlatformService = memberPlatformService;
	}
}