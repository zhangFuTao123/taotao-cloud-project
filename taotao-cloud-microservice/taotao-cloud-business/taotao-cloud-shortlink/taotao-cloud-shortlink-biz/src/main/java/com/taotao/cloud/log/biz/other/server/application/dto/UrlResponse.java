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

package com.taotao.cloud.log.biz.other.server.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UrlResponse {

    private String requestId;

    @ApiModelProperty("描述信息")
    private String Description;

    @ApiModelProperty("长域名地址")
    private String longUrl;

    @ApiModelProperty("短域名地址")
    private String shortUrl;

    @ApiModelProperty("错误信息, 0表示正常")
    private String errMsg = "0";

    public UrlResponse() {}
    ;

    public UrlResponse(String requestId, String description, String longUrl, String shortUrl) {
        this.requestId = requestId;
        this.Description = description;
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
    }
    ;
}
