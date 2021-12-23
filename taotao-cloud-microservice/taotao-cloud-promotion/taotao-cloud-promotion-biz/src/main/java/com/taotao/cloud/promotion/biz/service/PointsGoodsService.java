package com.taotao.cloud.promotion.biz.service;


import com.taotao.cloud.promotion.api.vo.PointsGoodsVO;
import com.taotao.cloud.promotion.biz.entity.PointsGoods;
import java.util.List;

/**
 * 积分商品业务层
 *
 * 
 * @since 2020/11/18 9:45 上午
 **/
public interface PointsGoodsService extends AbstractPromotionsService<PointsGoods> {

    /**
     * 批量保存库存商品
     *
     * @param promotionsList 积分商品列表
     * @return 是否保存成功
     */
    boolean savePointsGoodsBatch(List<PointsGoods> promotionsList);

    /**
     * 根据ID获取积分详情
     *
     * @param id 积分商品id
     * @return 积分详情
     */
    PointsGoodsVO getPointsGoodsDetail(String id);

    /**
     * 根据ID获取积分详情
     *
     * @param skuId 商品SkuId
     * @return 积分详情
     */
    PointsGoodsVO getPointsGoodsDetailBySkuId(String skuId);

}