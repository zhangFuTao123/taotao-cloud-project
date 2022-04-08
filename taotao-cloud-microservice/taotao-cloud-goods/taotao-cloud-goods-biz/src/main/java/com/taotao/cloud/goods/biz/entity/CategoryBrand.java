package com.taotao.cloud.goods.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.cloud.web.base.entity.BaseSuperEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.taotao.cloud.web.base.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 商品分类品牌关联表
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = CategoryBrand.TABLE_NAME)
@TableName(CategoryBrand.TABLE_NAME)
@org.hibernate.annotations.Table(appliesTo = CategoryBrand.TABLE_NAME, comment = "商品分类品牌关联表")
public class CategoryBrand extends SuperEntity<CategoryBrand, Long> {

	public static final String TABLE_NAME = "tt_category_brand";

	/**
	 * 分类id
	 */
	@Column(name = "category_id", columnDefinition = "bigint not null comment '分类id'")
	private Long categoryId;

	/**
	 * 品牌id
	 */
	@Column(name = "brand_id", columnDefinition = "bigint not null comment '品牌id'")
	private Long brandId;
}
