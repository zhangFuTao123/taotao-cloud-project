/*
 * Copyright 2002-2021 the original author or authors.
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
package com.taotao.cloud.sys.biz.mapstruct;

import com.taotao.cloud.sys.api.bo.role.RoleBO;
import com.taotao.cloud.sys.api.dto.role.RoleQueryDTO;
import com.taotao.cloud.sys.biz.entity.Role;
import com.taotao.cloud.sys.api.vo.role.RoleQueryVO;
import com.taotao.cloud.sys.api.vo.user.UserQueryVO;
import java.util.List;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * UserMapper
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2021-10-15 17:46:37
 */
@Mapper(builder = @Builder(disableBuilder = true),
	unmappedSourcePolicy = ReportingPolicy.IGNORE,
	unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRoleMapStruct {

	IRoleMapStruct INSTANCE = Mappers.getMapper(IRoleMapStruct.class);

	List<RoleQueryVO> bosToVos(List<RoleBO> bos);
	RoleQueryVO boToVo(RoleBO bo);

	RoleBO roleToBo(Role role);
	List<RoleBO> rolesToBos(List<Role> roles);


	/**
	 * SysRole转RoleQueryVO
	 *
	 * @param SysRole sysRole
	 * @return {@link UserQueryVO }
	 * @author shuigedeng
	 * @since 2021-10-15 17:47:46
	 */
	RoleQueryVO sysUserToUserQueryVO(Role sysRole);

	/**
	 * list -> SysRole转RoleQueryVO
	 *
	 * @param roleList roleList
	 * @return {@link List&lt;com.taotao.cloud.sys.api.vo.user.UserQueryVO&gt; }
	 * @author shuigedeng
	 * @since 2021-10-15 17:52:04
	 */
	List<RoleQueryVO> sysUserToUserQueryVO(List<Role> roleList);

	/**
	 * copyUserDtoToSysUser
	 *
	 * @param roleQueryDTO roleQueryDTO
	 * @param sysRole      sysRole
	 * @author shuigedeng
	 * @since 2021-10-15 17:54:47
	 */
	void copyUserDtoToSysUser(RoleQueryDTO roleQueryDTO, @MappingTarget Role sysRole);
}