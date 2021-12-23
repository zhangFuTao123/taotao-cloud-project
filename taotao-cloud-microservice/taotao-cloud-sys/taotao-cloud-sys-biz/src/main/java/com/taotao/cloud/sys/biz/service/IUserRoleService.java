package com.taotao.cloud.sys.biz.service;

import com.taotao.cloud.sys.biz.entity.UserRole;
import com.taotao.cloud.web.base.service.BaseSuperService;
import java.util.Set;

/**
 * 用户-角色服务类
 *
 * @since 2020/4/30 13:20
 */
public interface IUserRoleService extends
	BaseSuperService<UserRole, Long> {

	/**
	 * 添加用户-角色对应关系
	 *
	 * @param userId
	 * @param roleIds
	 * @return java.lang.Boolean
	 */
	Boolean saveUserRoles(Long userId, Set<Long> roleIds);
}