package com.taotao.cloud.uc.api.dubbo;

import com.taotao.cloud.uc.api.vo.resource.ResourceQueryBO;

/**
 * 后台部门表服务接口
 *
 * @author shuigedeng
 * @since 2020-10-16 15:54:05
 * @since 1.0
 */
public interface IDubboResourceService {
	ResourceQueryBO queryAllId(Long id);
}