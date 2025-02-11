/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
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

package com.taotao.cloud.wechat.biz.wechat.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 微信公众平台配置
 *
 * @author xxm
 * @date 2022/7/15
 */
@Getter
@Setter
@ConfigurationProperties("bootx.starter.third.wechat")
public class WeChatProperties {

    /** AppKey */
    private String appId;

    /** AppSecret */
    private String appSecret;

    /** token */
    private String token;

    /** 消息加解密密钥 */
    private String encodingAesKey;
}
