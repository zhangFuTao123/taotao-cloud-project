package com.taotao.cloud.goods.biz.controller.business.seller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.cloud.common.enums.ResultEnum;
import com.taotao.cloud.common.exception.BusinessException;
import com.taotao.cloud.common.model.PageResult;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.common.utils.common.SecurityUtils;
import com.taotao.cloud.goods.api.model.dto.DraftGoodsSkuParamsDTO;
import com.taotao.cloud.goods.api.model.page.DraftGoodsPageQuery;
import com.taotao.cloud.goods.api.model.vo.DraftGoodsSkuParamsVO;
import com.taotao.cloud.goods.api.model.vo.DraftGoodsVO;
import com.taotao.cloud.goods.biz.model.entity.DraftGoods;
import com.taotao.cloud.goods.biz.service.business.IDraftGoodsService;
import com.taotao.cloud.web.request.annotation.RequestLogger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 店铺端,草稿商品接口
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-14 22:05:35
 */
@AllArgsConstructor
@Validated
@RestController
@Tag(name = "店铺端-草稿商品API", description = "店铺端-草稿商品API")
@RequestMapping("/goods/seller/draft/goods")
public class DraftGoodsSellerController {

	/**
	 * 草稿商品服务
	 */
	private final IDraftGoodsService draftGoodsService;

	@Operation(summary = "分页获取草稿商品列表", description = "分页获取草稿商品列表")
	@RequestLogger("分页获取草稿商品列表")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/page")
	public Result<PageResult<DraftGoodsVO>> getDraftGoodsByPage(DraftGoodsPageQuery draftGoodsPageQuery) {
		Long storeId = SecurityUtils.getCurrentUser().getStoreId();
		draftGoodsPageQuery.setStoreId(storeId);
		IPage<DraftGoods> draftGoods = draftGoodsService.draftGoodsQueryPage(draftGoodsPageQuery);
		return Result.success(PageResult.convertMybatisPage(draftGoods, DraftGoodsVO.class));
	}

	@Operation(summary = "获取草稿商品", description = "获取草稿商品")
	@RequestLogger("获取草稿商品")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/{id}")
	public Result<DraftGoodsSkuParamsVO> getDraftGoods(@PathVariable Long id) {
		return Result.success(draftGoodsService.getDraftGoods(id));
	}

	@Operation(summary = "保存草稿商品", description = "保存草稿商品")
	@RequestLogger("保存草稿商品")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping
	public Result<Boolean> saveDraftGoods(@Validated @RequestBody DraftGoodsSkuParamsDTO draftGoodsSkuParamsDTO) {
		Long storeId = SecurityUtils.getCurrentUser().getStoreId();
		if (draftGoodsSkuParamsDTO.getStoreId() == null) {
			draftGoodsSkuParamsDTO.setStoreId(storeId);
		} else if (draftGoodsSkuParamsDTO.getStoreId() != null && !storeId.equals(
			draftGoodsSkuParamsDTO.getStoreId())) {
			throw new BusinessException(ResultEnum.USER_AUTHORITY_ERROR);
		}
		return Result.success(draftGoodsService.saveGoodsDraft(draftGoodsSkuParamsDTO));
	}

	@Operation(summary = "删除草稿商品", description = "删除草稿商品")
	@RequestLogger("删除草稿商品")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@DeleteMapping(value = "/{id}")
	public Result<Boolean> deleteDraftGoods(@PathVariable Long id) {
		draftGoodsService.getDraftGoods(id);
		return Result.success(draftGoodsService.deleteGoodsDraft(id));
	}

}