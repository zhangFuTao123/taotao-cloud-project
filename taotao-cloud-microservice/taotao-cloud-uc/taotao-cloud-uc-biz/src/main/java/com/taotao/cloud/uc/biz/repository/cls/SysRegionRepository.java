package com.taotao.cloud.uc.biz.repository.cls;

import com.taotao.cloud.uc.biz.entity.SysRegion;
import com.taotao.cloud.web.base.repository.BaseSuperRepository;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

/**
 * 岗位表Repository
 *
 * @author shuigedeng
 * @since 2020/9/29 18:02
 * @version 1.0.0
 */
@Repository
public class SysRegionRepository extends BaseSuperRepository<SysRegion, Long> {
    public SysRegionRepository(EntityManager em) {
        super(SysRegion.class, em);
    }
}