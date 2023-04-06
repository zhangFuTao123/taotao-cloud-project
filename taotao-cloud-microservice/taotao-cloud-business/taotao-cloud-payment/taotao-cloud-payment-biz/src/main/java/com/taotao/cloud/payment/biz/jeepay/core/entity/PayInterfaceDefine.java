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

package com.taotao.cloud.payment.biz.jeepay.core.entity;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.taotao.cloud.payment.biz.jeepay.core.model.BaseModel;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 支付接口定义表
 *
 * @author [mybatis plus generator]
 * @since 2021-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "t_pay_interface_define", autoResultMap = true)
public class PayInterfaceDefine extends BaseModel implements Serializable {

    public static final LambdaQueryWrapper<PayInterfaceDefine> gw() {
        return new LambdaQueryWrapper<>();
    }

    private static final long serialVersionUID = 1L;

    /** 接口代码 全小写 wxpay alipay */
    @TableId
    private String ifCode;

    /** 接口名称 */
    private String ifName;

    /** 是否支持普通商户模式: 0-不支持, 1-支持 */
    private Byte isMchMode;

    /** 是否支持服务商子商户模式: 0-不支持, 1-支持 */
    private Byte isIsvMode;

    /** 支付参数配置页面类型:1-JSON渲染,2-自定义 */
    private Byte configPageType;

    /** ISV接口配置定义描述,json字符串 */
    private String isvParams;

    /** 特约商户接口配置定义描述,json字符串 */
    private String isvsubMchParams;

    /** 普通商户接口配置定义描述,json字符串 */
    private String normalMchParams;

    /** 支持的支付方式 ["wxpay_jsapi", "wxpay_bar"] */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private JSONArray wayCodes;

    /** 页面展示：卡片-图标 */
    private String icon;

    /** 页面展示：卡片-背景色 */
    private String bgColor;

    /** 状态: 0-停用, 1-启用 */
    private Byte state;

    /** 备注 */
    private String remark;

    /** 创建时间 */
    private Date createdAt;

    /** 更新时间 */
    private Date updatedAt;
}
