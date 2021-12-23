package com.taotao.cloud.member.biz.controller.manager;
 
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.cloud.member.api.vo.MemberPointsHistoryVO;
import com.taotao.cloud.member.biz.entity.MemberPointsHistory;
import com.taotao.cloud.member.biz.service.MemberPointsHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理端,会员积分历史接口
 *
 * 
 * @since 2020-02-25 14:10:16
 */
@RestController
@Api(tags = "管理端,会员积分历史接口")
@RequestMapping("/manager/member/memberPointsHistory")
public class MemberPointsHistoryManagerController {
    @Autowired
    private MemberPointsHistoryService memberPointsHistoryService;

    @ApiOperation(value = "分页获取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberId", value = "会员ID", required = true, paramType = "query"),
            @ApiImplicitParam(name = "memberName", value = "会员名称", required = true, paramType = "query")
    })
    @GetMapping(value = "/getByPage")
    public ResultMessage<IPage<MemberPointsHistory>> getByPage(PageVO page, String memberId, String memberName) {
        return ResultUtil.data(memberPointsHistoryService.MemberPointsHistoryList(page, memberId, memberName));
    }

    @ApiOperation(value = "获取会员积分VO")
    @ApiImplicitParam(name = "memberId", value = "会员ID", paramType = "query")
    @GetMapping(value = "/getMemberPointsHistoryVO")
    public ResultMessage<MemberPointsHistoryVO> getMemberPointsHistoryVO(String memberId) {
        return ResultUtil.data(memberPointsHistoryService.getMemberPointsHistoryVO(memberId));
    }


}