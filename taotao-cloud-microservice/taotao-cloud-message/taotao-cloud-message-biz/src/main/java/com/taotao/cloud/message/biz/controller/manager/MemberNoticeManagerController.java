package com.taotao.cloud.message.biz.controller.manager;

import cn.hutool.core.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.cloud.common.constant.CommonConstant;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.logger.annotation.RequestLogger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.apache.shardingsphere.distsql.parser.autogen.CommonDistSQLStatementParser.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理端,会员站内信管理接口
 */
@Validated
@RestController
@Tag(name = "平台管理端-会员站内信管理API", description = "平台管理端-会员站内信管理API")
@RequestMapping("/message/manager/memberNotice")
public class MemberNoticeManagerController {

	@Autowired
	private MemberNoticeService memberNoticeService;

	@Operation(summary = "获取详情", description = "获取详情", method = CommonConstant.GET)
	@RequestLogger(description = "获取详情")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/{id}")
	public Result<MemberNotice> get(@PathVariable String id) {
		MemberNotice memberNotice = memberNoticeService.getById(id);
		return Result.success(memberNotice);
	}

	@Operation(summary = "分页获取站内信", description = "分页获取站内信", method = CommonConstant.GET)
	@RequestLogger(description = "分页获取站内信")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/page")
	public Result<IPage<MemberNotice>> getByPage(PageVO page) {
		IPage<MemberNotice> data = memberNoticeService.page(PageUtil.initPage(page));
		return Result.success(data);
	}

	@Operation(summary = "阅读消息", description = "阅读消息", method = CommonConstant.POST)
	@RequestLogger(description = "阅读消息")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/read/{ids}")
	public Result<Object> read(@PathVariable List ids) {
		UpdateWrapper updateWrapper = new UpdateWrapper();
		updateWrapper.in("id", ids);
		updateWrapper.set("is_read", true);
		memberNoticeService.update(updateWrapper);
		return Result.success();
	}

	@Operation(summary = "阅读全部", description = "阅读全部", method = CommonConstant.POST)
	@RequestLogger(description = "阅读全部")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping("/read/all")
	public Result<Object> readAll() {
		UpdateWrapper updateWrapper = new UpdateWrapper();
		updateWrapper.in("member_id", UserContext.getCurrentUser().getId());
		updateWrapper.set("is_read", true);
		memberNoticeService.update(updateWrapper);
		return RResult.success();
	}

	@Operation(summary = "批量删除", description = "批量删除", method = CommonConstant.DELETE)
	@RequestLogger(description = "批量删除")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@DeleteMapping(value = "/{ids}")
	public Result<Object> delAllByIds(@PathVariable List ids) {
		memberNoticeService.removeByIds(ids);
		return Result.success();
	}

	@Operation(summary = "删除所有", description = "删除所有", method = CommonConstant.DELETE)
	@RequestLogger(description = "删除所有")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@DeleteMapping(value = "/all")
	public Result<Object> deleteAll() {
		QueryWrapper queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("member_id", UserContext.getCurrentUser().getId());
		memberNoticeService.remove(queryWrapper);
		return Result.success();
	}

}