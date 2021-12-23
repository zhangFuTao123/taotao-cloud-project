package com.taotao.cloud.member.biz.service.impl;

import cn.hutool.core.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.taotao.cloud.member.api.dto.CollectionDTO;
import com.taotao.cloud.member.api.vo.StoreCollectionVO;
import com.taotao.cloud.member.biz.entity.StoreCollection;
import com.taotao.cloud.member.biz.mapper.StoreCollectionMapper;
import com.taotao.cloud.member.biz.service.StoreCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 会员店铺收藏业务层实现
 *
 *
 * @since 2020/11/18 2:52 下午
 */
@Service
public class StoreCollectionServiceImpl extends ServiceImpl<StoreCollectionMapper, StoreCollection> implements
	StoreCollectionService {


    @Autowired
    private StoreService storeService;

    @Override
    public IPage<StoreCollectionVO> storeCollection(PageVO pageVo) {
        QueryWrapper<StoreCollectionVO> queryWrapper = new QueryWrapper();
        queryWrapper.eq("sc.member_id", UserContext.getCurrentUser().getId());
        queryWrapper.orderByDesc("sc.create_time");
        return this.baseMapper.storeCollectionVOList(PageUtil.initPage(pageVo), queryWrapper);
    }

    @Override
    public boolean isCollection(String storeId) {
        QueryWrapper<StoreCollection> queryWrapper = new QueryWrapper();
        queryWrapper.eq("member_id", UserContext.getCurrentUser().getId());
        queryWrapper.eq("store_id", storeId);
        return Optional.ofNullable(this.getOne(queryWrapper)).isPresent();
    }

    @Override
    public StoreCollection addStoreCollection(String storeId) {
        if (this.getOne(new LambdaUpdateWrapper<StoreCollection>()
                .eq(StoreCollection::getMemberId, UserContext.getCurrentUser().getId())
                .eq(StoreCollection::getStoreId, storeId)) == null) {
            StoreCollection storeCollection = new StoreCollection(UserContext.getCurrentUser().getId(), storeId);
            this.save(storeCollection);
            storeService.updateStoreCollectionNum(new CollectionDTO(storeId, 1));
            return storeCollection;
        }
        throw new ServiceException(ResultCode.USER_COLLECTION_EXIST);
    }

    @Override
    public boolean deleteStoreCollection(String storeId) {
        QueryWrapper<StoreCollection> queryWrapper = new QueryWrapper();
        queryWrapper.eq("member_id", UserContext.getCurrentUser().getId());
        queryWrapper.eq("store_id", storeId);
        storeService.updateStoreCollectionNum(new CollectionDTO(storeId, -1));
        return this.remove(queryWrapper);
    }
}