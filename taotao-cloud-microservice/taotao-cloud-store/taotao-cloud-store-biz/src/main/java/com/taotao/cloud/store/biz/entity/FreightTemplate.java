package com.taotao.cloud.store.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.cloud.store.api.enums.FreightTemplateEnum;
import com.taotao.cloud.web.base.entity.BaseSuperEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 运费模板
 *
 * @since 2020/11/17 4:27 下午
 */
@Entity
@Table(name = FreightTemplate.TABLE_NAME)
@TableName(FreightTemplate.TABLE_NAME)
@org.hibernate.annotations.Table(appliesTo = FreightTemplate.TABLE_NAME, comment = "运费模板表")
public class FreightTemplate extends BaseSuperEntity<FreightTemplate, Long> {

	public static final String TABLE_NAME = "li_freight_template";

	@Column(name = "store_id", nullable = false, columnDefinition = "varchar(32) not null comment '店铺ID'")
	private String storeId;

	@Column(name = "name", nullable = false, columnDefinition = "varchar(32) not null comment '模板名称'")
	private String name;

	/**
	 * @see FreightTemplateEnum
	 */
	@Column(name = "pricing_method", nullable = false, columnDefinition = "varchar(32) not null comment '计价方式：按件、按重量 WEIGHT,NUM,FREE'")
	private String pricingMethod;


	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPricingMethod() {
		return pricingMethod;
	}

	public void setPricingMethod(String pricingMethod) {
		this.pricingMethod = pricingMethod;
	}
}
