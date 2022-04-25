package com.taotao.cloud.message.biz.austin.handler.deduplication.limit;

import com.taotao.cloud.message.biz.austin.common.domain.TaskInfo;
import com.taotao.cloud.message.biz.austin.handler.deduplication.DeduplicationParam;
import com.taotao.cloud.message.biz.austin.handler.deduplication.service.AbstractDeduplicationService;

import java.util.Set;

public interface LimitService {


    /**
     * 去重限制
     * @param service 去重器对象
     * @param taskInfo
     * @param param 去重参数
     * @return 返回不符合条件的手机号码
     */
    Set<String> limitFilter(AbstractDeduplicationService service, TaskInfo taskInfo, DeduplicationParam param);

}