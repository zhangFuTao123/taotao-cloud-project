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

package com.taotao.cloud.sys.biz.service.business.impl;

import com.taotao.cloud.cache.redis.repository.RedisRepository;
import com.taotao.cloud.common.constant.RedisConstant;
import com.taotao.cloud.sys.biz.mapper.ISensitiveWordMapper;
import com.taotao.cloud.sys.biz.model.entity.sensitive.SensitiveWord;
import com.taotao.cloud.sys.biz.repository.cls.SensitiveWordRepository;
import com.taotao.cloud.sys.biz.repository.inf.ISensitiveWordRepository;
import com.taotao.cloud.sys.biz.service.business.ISensitiveWordService;
import com.taotao.cloud.web.base.service.impl.BaseSuperServiceImpl;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/** 敏感词业务层实现 */
@Service
@AllArgsConstructor
public class SensitiveWordServiceImpl
        extends BaseSuperServiceImpl<
                ISensitiveWordMapper, SensitiveWord, SensitiveWordRepository, ISensitiveWordRepository, Long>
        implements ISensitiveWordService {

    private final RedisRepository redisRepository;

    @Override
    public void resetCache() {
        List<SensitiveWord> sensitiveWordsList = this.list();
        if (sensitiveWordsList == null || sensitiveWordsList.isEmpty()) {
            return;
        }
        List<String> sensitiveWords =
                sensitiveWordsList.stream().map(SensitiveWord::getSensitiveWord).collect(Collectors.toList());

        redisRepository.set(RedisConstant.SENSITIVE_WORDS_KEY, sensitiveWords);
    }
}
