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

package com.taotao.cloud.goods.biz.service.business.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.taotao.cloud.goods.biz.mapper.ICategorySpecificationMapper;
import com.taotao.cloud.goods.biz.model.entity.CategorySpecification;
import com.taotao.cloud.goods.biz.model.entity.Specification;
import com.taotao.cloud.goods.biz.repository.cls.CategorySpecificationRepository;
import com.taotao.cloud.goods.biz.repository.inf.ICategorySpecificationRepository;
import com.taotao.cloud.goods.biz.service.business.ICategorySpecificationService;
import com.taotao.cloud.web.base.service.impl.BaseSuperServiceImpl;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 商品分类规格业务层实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:02:15
 */
@AllArgsConstructor
@Service
public class CategorySpecificationServiceImpl
        extends BaseSuperServiceImpl<
                ICategorySpecificationMapper,
                CategorySpecification,
                CategorySpecificationRepository,
                ICategorySpecificationRepository,
                Long>
        implements ICategorySpecificationService {

    @Override
    public List<Specification> getCategorySpecList(Long categoryId) {
        return this.baseMapper.getCategorySpecList(categoryId);
    }

    @Override
    public Boolean deleteByCategoryId(Long categoryId) {
        return this.baseMapper.delete(new LambdaQueryWrapper<CategorySpecification>()
                        .eq(CategorySpecification::getCategoryId, categoryId))
                > 0;
    }
}
