package com.taotao.cloud.sa.just.biz.just.justauth.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

/**
 * <p>
 * 租户第三方登录信息配置表
 * </p>
 *
 * @since 2022-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "JustAuthSource对象", description = "租户第三方登录信息配置表")
public class UpdateJustAuthSourceDTO {

	private static final long serialVersionUID = 1L;

	@Schema(description = "主键")
	private Long id;

	@Schema(description = "名称")
	@NotBlank(message = "名称不能为空")
	@Length(min = 1, max = 32)
	private String sourceName;

	@Schema(description = "登录类型")
	@NotBlank(message = "登录类型不能为空")
	@Length(min = 1, max = 32)
	private String sourceType;

	@Schema(description = "自定义Class")
	@Length(min = 1, max = 255)
	private String requestClass;

	@Schema(description = "客户端id")
	@NotBlank(message = "客户端id不能为空")
	@Length(min = 1, max = 100)
	private String clientId;

	@Schema(description = "客户端Secret")
	@NotBlank(message = "客户端Secret不能为空")
	@Length(min = 1, max = 100)
	private String clientSecret;

	@Schema(description = "回调地址")
	@NotBlank(message = "回调地址不能为空")
	@Length(min = 1, max = 255)
	private String redirectUri;

	@Schema(description = "支付宝公钥")
	@Length(min = 1, max = 100)
	private String alipayPublicKey;

	@Schema(description = "unionid")
	@Length(min = 1, max = 1)
	private Boolean unionId;

	@Schema(description = "Stack Overflow Key")
	@Length(min = 1, max = 100)
	private String stackOverflowKey;

	@Schema(description = "企业微信网页应用ID")
	@Length(min = 1, max = 100)
	private String agentId;

	@Schema(description = "企业微信用户类型")
	@Length(min = 1, max = 100)
	private String userType;

	@Schema(description = "DomainPrefix")
	@Length(min = 1, max = 255)
	private String domainPrefix;

	@Schema(description = "忽略校验code state")
	@Length(min = 1, max = 1)
	private Boolean ignoreCheckState;

	@Schema(description = "自定义授权scope")
	@Length(min = 1, max = 100)
	private String scopes;

	@Schema(description = "设备ID")
	@Length(min = 1, max = 100)
	private String deviceId;

	@Schema(description = "客户端操作系统类型")
	@Min(-2147483648L)
	@Max(2147483647L)
	@Length(min = 1, max = 10)
	private Integer clientOsType;

	@Schema(description = "客户端包名")
	@Length(min = 1, max = 100)
	private String packId;

	@Schema(description = "开启PKC模式")
	@Length(min = 1, max = 1)
	private Boolean pkce;

	@Schema(description = "Okta授权服务器的 ID")
	@Length(min = 1, max = 255)
	private String authServerId;

	@Schema(description = "忽略校验RedirectUri")
	@Length(min = 1, max = 1)
	private Boolean ignoreCheckRedirectUri;

	@Schema(description = "Http代理类型")
	@Length(min = 1, max = 10)
	private String proxyType;

	@Schema(description = "Http代理Host")
	@Length(min = 1, max = 100)
	private String proxyHostName;

	@Schema(description = "Http代理Port")
	@Min(-2147483648L)
	@Max(2147483647L)
	@Length(min = 1, max = 10)
	private Integer proxyPort;

	@Schema(description = "状态")
	@Min(-2147483648L)
	@Max(2147483647L)
	@Length(min = 1, max = 3)
	private Integer status;

	@Schema(description = "备注")
	@Length(min = 1, max = 255)
	private String remark;
}
