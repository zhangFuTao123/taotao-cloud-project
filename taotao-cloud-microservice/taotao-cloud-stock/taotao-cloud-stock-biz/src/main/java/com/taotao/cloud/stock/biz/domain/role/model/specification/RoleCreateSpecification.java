package com.taotao.cloud.stock.biz.domain.role.model.specification;

import com.xtoon.cloud.common.core.domain.AbstractSpecification;
import com.xtoon.cloud.sys.domain.model.role.Role;
import com.xtoon.cloud.sys.domain.model.role.RoleRepository;

/**
 * 角色创建Specification
 *
 * @author haoxin
 * @date 2021-02-20
 **/
public class RoleCreateSpecification extends AbstractSpecification<Role> {

    private RoleRepository roleRepository;

    public RoleCreateSpecification(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean isSatisfiedBy(Role role) {
        if (role.getRoleCode() != null) {
            Role existRole = roleRepository.find(role.getRoleCode());
            if (existRole != null && !existRole.getRoleId().sameValueAs(role.getRoleId())) {
                throw new IllegalArgumentException("角色编码已存在");
            }
        }
        if (role.getRoleName() != null) {
            Role existRole = roleRepository.find(role.getRoleName());
            if (existRole != null && !existRole.getRoleId().sameValueAs(role.getRoleId())) {
                throw new IllegalArgumentException("角色名称已存在");
            }
        }
        if (role.getRoleCode().isTenantAdmin()) {
            throw new IllegalArgumentException("管理角色无法更新");
        }
        return true;
    }
}