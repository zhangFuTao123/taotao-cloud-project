/*
 * COPYRIGHT (C) 2022 Art AUTHORS(fxzcloud@gmail.com). ALL RIGHTS RESERVED.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.taotao.cloud.sys.biz.controller.business.manager;

import com.taotao.cloud.common.model.PageResult;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.sys.api.model.dto.app.AppDTO;
import com.taotao.cloud.sys.api.model.dto.app.AppPageDTO;
import com.taotao.cloud.sys.biz.service.business.AppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统应用表
 *
 * @author fxz
 * @date 2022-09-12
 */
@Tag(name = "应用管理")
@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class AppController {

	private final AppService appService;

	/**
	 * 分页
	 */
	@Operation(summary = "分页")
	@GetMapping(value = "/page")
	public Result<PageResult<AppDTO>> pageSysApp(AppPageDTO appPageDTO) {
		//Validator.validateParam(appPageDTO, ValidationGroups.query.class);
		return Result.success(
			PageResult.convertMybatisPage(appService.pageApp(appPageDTO), AppDTO.class));
	}

	/**
	 * 添加
	 */
	@Operation(summary = "添加")
	@PostMapping(value = "/add")
	public Result<Boolean> add(@RequestBody AppDTO appDTO) {
		//ValidationUtil.validateParam(appDTO, ValidationGroups.add.class);
		return Result.success(appService.addApp(appDTO));
	}

	/**
	 * 修改
	 */
	@Operation(summary = "修改")
	@PostMapping(value = "/update")
	public Result<Boolean> update(@RequestBody AppDTO appDTO) {
		//ValidationUtil.validateParam(appDTO, ValidationGroups.update.class);
		return Result.success(appService.updateApp(appDTO));
	}

	/**
	 * 删除
	 */
	@Operation(summary = "删除")
	@DeleteMapping(value = "/delete")
	public Result<Boolean> delete(Long id) {
		return Result.success(appService.deleteApp(id));
	}

	/**
	 * 获取单条
	 */
	@Operation(summary = "获取单条")
	@GetMapping(value = "/findById")
	public Result<AppDTO> findById(Long id) {
		return Result.success(appService.findById(id));
	}

	/**
	 * 获取全部
	 */
	@Operation(summary = "获取全部")
	@GetMapping(value = "/findAll")
	public Result<List<AppDTO>> findAll() {
		return Result.success(appService.findAll());
	}

}