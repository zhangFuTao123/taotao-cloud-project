package com.taotao.cloud.promotion.biz.controller.manager;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.cloud.promotion.api.enums.PromotionsStatusEnum;
import com.taotao.cloud.promotion.api.vo.PromotionGoodsSearchParams;
import com.taotao.cloud.promotion.biz.entity.PromotionGoods;
import com.taotao.cloud.promotion.biz.service.PromotionGoodsService;
import com.taotao.cloud.promotion.biz.service.PromotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 管理端,促销接口
 *
 * 
 * @since 2021/2/2
 **/
@RestController
@Api(tags = "管理端,促销接口")
@RequestMapping("/manager/promotion")
public class PromotionManagerController {

    @Autowired
    private PromotionService promotionService;
    @Autowired
    private PromotionGoodsService promotionGoodsService;

    @GetMapping("/current")
    @ApiOperation(value = "获取当前进行中的促销活动")
    public ResultMessage<Map<String, Object>> getCurrentPromotion() {
        Map<String, Object> currentPromotion = promotionService.getCurrentPromotion();
        return ResultUtil.data(currentPromotion);
    }

    @GetMapping("/{promotionId}/goods")
    @ApiOperation(value = "获取当前进行中的促销活动商品")
    public ResultMessage<IPage<PromotionGoods>> getPromotionGoods(@PathVariable String promotionId, String promotionType, PageVO pageVO) {
        PromotionGoodsSearchParams searchParams = new PromotionGoodsSearchParams();
        searchParams.setPromotionId(promotionId);
        searchParams.setPromotionType(promotionType);
        searchParams.setPromotionStatus(PromotionsStatusEnum.START.name());
        IPage<PromotionGoods> promotionGoods = promotionGoodsService.pageFindAll(searchParams, pageVO);
        return ResultUtil.data(promotionGoods);
    }


}