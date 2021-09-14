/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.taotao.cloud.order.api.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author shuigedeng
 * @version 1.0.0
 * @since 2020/10/22 12:29
 */
@Schema(name = "OrderItemDTO", description = "订单项DTO")
public class OrderItemDTO implements Serializable {

	private static final long serialVersionUID = 5126530068827085130L;
	@Schema(description = "订单子编码")
	private String itemCode;
	@Schema(description = "商品SPU ID")
	private Long productSpuId;
	@Schema(description = "商品SPU_CODE")
	private String productSpuCode;
	@Schema(description = "商品SPU名称")
	private String productSpuName;
	@Schema(description = "商品SKU ID")
	private Long productSkuId;
	@Schema(description = "商品SKU 规格名称")
	private String productSkuName;
	@Schema(description = "商品单价")
	private BigDecimal productPrice = BigDecimal.ZERO;
	@Schema(description = "购买数量")
	private Integer num = 1;
	@Schema(description = "合计金额")
	private BigDecimal sumAmount = BigDecimal.ZERO;
	@Schema(description = "商品主图")
	private String productPicUrl;
	@Schema(description = "供应商id")
	private Long supplierId;
	@Schema(description = "供应商名称")
	private String supplierName;
	@Schema(description = "超时退货期限")
	private Integer refundTime;
	@Schema(description = "退货数量")
	private Integer rejectCount = 0;
	@Schema(description = "商品类型 0 普通商品 1 秒杀商品")
	private Integer type = 0;


	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Long getProductSpuId() {
		return productSpuId;
	}

	public void setProductSpuId(Long productSpuId) {
		this.productSpuId = productSpuId;
	}

	public String getProductSpuCode() {
		return productSpuCode;
	}

	public void setProductSpuCode(String productSpuCode) {
		this.productSpuCode = productSpuCode;
	}

	public String getProductSpuName() {
		return productSpuName;
	}

	public void setProductSpuName(String productSpuName) {
		this.productSpuName = productSpuName;
	}

	public Long getProductSkuId() {
		return productSkuId;
	}

	public void setProductSkuId(Long productSkuId) {
		this.productSkuId = productSkuId;
	}

	public String getProductSkuName() {
		return productSkuName;
	}

	public void setProductSkuName(String productSkuName) {
		this.productSkuName = productSkuName;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public BigDecimal getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}

	public String getProductPicUrl() {
		return productPicUrl;
	}

	public void setProductPicUrl(String productPicUrl) {
		this.productPicUrl = productPicUrl;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Integer refundTime) {
		this.refundTime = refundTime;
	}

	public Integer getRejectCount() {
		return rejectCount;
	}

	public void setRejectCount(Integer rejectCount) {
		this.rejectCount = rejectCount;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public static OrderItemDTOBuilder builder() {
		return new OrderItemDTOBuilder();
	}

	public static final class OrderItemDTOBuilder {

		private String itemCode;
		private Long productSpuId;
		private String productSpuCode;
		private String productSpuName;
		private Long productSkuId;
		private String productSkuName;
		private BigDecimal productPrice = BigDecimal.ZERO;
		private Integer num = 1;
		private BigDecimal sumAmount = BigDecimal.ZERO;
		private String productPicUrl;
		private Long supplierId;
		private String supplierName;
		private Integer refundTime;
		private Integer rejectCount = 0;
		private Integer type = 0;

		private OrderItemDTOBuilder() {
		}



		public OrderItemDTOBuilder itemCode(String itemCode) {
			this.itemCode = itemCode;
			return this;
		}

		public OrderItemDTOBuilder productSpuId(Long productSpuId) {
			this.productSpuId = productSpuId;
			return this;
		}

		public OrderItemDTOBuilder productSpuCode(String productSpuCode) {
			this.productSpuCode = productSpuCode;
			return this;
		}

		public OrderItemDTOBuilder productSpuName(String productSpuName) {
			this.productSpuName = productSpuName;
			return this;
		}

		public OrderItemDTOBuilder productSkuId(Long productSkuId) {
			this.productSkuId = productSkuId;
			return this;
		}

		public OrderItemDTOBuilder productSkuName(String productSkuName) {
			this.productSkuName = productSkuName;
			return this;
		}

		public OrderItemDTOBuilder productPrice(BigDecimal productPrice) {
			this.productPrice = productPrice;
			return this;
		}

		public OrderItemDTOBuilder num(Integer num) {
			this.num = num;
			return this;
		}

		public OrderItemDTOBuilder sumAmount(BigDecimal sumAmount) {
			this.sumAmount = sumAmount;
			return this;
		}

		public OrderItemDTOBuilder productPicUrl(String productPicUrl) {
			this.productPicUrl = productPicUrl;
			return this;
		}

		public OrderItemDTOBuilder supplierId(Long supplierId) {
			this.supplierId = supplierId;
			return this;
		}

		public OrderItemDTOBuilder supplierName(String supplierName) {
			this.supplierName = supplierName;
			return this;
		}

		public OrderItemDTOBuilder refundTime(Integer refundTime) {
			this.refundTime = refundTime;
			return this;
		}

		public OrderItemDTOBuilder rejectCount(Integer rejectCount) {
			this.rejectCount = rejectCount;
			return this;
		}

		public OrderItemDTOBuilder type(Integer type) {
			this.type = type;
			return this;
		}

		public OrderItemDTO build() {
			OrderItemDTO orderItemDTO = new OrderItemDTO();
			orderItemDTO.setItemCode(itemCode);
			orderItemDTO.setProductSpuId(productSpuId);
			orderItemDTO.setProductSpuCode(productSpuCode);
			orderItemDTO.setProductSpuName(productSpuName);
			orderItemDTO.setProductSkuId(productSkuId);
			orderItemDTO.setProductSkuName(productSkuName);
			orderItemDTO.setProductPrice(productPrice);
			orderItemDTO.setNum(num);
			orderItemDTO.setSumAmount(sumAmount);
			orderItemDTO.setProductPicUrl(productPicUrl);
			orderItemDTO.setSupplierId(supplierId);
			orderItemDTO.setSupplierName(supplierName);
			orderItemDTO.setRefundTime(refundTime);
			orderItemDTO.setRejectCount(rejectCount);
			orderItemDTO.setType(type);
			return orderItemDTO;
		}
	}
}