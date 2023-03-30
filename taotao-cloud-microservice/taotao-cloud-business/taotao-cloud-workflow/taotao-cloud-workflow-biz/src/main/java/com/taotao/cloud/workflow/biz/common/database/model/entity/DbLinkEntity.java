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

package com.taotao.cloud.workflow.biz.common.database.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.cloud.workflow.biz.common.database.util.DataSourceUtil;
import java.util.Date;
import lombok.Data;

/** 数据连接 */
@Data
@TableName("base_dblink")
public class DbLinkEntity extends DataSourceUtil implements DataSourceMod {
    /** 连接主键 */
    @TableId("F_ID")
    private String id;

    /** 连接名称 */
    @TableField("F_FULLNAME")
    private String fullName;

    /** 描述 */
    @TableField("F_DESCRIPTION")
    private String description;

    /** 排序码 */
    @TableField("F_SORTCODE")
    private Long sortCode;

    /** 有效标志 */
    @TableField("F_ENABLEDMARK")
    private Integer enabledMark;

    /** 创建时间 */
    @TableField(value = "F_CREATORTIME", fill = FieldFill.INSERT)
    private Date creatorTime;

    /** 创建用户 */
    @TableField(value = "F_CREATORUSERID", fill = FieldFill.INSERT)
    private String creatorUserId;

    /** 修改时间 */
    @TableField(value = "F_LASTMODIFYTIME", fill = FieldFill.UPDATE)
    private Date lastModifyTime;

    /** 修改用户 */
    @TableField(value = "F_LASTMODIFYUSERID", fill = FieldFill.UPDATE)
    private String lastModifyUserId;

    /** 删除标志 */
    @TableField("F_DELETEMARK")
    private Integer deleteMark;

    /** 删除时间 */
    @TableField("F_DELETETIME")
    private Date deleteTime;

    /** 删除用户 */
    @TableField("F_DELETEUSERID")
    private String deleteUserId;

    @Override
    public DataSourceDTO convertDTO() {
        return convertDTO(null);
    }

    @Override
    public DataSourceDTO convertDTO(String dbName) {
        try {
            return convertDTO(dbName, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
