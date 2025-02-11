package com.taotao.cloud.payment.biz.daxpay.param.paymodel.wechat;

import cn.bootx.platform.daxpay.code.paymodel.WeChatPayCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 微信支付配置参数
 *
 * @author xxm
 * @date 2022/7/7
 */
@Data
@Accessors(chain = true)
@Schema(title = "微信支付配置参数")
public class WeChatPayConfigParam {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "微信商户号")
    private String mchId;

    @Schema(description = "微信应用appId")
    private String appId;

    /**
     * @see WeChatPayCode#API_V2
     */
//    @Schema(description = "api版本")
//    private String apiVersion;

    @Schema(description = "商户平台「API安全」中的 APIv2 密钥")
    private String apiKeyV2;

    @Schema(description = "商户平台「API安全」中的 APIv3 密钥")
    private String apiKeyV3;

    @Schema(description = "APPID对应的接口密码，用于获取接口调用凭证access_token时使用")
    private String appSecret;

    @Schema(description = "API 证书中的 p12 文件id")
    private Long p12;

    @Schema(description = "API 证书中的 cert.pem 证书")
    private String certPem;

    @Schema(description = "API 证书中的 key.pem 私钥")
    private String keyPem;

    @Schema(description = "应用域名，回调中会使用此参数")
    private String domain;

    @Schema(description = "服务器异步通知页面路径 通知url必须为直接可访问的url，不能携带参数。公网域名必须为https ")
    private String notifyUrl;

    @Schema(description = "页面跳转同步通知页面路径")
    private String returnUrl;

    @Schema(description = "是否沙箱环境")
    private boolean sandbox;

    @Schema(description = "超时时间(分钟)")
    private Integer expireTime;

    @Schema(description = "可用支付方式")
    private List<String> payWayList;

    @Schema(description = "是否启用")
    private Boolean activity;

    @Schema(description = "状态")
    private Integer state;

    @Schema(description = "备注")
    private String remark;

}
