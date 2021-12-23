package com.taotao.cloud.promotion.biz.entity;

import cn.lili.mybatis.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.cloud.web.base.entity.BaseSuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * 积分商品分类
 *
 * 
 * @since 2020-03-19 10:44 上午
 **/
@Entity
@Table(name = PointsGoodsCategory.TABLE_NAME)
@TableName(PointsGoodsCategory.TABLE_NAME)
@org.hibernate.annotations.Table(appliesTo = PointsGoodsCategory.TABLE_NAME, comment = "积分商品分类")
public class PointsGoodsCategory extends BaseSuperEntity<PointsGoodsCategory, Long> {

	public static final String TABLE_NAME = "li_points_goods_category";

    @NotEmpty(message = "分类名称不能为空")
    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "父id, 根节点为0")
    private String parentId;

    @ApiModelProperty(value = "层级, 从0开始")
    private Integer level;

    @ApiModelProperty(value = "排序值")
    private BigDecimal sortOrder;

}