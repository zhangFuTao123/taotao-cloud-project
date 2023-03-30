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

package com.taotao.cloud.message.biz.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.cloud.web.base.entity.BaseSuperEntity;
import com.taotao.cloud.web.base.entity.JpaEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** 微信消息表 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = WechatMessage.TABLE_NAME)
@TableName(WechatMessage.TABLE_NAME)
@EntityListeners({JpaEntityListener.class})
@org.hibernate.annotations.Table(appliesTo = WechatMessage.TABLE_NAME, comment = "微信消息表")
public class WechatMessage extends BaseSuperEntity<WechatMessage, Long> {

    public static final String TABLE_NAME = "tt_wechat_message";

    /** 模版名称 */
    @Column(name = "name", columnDefinition = "varchar(255) not null default '' comment '模版名称'")
    private String name;

    /** 微信模版码 */
    @Column(name = "code", columnDefinition = "varchar(255) not null default '' comment '微信模版码'")
    private String code;

    /** 关键字 */
    @Column(name = "keywords", columnDefinition = "varchar(255) not null default '' comment '关键字'")
    private String keywords;

    /** 是否开启 */
    @Column(name = "enable", columnDefinition = "boolean not null default '' comment '是否开启'")
    private Boolean enable = true;

    /** 订单状态 */
    @Column(
            name = "order_status",
            columnDefinition = "varchar(255) not null default '' comment '订单状态'")
    private String orderStatus;

    /** 模版头部信息 */
    @Column(name = "first", columnDefinition = "varchar(255) not null default '' comment '模版头部信息'")
    private String first;

    /** 模版备注（位于最下方） */
    @Column(
            name = "remark",
            columnDefinition = "varchar(255) not null default '' comment '模版备注（位于最下方）'")
    private String remark;
}
