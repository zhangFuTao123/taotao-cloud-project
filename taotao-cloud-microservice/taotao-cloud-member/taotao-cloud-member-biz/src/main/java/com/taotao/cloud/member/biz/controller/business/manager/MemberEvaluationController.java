package com.taotao.cloud.member.biz.controller.business.manager;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.cloud.common.model.PageResult;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.member.api.model.query.EvaluationPageQuery;
import com.taotao.cloud.member.api.model.vo.MemberEvaluationListVO;
import com.taotao.cloud.member.api.model.vo.MemberEvaluationVO;
import com.taotao.cloud.member.biz.model.convert.MemberEvaluationConvert;
import com.taotao.cloud.member.biz.model.entity.MemberEvaluation;
import com.taotao.cloud.member.biz.service.business.IMemberEvaluationService;
import com.taotao.cloud.web.request.annotation.RequestLogger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理端,会员商品评价API
 *
 * @since 2020-02-25 14:10:16
 */
@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/member/manager/membe/evaluation")
@Tag(name = "管理端-会员商品评价管理API", description = "管理端-会员商品评价管理API")
public class MemberEvaluationController {

	private final IMemberEvaluationService memberEvaluationService;

	@Operation(summary = "通过id获取评论", description = "通过id获取评论")
	@RequestLogger
	@PreAuthorize("@el.check('admin','timing:list')")
	@GetMapping(value = "/{id}")
	public Result<MemberEvaluationVO> get(@PathVariable Long id) {
		MemberEvaluation memberEvaluation = memberEvaluationService.queryById(id);
		return Result.success(MemberEvaluationConvert.INSTANCE.convert(memberEvaluation));
	}

	@Operation(summary = "获取评价分页", description = "获取评价分页")
	@RequestLogger
	@PreAuthorize("@el.check('admin','timing:list')")
	public Result<PageResult<MemberEvaluationListVO>> queryPage(EvaluationPageQuery evaluationPageQuery) {
		IPage<MemberEvaluation> memberEvaluationPage = memberEvaluationService.queryPage(
				evaluationPageQuery);
		return Result.success(
				PageResult.convertMybatisPage(memberEvaluationPage, MemberEvaluationListVO.class));
	}

	@Operation(summary = "修改评价状态", description = "修改评价状态")
	@RequestLogger
	@PreAuthorize("@el.check('admin','timing:list')")
	@GetMapping(value = "/status/{id}")
	public Result<Boolean> updateStatus(@PathVariable Long id,
			@Parameter(description = "显示状态,OPEN 正常 ,CLOSE 关闭", required = true) @NotNull String status) {
		return Result.success(memberEvaluationService.updateStatus(id, status));
	}

	@Operation(summary = "删除评论", description = "删除评论")
	@RequestLogger
	@PreAuthorize("@el.check('admin','timing:list')")
	@DeleteMapping(value = "/{id}")
	public Result<Boolean> delete(@PathVariable Long id) {
		return Result.success(memberEvaluationService.delete(id));
	}

}
