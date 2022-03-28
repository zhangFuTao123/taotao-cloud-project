package com.taotao.cloud.report.biz.entity.statistics.service;

import cn.lili.modules.distribution.entity.dos.DistributionCash;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 分销佣金统计
 */
public interface DistributionCashStatisticsService extends IService<DistributionCash> {

    /**
     * 待处理分销员提现申请数量
     *
     * @return 待处理分销员提现申请数量
     */
    long newDistributionCash();
}