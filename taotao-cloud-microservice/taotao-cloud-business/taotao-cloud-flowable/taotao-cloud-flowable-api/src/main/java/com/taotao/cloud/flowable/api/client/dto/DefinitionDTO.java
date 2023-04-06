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

package com.taotao.cloud.flowable.api.client.dto;

import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * @author laokou
 */
@Data
public class DefinitionDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -818653141079850719L;

    @NotNull(message = "显示页码不为空")
    private Integer pageNum;

    @NotNull(message = "显示条数不为空")
    private Integer pageSize;

    @NotNull(message = "显示条数不为空")
    private String processName;
}
