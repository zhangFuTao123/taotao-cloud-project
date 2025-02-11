package com.taotao.cloud.payment.biz.daxpay.core.merchant.service;

import cn.bootx.platform.common.core.exception.DataNotExistException;
import cn.bootx.platform.common.core.rest.PageResult;
import cn.bootx.platform.common.core.rest.param.PageParam;
import cn.bootx.platform.common.core.util.ResultConvertUtil;
import cn.bootx.platform.common.mybatisplus.util.MpUtil;
import cn.bootx.platform.daxpay.core.merchant.dao.MerchantInfoManager;
import cn.bootx.platform.daxpay.core.merchant.entity.MerchantInfo;
import cn.bootx.platform.daxpay.dto.merchant.MerchantInfoDto;
import cn.bootx.platform.daxpay.param.merchant.MerchantInfoParam;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商户
 * @author xxm
 * @date 2023-05-17
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MerchantInfoService {
    private final MerchantInfoManager merchantInfoManager;

    /**
     * 添加
     */
    public void add(MerchantInfoParam param){
        MerchantInfo merchantInfo = MerchantInfo.init(param);
        merchantInfo.setMchNo("M"+System.currentTimeMillis());
        merchantInfoManager.save(merchantInfo);
    }

    /**
     * 修改
     */
    public void update(MerchantInfoParam param){
        MerchantInfo merchantInfo = merchantInfoManager.findById(param.getId()).orElseThrow(DataNotExistException::new);
        BeanUtil.copyProperties(param,merchantInfo, CopyOptions.create().ignoreNullValue());
        merchantInfoManager.updateById(merchantInfo);
    }

    /**
     * 分页
     */
    public PageResult<MerchantInfoDto> page(PageParam pageParam,MerchantInfoParam merchantInfoParam){
        return MpUtil.convert2DtoPageResult(merchantInfoManager.page(pageParam,merchantInfoParam));
    }

    /**
     * 获取单条
     */
    public MerchantInfoDto findById(Long id){
        return merchantInfoManager.findById(id).map(MerchantInfo::toDto).orElseThrow(DataNotExistException::new);
    }

    /**
     * 获取全部
     */
    public List<MerchantInfoDto> findAll(){
        return ResultConvertUtil.dtoListConvert(merchantInfoManager.findAll());
    }

    /**
     * 删除
     */
    public void delete(Long id){
        merchantInfoManager.deleteById(id);
    }
}
