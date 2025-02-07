/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
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

package com.taotao.cloud.goods.biz.service.business;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taotao.cloud.common.enums.CachePrefix;
import com.taotao.cloud.goods.api.model.dto.GoodsOperationDTO;
import com.taotao.cloud.goods.api.model.dto.GoodsSkuDTO;
import com.taotao.cloud.goods.api.model.dto.GoodsSkuStockDTO;
import com.taotao.cloud.goods.api.model.page.GoodsPageQuery;
import com.taotao.cloud.goods.api.model.vo.GoodsSkuSpecGalleryVO;
import com.taotao.cloud.goods.api.model.vo.GoodsSkuVO;
import com.taotao.cloud.goods.biz.model.entity.Goods;
import com.taotao.cloud.goods.biz.model.entity.GoodsSku;
import com.taotao.cloud.web.base.service.BaseSuperService;
import java.util.List;
import java.util.Map;

/**
 * 商品sku业务层
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:00:44
 */
public interface IGoodsSkuService extends BaseSuperService<GoodsSku, Long> {
	/**
	 * 获取商品SKU缓存ID
	 *
	 * @param id SkuId
	 * @return 商品SKU缓存ID
	 */
	static String getCacheKeys(String id) {
		return CachePrefix.GOODS_SKU.getPrefix() + id;
	}

	/**
	 * 获取商品SKU库存缓存ID
	 *
	 * @param id SkuId
	 * @return 商品SKU缓存ID
	 */
	static String getStockCacheKey(String id) {
		return CachePrefix.SKU_STOCK.getPrefix() + id;
	}

	/**
	 * 添加商品sku
	 *
	 * @param goods             商品信息
	 * @param goodsOperationDTO 商品操作信息
	 */
	void add(Goods goods, GoodsOperationDTO goodsOperationDTO);

	/**
	 * 更新商品sku
	 *
	 * @param goods             商品信息
	 * @param goodsOperationDTO 商品操作信息
	 */
	void update(Goods goods, GoodsOperationDTO goodsOperationDTO);

	/**
	 * 更新商品sku
	 *
	 * @param goodsSku sku信息
	 */
	void update(GoodsSku goodsSku);

	/**
	 * 清除sku缓存
	 *
	 * @param skuId skuid
	 */
	void clearCache(String skuId);

	/**
	 * 从redis缓存中获取商品SKU信息
	 *
	 * @param id SkuId
	 * @return 商品SKU信息
	 */
	GoodsSku getGoodsSkuByIdFromCache(String id);

	/**
	 * 从缓存中获取可参与促销商品
	 *
	 * @param skuId skuid
	 * @return 商品详情
	 */
	GoodsSku getCanPromotionGoodsSkuByIdFromCache(String skuId);

	/**
	 * 获取商品sku详情
	 *
	 * @param goodsId 商品ID
	 * @param skuId   skuID
	 * @return 商品sku详情
	 */
	Map<String, Object> getGoodsSkuDetail(String goodsId, String skuId);

	/**
	 * 批量从redis中获取商品SKU信息
	 *
	 * @param ids SkuId集合
	 * @return 商品SKU信息集合
	 */
	List<GoodsSku> getGoodsSkuByIdFromCache(List<String> ids);

	/**
	 * 获取goodsId下所有的goodsSku
	 *
	 * @param goodsId 商品id
	 * @return goodsSku列表
	 */
	List<GoodsSkuVO> getGoodsListByGoodsId(String goodsId);

	/**
	 * 获取goodsId下所有的goodsSku
	 *
	 * @param goodsId 商品id
	 * @return goodsSku列表
	 */
	List<GoodsSku> getGoodsSkuListByGoodsId(String goodsId);

	/**
	 * 根据goodsSku组装goodsSkuVO
	 *
	 * @param list 商品id
	 * @return goodsSku列表
	 */
	List<GoodsSkuVO> getGoodsSkuVOList(List<GoodsSku> list);

	/**
	 * 根据goodsSku组装goodsSkuVO
	 *
	 * @param goodsSku 商品规格
	 * @return goodsSku列表
	 */
	GoodsSkuVO getGoodsSkuVO(GoodsSku goodsSku);

	/**
	 * 分页查询商品sku信息
	 *
	 * @param searchParams 查询参数
	 * @return 商品sku信息
	 */
	IPage<GoodsSku> getGoodsSkuByPage(GoodsPageQuery searchParams);


	/**
	 * 分页查询商品sku信息
	 *
	 * @param page         分页参数
	 * @param queryWrapper 查询参数
	 * @return 商品sku信息
	 */
	IPage<GoodsSkuDTO> getGoodsSkuDTOByPage(Page<GoodsSkuDTO> page, Wrapper<GoodsSkuDTO> queryWrapper);

	/**
	 * 列表查询商品sku信息
	 *
	 * @param searchParams 查询参数
	 * @return 商品sku信息
	 */
	List<GoodsSku> getGoodsSkuByList(GoodsPageQuery searchParams);

	/**
	 * 更新商品sku状态
	 *
	 * @param goods 商品信息(Id,MarketEnable/AuthFlag)
	 */
	void updateGoodsSkuStatus(Goods goods);

	/**
	 * 更新商品sku状态根据店铺id
	 *
	 * @param storeId      店铺id
	 * @param marketEnable 市场启用状态
	 * @param authFlag     审核状态
	 */
	void updateGoodsSkuStatusByStoreId(String storeId, String marketEnable, String authFlag);

	/**
	 * 更新SKU库存
	 *
	 * @param goodsSkuStockDTOS sku库存修改实体
	 */
	void updateStocks(List<GoodsSkuStockDTO> goodsSkuStockDTOS);

	/**
	 * 更新SKU库存
	 *
	 * @param skuId    SKUId
	 * @param quantity 设置的库存数量
	 */
	void updateStock(String skuId, Integer quantity);

	/**
	 * 获取商品sku库存
	 *
	 * @param skuId 商品skuId
	 * @return 库存数量
	 */
	Integer getStock(String skuId);

	/**
	 * 修改商品库存字段
	 *
	 * @param goodsSkus
	 */
	void updateGoodsStuck(List<GoodsSku> goodsSkus);

	/**
	 * 更新SKU评价数量
	 *
	 * @param skuId SKUId
	 */
	void updateGoodsSkuCommentNum(String skuId);

	/**
	 * 根据商品id获取全部skuId的集合
	 *
	 * @param goodsId goodsId
	 * @return 全部skuId的集合
	 */
	List<String> getSkuIdsByGoodsId(String goodsId);

	/**
	 * 删除并且新增sku，即覆盖之前信息
	 *
	 * @param goodsSkus 商品sku集合
	 * @return
	 */
	boolean deleteAndInsertGoodsSkus(List<GoodsSku> goodsSkus);

	/**
	 * 统计sku总数
	 *
	 * @param storeId 店铺id
	 * @return sku总数
	 */
	Long countSkuNum(String storeId);

	/**
	 * 批量渲染商品sku
	 *
	 * @param goodsSkuList SKU基础数据列表
	 * @param goodsOperationDTO 商品操作信息
	 */
	void renderGoodsSkuList(List<GoodsSku> goodsSkuList, GoodsOperationDTO goodsOperationDTO);
}
