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

package com.taotao.cloud.payment.biz.jeepay.mch.ctrl.transfer;

import com.alibaba.fastjson.JSONObject;
import com.taotao.cloud.payment.biz.jeepay.mch.ctrl.CommonCtrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 获取用户ID - 回调函数
 *
 * @author terrfly
 * @site https://www.jeequan.com
 * @date 2021/8/13 17:54
 */
@Controller
@RequestMapping("/api/anon/channelUserIdCallback")
public class ChannelUserIdNotifyController extends CommonCtrl {

    @RequestMapping("")
    public String channelUserIdCallback() {

        try {
            // 请求参数
            JSONObject params = getReqParamJSON();

            String extParam = params.getString("extParam");
            String channelUserId = params.getString("channelUserId");
            String appId = params.getString("appId");

            // 推送到前端
            WsChannelUserIdServer.sendMsgByAppAndCid(appId, extParam, channelUserId);

        } catch (Exception e) {
            request.setAttribute("errMsg", e.getMessage());
        }

        return "channelUser/getChannelUserIdPage";
    }
}
