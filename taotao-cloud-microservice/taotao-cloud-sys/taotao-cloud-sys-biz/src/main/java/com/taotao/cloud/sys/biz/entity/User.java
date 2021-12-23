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
package com.taotao.cloud.sys.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.cloud.common.enums.SexTypeEnum;
import com.taotao.cloud.web.base.entity.BaseSuperEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统用户表
 *
 * @author shuigedeng
 * @version 2021.10
 * @since 2021-10-09 21:05:21
 */
@Entity
@Table(name = User.TABLE_NAME)
@TableName(User.TABLE_NAME)
@org.hibernate.annotations.Table(appliesTo = User.TABLE_NAME, comment = "用户表")
public class User extends BaseSuperEntity<User,Long> {

	public static final String TABLE_NAME = "tt_sys_user";

	/**
	 * 账号
	 */
	@Column(name = "account", nullable = false, columnDefinition = "varchar(255) not null comment '账号'")
	private String account;

	/**
	 * 昵称
	 */
	@Column(name = "nickname", nullable = false, columnDefinition = "varchar(255) not null comment '昵称'")
	private String nickname;

	/**
	 * 姓名
	 */
	@Column(name = "username", nullable = false, columnDefinition = "varchar(255) not null comment '真实用户名'")
	private String username;

	/**
	 * 密码
	 */
	@Column(name = "password", nullable = false, columnDefinition = "varchar(255) not null comment '密码'")
	private String password;

	/**
	 * 手机号
	 */
	@Column(name = "mobile", unique = true, nullable = false, columnDefinition = "varchar(11) not null comment '手机号'")
	private String mobile;

	/**
	 * 电话号码
	 */
	@Column(name = "phone", columnDefinition = "varchar(11) comment '电话号码'")
	private String phone;

	/**
	 * 性别 1男 2女 0未知
	 *
	 * @see SexTypeEnum
	 */
	@Column(name = "sex", nullable = false, columnDefinition = "int not null default 0 comment '性别 1男 2女 0未知'")
	private Integer sex = 0;

	/**
	 * 邮箱
	 */
	@Column(name = "email", columnDefinition = "varchar(50) not null comment '邮箱'")
	private String email;

	/**
	 * 生日
	 */
	@Column(name = "birthday", columnDefinition = "varchar(50) not null comment '生日'")
	private String birthday;

	/**
	 * 部门ID
	 */
	@Column(name = "dept_id", columnDefinition = "bigint not null comment '部门ID'")
	private Long deptId;

	/**
	 * 岗位ID
	 */
	@Column(name = "job_id", columnDefinition = "bigint not null comment '岗位ID'")
	private Long jobId;

	/**
	 * 头像
	 */
	@Column(name = "avatar", columnDefinition = "varchar(255) comment '头像'")
	private String avatar;

	/**
	 * 状态 1-启用，2-禁用
	 */
	@Column(name = "status", nullable = false, columnDefinition = "int NOT NULL DEFAULT 1 comment '状态 1-启用，2-禁用'")
	private Integer status;

	/**
	 * 租户id
	 */
	@Column(name = "tenant_id", unique = true, columnDefinition = "varchar(32) COMMENT '租户id'")
	private String tenantId;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}