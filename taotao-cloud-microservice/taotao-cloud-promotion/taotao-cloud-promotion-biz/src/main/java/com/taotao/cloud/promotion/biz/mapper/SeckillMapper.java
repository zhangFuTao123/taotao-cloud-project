package com.taotao.cloud.promotion.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taotao.cloud.promotion.biz.entity.Seckill;
import org.apache.ibatis.annotations.Update;

/**
 * 秒杀活动数据处理层
 *
 * 
 * @since 2020/8/21
 */
public interface SeckillMapper extends BaseMapper<Seckill> {

    /**
     * 修改秒杀活动数量
     *
     * @param seckillId 秒杀活动ID
     */
    @Update("UPDATE li_seckill SET goods_num =( SELECT count( id ) FROM li_seckill_apply WHERE seckill_id = #{seckillId} ) WHERE id = #{seckillId}")
    void updateSeckillGoodsNum(String seckillId);
}