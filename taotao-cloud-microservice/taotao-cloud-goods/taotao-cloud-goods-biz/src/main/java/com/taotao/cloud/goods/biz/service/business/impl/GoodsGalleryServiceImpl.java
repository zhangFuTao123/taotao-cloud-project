package com.taotao.cloud.goods.biz.service.business.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.taotao.cloud.common.model.Result;
import com.taotao.cloud.common.utils.io.FileUtils;
import com.taotao.cloud.goods.biz.mapper.IGoodsGalleryMapper;
import com.taotao.cloud.goods.biz.model.entity.GoodsGallery;
import com.taotao.cloud.goods.biz.repository.cls.GoodsGalleryRepository;
import com.taotao.cloud.goods.biz.repository.inf.IGoodsGalleryRepository;
import com.taotao.cloud.goods.biz.service.business.IGoodsGalleryService;
import com.taotao.cloud.sys.api.enums.SettingCategoryEnum;
import com.taotao.cloud.sys.api.feign.IFeignSettingApi;
import com.taotao.cloud.sys.api.model.vo.setting.GoodsSettingVO;
import com.taotao.cloud.web.base.service.impl.BaseSuperServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品相册接口实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:02:38
 */
@AllArgsConstructor
@Service
public class GoodsGalleryServiceImpl extends
	BaseSuperServiceImpl<IGoodsGalleryMapper, GoodsGallery, GoodsGalleryRepository, IGoodsGalleryRepository, Long> implements IGoodsGalleryService {

	/**
	 * 设置
	 */
	@Autowired
	private IFeignSettingApi settingService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean add(List<String> goodsGalleryList, Long goodsId) {
		//删除原来商品相册信息
		this.baseMapper.delete(new UpdateWrapper<GoodsGallery>().eq("goods_id", goodsId));
		//确定好图片选择器后进行处理
		int i = 0;
		for (String origin : goodsGalleryList) {
			//获取带所有缩略的相册
			GoodsGallery galley = this.getGoodsGallery(origin);
			galley.setGoodsId(goodsId);
			//默认第一个为默认图片
			galley.setIsDefault(i == 0 ? 1 : 0);
			i++;
			this.baseMapper.insert(galley);
		}
		return true;
	}

	@Override
	public GoodsGallery getGoodsGallery(String origin) {
		GoodsGallery goodsGallery = new GoodsGallery();
		//获取商品系统配置决定是否审核
		GoodsSettingVO goodsSetting = settingService.getGoodsSetting(
			SettingCategoryEnum.GOODS_SETTING.name());
		//缩略图
		String thumbnail = FileUtils.getUrl(origin, goodsSetting.getAbbreviationPictureWidth(),
			goodsSetting.getAbbreviationPictureHeight());
		//小图
		String small = FileUtils.getUrl(origin, goodsSetting.getSmallPictureWidth(),
			goodsSetting.getSmallPictureHeight());
		//赋值
		goodsGallery.setSmall(small);
		goodsGallery.setThumbnail(thumbnail);
		goodsGallery.setOriginal(origin);
		return goodsGallery;
	}

	@Override
	public List<GoodsGallery> goodsGalleryList(Long goodsId) {
		//根据商品id查询商品相册
		LambdaQueryWrapper<GoodsGallery> queryWrapper = Wrappers.lambdaQuery();
		queryWrapper.eq(GoodsGallery::getGoodsId, goodsId);
		return this.list(queryWrapper);
	}
}
