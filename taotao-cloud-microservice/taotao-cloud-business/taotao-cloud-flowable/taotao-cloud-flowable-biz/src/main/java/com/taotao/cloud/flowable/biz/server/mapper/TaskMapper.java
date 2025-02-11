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

package com.taotao.cloud.flowable.biz.server.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.laokou.flowable.client.dto.TaskDTO;
import org.laokou.flowable.client.vo.TaskVO;
import org.springframework.stereotype.Repository;

/**
 * @author laokou
 */
@Mapper
@Repository
public interface TaskMapper {

    /**
     * 获取审批人或处理人
     *
     * @param instanceId
     * @return
     */
    String getAssignee(@Param("instanceId") String instanceId);

    /**
     * 分页查询任务
     *
     * @param page
     * @param dto
     * @return
     */
    IPage<TaskVO> getTakePage(IPage<TaskVO> page, @Param("dto") TaskDTO dto);
}
