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

package com.taotao.cloud.wechat.biz.wechat.core.user.convert;

import cn.bootx.starter.wechat.core.user.entity.WechatFans;
import cn.bootx.starter.wechat.dto.user.WechatFansDto;
import cn.bootx.starter.wechat.param.user.WechatFansParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 微信公众号粉丝
 *
 * @author xxm
 * @date 2022-07-16
 */
@Mapper
public interface WechatFansConvert {
    WechatFansConvert CONVERT = Mappers.getMapper(WechatFansConvert.class);

    WechatFans convert(WechatFansParam in);

    WechatFansDto convert(WechatFans in);
}
