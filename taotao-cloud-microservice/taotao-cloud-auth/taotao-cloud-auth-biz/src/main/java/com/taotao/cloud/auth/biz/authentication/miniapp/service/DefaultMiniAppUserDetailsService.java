package com.taotao.cloud.auth.biz.authentication.miniapp.service;

import com.taotao.cloud.auth.biz.utils.WxUtils;
import com.taotao.cloud.common.model.SecurityUser;
import com.taotao.cloud.common.utils.common.JsonUtils;
import com.taotao.cloud.member.api.feign.IFeignMemberApi;
import com.taotao.cloud.sys.api.feign.IFeignUserApi;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * // 小程序用户 自动注册和检索  1 在此处配置 优先级最高 2 注册为Spring Bean 可以免配置
 */
@Service
public class DefaultMiniAppUserDetailsService implements MiniAppUserDetailsService {

	@Autowired
	private IFeignUserApi feignUserApi;
	@Autowired
	private IFeignMemberApi feignMemberApi;
	
	@Override
	public UserDetails register(MiniAppRequest request, String sessionKey) {
		System.out.println(request);

		String signature = DigestUtils.sha1Hex(request.getRawData() + sessionKey);
		if (!request.getSignature().equals(signature)) {
			throw new RuntimeException("数字签名验证失败");
		}

		String encryptedData = request.getEncryptedData();
		String iv = request.getIv();

		// 解密encryptedData数据
		String decrypt = WxUtils.decrypt(sessionKey, iv, encryptedData);
		MiniAppUserInfo miniAppUserInfo = JsonUtils.toObject(decrypt,
			MiniAppUserInfo.class);
		miniAppUserInfo.setSessionKey(sessionKey);

		System.out.println(miniAppUserInfo);

		// 调用数据库 微信小程序用户注册

		//模拟
		return SecurityUser.builder()
			.account("admin")
			.userId(1L)
			.username("admin")
			.nickname("admin")
			.password(
				"$2a$10$ofQ95D2nNs1JC.JiPaGo3O11.P7sP3TkcRyXBpyfskwBDJRAh0caG")
			.phone("15730445331")
			.mobile("15730445331")
			.email("981376578@qq.com")
			.sex(1)
			.status(1)
			.type(2)
			.permissions(Set.of("xxx", "sldfl"))
			.build();
	}

	@Override
	public UserDetails loadByOpenId(String clientId, String openId) {
		System.out.println(clientId);
		System.out.println(openId);

		// 模拟 根据openid 查询 小程序用户信息
		return null;
	}
}