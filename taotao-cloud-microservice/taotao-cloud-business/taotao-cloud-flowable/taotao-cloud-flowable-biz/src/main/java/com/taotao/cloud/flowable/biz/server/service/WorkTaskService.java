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

package com.taotao.cloud.flowable.biz.server.service;

import java.io.IOException;
import org.laokou.flowable.client.dto.*;
import org.laokou.flowable.client.vo.AssigneeVO;
import org.laokou.flowable.client.vo.PageVO;
import org.laokou.flowable.client.vo.TaskVO;

/**
 * @author laokou
 */
public interface WorkTaskService {

    /**
     * 审批任务
     *
     * @param dto
     * @return
     */
    AssigneeVO auditTask(AuditDTO dto);

    /**
     * 处理任务
     *
     * @param dto
     * @return
     */
    AssigneeVO resolveTask(ResolveDTO dto);

    /**
     * 开始任务
     *
     * @param dto
     * @return
     */
    AssigneeVO startTask(ProcessDTO dto);

    /**
     * 任务分页
     *
     * @param dto
     * @return
     */
    PageVO<TaskVO> queryTaskPage(TaskDTO dto);

    /**
     * 任务流程图
     *
     * @param processInstanceId
     * @return
     * @throws IOException
     */
    String diagramTask(String processInstanceId) throws IOException;

    /**
     * 转办任务
     *
     * @param dto
     * @return
     */
    AssigneeVO transferTask(TransferDTO dto);

    /**
     * 委派任务
     *
     * @param dto
     * @return
     */
    AssigneeVO delegateTask(DelegateDTO dto);
}
