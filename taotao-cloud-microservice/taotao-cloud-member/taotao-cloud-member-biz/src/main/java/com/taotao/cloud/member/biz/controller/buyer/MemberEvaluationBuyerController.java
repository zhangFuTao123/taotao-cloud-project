package com.taotao.cloud.member.biz.controller.buyer;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.cloud.member.api.dto.EvaluationQueryParams;
import com.taotao.cloud.member.api.dto.MemberEvaluationDTO;
import com.taotao.cloud.member.api.vo.EvaluationNumberVO;
import com.taotao.cloud.member.api.vo.MemberEvaluationVO;
import com.taotao.cloud.member.biz.entity.MemberEvaluation;
import com.taotao.cloud.member.biz.service.MemberEvaluationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 买家端,会员商品评价接口
 *
 * 
 * @since 2020/11/16 10:08 下午
 */
@RestController
@Api(tags = "买家端,会员商品评价接口")
@RequestMapping("/buyer/memberEvaluation")
public class MemberEvaluationBuyerController {

    /**
     * 会员商品评价
     */
    @Autowired
    private MemberEvaluationService memberEvaluationService;

    @ApiOperation(value = "添加会员评价")
    @PostMapping
    public ResultMessage<MemberEvaluationDTO> save(@Valid MemberEvaluationDTO memberEvaluationDTO) {
        return ResultUtil.data(memberEvaluationService.addMemberEvaluation(memberEvaluationDTO));
    }

    @ApiOperation(value = "查看会员评价详情")
    @ApiImplicitParam(name = "id", value = "评价ID", required = true, paramType = "path")
    @GetMapping(value = "/get/{id}")
    public ResultMessage<MemberEvaluationVO> save(@NotNull(message = "评价ID不能为空") @PathVariable("id") String id) {
        return ResultUtil.data(memberEvaluationService.queryById(id));

    }

    @ApiOperation(value = "查看当前会员评价列表")
    @GetMapping
    public ResultMessage<IPage<MemberEvaluation>> queryMineEvaluation(
	    EvaluationQueryParams evaluationQueryParams) {
        //设置当前登录会员
        evaluationQueryParams.setMemberId(UserContext.getCurrentUser().getId());
        return ResultUtil.data(memberEvaluationService.managerQuery(evaluationQueryParams));
    }

    @ApiOperation(value = "查看某一个商品的评价列表")
    @ApiImplicitParam(name = "goodsId", value = "商品ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/{goodsId}/goodsEvaluation")
    public ResultMessage<IPage<MemberEvaluation>> queryGoodsEvaluation(EvaluationQueryParams evaluationQueryParams,
                                                                       @NotNull @PathVariable("goodsId") String goodsId) {
        //设置查询查询商品
        evaluationQueryParams.setGoodsId(goodsId);
        evaluationQueryParams.setStatus(SwitchEnum.OPEN.name());
        return ResultUtil.data(memberEvaluationService.managerQuery(evaluationQueryParams));
    }

    @ApiOperation(value = "查看某一个商品的评价数量")
    @ApiImplicitParam(name = "goodsId", value = "商品ID", required = true, dataType = "Long", paramType = "path")
    @GetMapping(value = "/{goodsId}/evaluationNumber")
    public ResultMessage<EvaluationNumberVO> queryEvaluationNumber(@NotNull @PathVariable("goodsId") String goodsId) {
        return ResultUtil.data(memberEvaluationService.getEvaluationNumber(goodsId));
    }
}