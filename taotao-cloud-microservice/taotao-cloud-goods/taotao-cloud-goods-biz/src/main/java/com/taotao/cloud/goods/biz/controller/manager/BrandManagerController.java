package com.taotao.cloud.goods.biz.controller.manager;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taotao.cloud.common.constant.CommonConstant;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.goods.api.dto.BrandPageDTO;
import com.taotao.cloud.goods.api.vo.BrandVO;
import com.taotao.cloud.goods.biz.entity.Brand;
import com.taotao.cloud.goods.biz.service.BrandService;
import com.taotao.cloud.logger.annotation.RequestLogger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 管理端,品牌接口
 */
@Validated
@RestController
@Tag(name = "平台管理端-品牌管理API", description = "平台管理端-品牌管理API")
@RequestMapping("/goods/manager/brand")
public class BrandManagerController {

	/**
	 * 品牌
	 */
	@Autowired
	private BrandService brandService;

	@Operation(summary = "通过id获取", description = "通过id获取", method = CommonConstant.GET)
	@RequestLogger(description = "通过id获取")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/get/{id}")
	public Result<Brand> get(@NotNull @PathVariable String id) {
		return Result.success(brandService.getById(id));
	}

	@Operation(summary = "获取所有可用品牌", description = "获取所有可用品牌", method = CommonConstant.GET)
	@RequestLogger(description = "获取所有可用品牌")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/all")
	public Result<List<Brand>> getAll() {
		List<Brand> list = brandService.list(new QueryWrapper<Brand>().eq("delete_flag", 0));
		return Result.success(list);
	}

	@Operation(summary = "分页获取", description = "分页获取", method = CommonConstant.GET)
	@RequestLogger(description = "分页获取")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@GetMapping(value = "/page")
	public Result<IPage<Brand>> getByPage(BrandPageDTO page) {
		return Result.success(brandService.getBrandsByPage(page));
	}

	@Operation(summary = "新增品牌", description = "新增品牌", method = CommonConstant.POST)
	@RequestLogger(description = "新增品牌")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PostMapping
	public Result<BrandVO> save(@Valid BrandVO brand) {
		if (brandService.addBrand(brand)) {
			return Result.success(brand);
		}
		throw new ServiceException(ResultCode.BRAND_SAVE_ERROR);
	}

	@Operation(summary = "更新数据", description = "更新数据", method = CommonConstant.POST)
	@RequestLogger(description = "更新数据")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PutMapping("/{id}")
	public Result<BrandVO> update(@PathVariable String id, @Valid BrandVO brand) {
		brand.setId(id);
		if (brandService.updateBrand(brand)) {
			return Result.success(brand);
		}
		throw new ServiceException(ResultCode.BRAND_UPDATE_ERROR);
	}

	@Operation(summary = "后台禁用品牌", description = "后台禁用品牌", method = CommonConstant.PUT)
	@RequestLogger(description = "后台禁用品牌")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@PutMapping(value = "/disable/{brandId}")
	public Result<Object> disable(@PathVariable String brandId,
		@RequestParam Boolean disable) {
		if (brandService.brandDisable(brandId, disable)) {
			return Result.success;
		}
		throw new ServiceException(ResultCode.BRAND_DISABLE_ERROR);
	}

	@Operation(summary = "批量删除", description = "批量删除", method = CommonConstant.DELETE)
	@RequestLogger(description = "批量删除")
	@PreAuthorize("hasAuthority('dept:tree:data')")
	@DeleteMapping(value = "/{ids}")
	public Result<Object> delAllByIds(@PathVariable List<String> ids) {
		brandService.deleteBrands(ids);
		return Result.success(ResultCode.SUCCESS);
	}

}