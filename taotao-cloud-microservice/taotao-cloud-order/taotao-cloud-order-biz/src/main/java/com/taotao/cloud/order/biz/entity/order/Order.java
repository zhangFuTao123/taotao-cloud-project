package com.taotao.cloud.order.biz.entity.order;

import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.cloud.common.enums.ClientTypeEnum;
import com.taotao.cloud.order.api.enums.cart.DeliveryMethodEnum;
import com.taotao.cloud.order.api.enums.order.DeliverStatusEnum;
import com.taotao.cloud.order.api.enums.order.OrderPromotionTypeEnum;
import com.taotao.cloud.order.api.enums.order.OrderStatusEnum;
import com.taotao.cloud.order.api.enums.order.OrderTypeEnum;
import com.taotao.cloud.order.api.enums.order.PayStatusEnum;
import com.taotao.cloud.web.base.entity.BaseSuperEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 订单表
 */
@Entity
@Table(name = Order.TABLE_NAME)
@TableName(Order.TABLE_NAME)
@org.hibernate.annotations.Table(appliesTo = Order.TABLE_NAME, comment = "订单表")
public class Order extends BaseSuperEntity<OrderInfo, Long> {

	public static final String TABLE_NAME = "tt_order";

	/**
	 * 订单编号
	 */
	@Column(name = "sn", nullable = false, columnDefinition = "varchar(64) not null comment '订单编号'")
	private String sn;

	/**
	 * 交易编号 关联Trade
	 */
	@Column(name = "trade_sn", nullable = false, columnDefinition = "varchar(64) not null comment '交易编号 关联Trade'")
	private String tradeSn;

	/**
	 * 店铺ID
	 */
	@Column(name = "store_id", nullable = false, columnDefinition = "varchar(64) not null comment '店铺ID'")
	private String storeId;

	/**
	 * 店铺名称
	 */
	@Column(name = "store_name", nullable = false, columnDefinition = "varchar(64) not null comment '店铺名称'")
	private String storeName;

	/**
	 * 会员ID
	 */
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '会员ID'")
	private String memberId;

	/**
	 * 用户名
	 */
	@Column(name = "member_name", nullable = false, columnDefinition = "varchar(64) not null comment '用户名'")
	private String memberName;

	/**
	 * 订单状态
	 *
	 * @see OrderStatusEnum
	 */
	@Column(name = "order_status", nullable = false, columnDefinition = "varchar(64) not null comment '订单状态'")
	private String orderStatus;

	/**
	 * 付款状态
	 *
	 * @see PayStatusEnum
	 */
	@Column(name = "pay_status", nullable = false, columnDefinition = "varchar(64) not null comment '付款状态'")
	private String payStatus;

	/**
	 * 货运状态
	 *
	 * @see DeliverStatusEnum
	 */
	@Column(name = "deliver_status", nullable = false, columnDefinition = "varchar(64) not null comment '货运状态'")
	private String deliverStatus;

	/**
	 * 第三方付款流水号
	 */
	@Column(name = "receivable_no", nullable = false, columnDefinition = "varchar(64) not null comment '第三方付款流水号'")
	private String receivableNo;

	/**
	 * 支付方式
	 */
	@Column(name = "payment_method", nullable = false, columnDefinition = "varchar(64) not null comment '支付方式'")
	private String paymentMethod;

	/**
	 * 支付时间
	 */
	@Column(name = "member_id", nullable = false, columnDefinition = "varchar(64) not null comment '支付时间'")
	private LocalDateTime paymentTime;

	/**
	 * 收件人姓名
	 */
	@Column(name = "consignee_name", nullable = false, columnDefinition = "varchar(64) not null comment '收件人姓名'")
	private String consigneeName;

	/**
	 * 收件人手机
	 */
	@Column(name = "consignee_mobile", nullable = false, columnDefinition = "varchar(64) not null comment '收件人手机'")
	private String consigneeMobile;

	/**
	 * 配送方式
	 *
	 * @see DeliveryMethodEnum
	 */
	@Column(name = "delivery_method", nullable = false, columnDefinition = "varchar(64) not null comment '配送方式'")
	private String deliveryMethod;

	/**
	 * 地址名称， '，'分割
	 */
	@Column(name = "consignee_address_path", nullable = false, columnDefinition = "varchar(64) not null comment '地址名称，逗号分割'")
	private String consigneeAddressPath;

	/**
	 * 地址id，'，'分割
	 */
	@Column(name = "consignee_address_id_path", nullable = false, columnDefinition = "varchar(64) not null comment '地址id，逗号分割'")
	private String consigneeAddressIdPath;

	/**
	 * 详细地址
	 */
	@Column(name = "consignee_detail", nullable = false, columnDefinition = "varchar(64) not null comment '详细地址'")
	private String consigneeDetail;

	/**
	 * 总价格
	 */
	@Column(name = "flow_price", nullable = false, columnDefinition = "varchar(64) not null comment '总价格'")
	private BigDecimal flowPrice;

	/**
	 * 商品价格
	 */
	@Column(name = "goods_price", nullable = false, columnDefinition = "varchar(64) not null comment '商品价格'")
	private BigDecimal goodsPrice;

	/**
	 * 运费
	 */
	@Schema(description = "运费")
	@Column(name = "freight_price", nullable = false, columnDefinition = "varchar(64) not null comment '运费'")
	private BigDecimal freightPrice;

	/**
	 * 优惠的金额
	 */
	@Schema(description = "优惠的金额")
	@Column(name = "discount_price", nullable = false, columnDefinition = "varchar(64) not null comment '优惠的金额'")
	private BigDecimal discountPrice;

	/**
	 * 修改价格
	 */
	@Column(name = "update_price", nullable = false, columnDefinition = "varchar(64) not null comment '修改价格'")
	private BigDecimal updatePrice;

	/**
	 * 发货单号
	 */
	@Column(name = "logistics_no", nullable = false, columnDefinition = "varchar(64) not null comment '发货单号'")
	private String logisticsNo;

	/**
	 * 物流公司CODE
	 */
	@Column(name = "logistics_code", nullable = false, columnDefinition = "varchar(64) not null comment '物流公司CODE'")
	private String logisticsCode;

	/**
	 * 物流公司名称
	 */
	@Column(name = "logistics_name", nullable = false, columnDefinition = "varchar(64) not null comment '物流公司名称'")
	private String logisticsName;

	/**
	 * 订单商品总重量
	 */
	@Column(name = "weight", nullable = false, columnDefinition = "varchar(64) not null comment '订单商品总重量'")
	private BigDecimal weight;

	/**
	 * 商品数量
	 */
	@Column(name = "goods_num", nullable = false, columnDefinition = "varchar(64) not null comment '商品数量'")
	private Integer goodsNum;

	/**
	 * 买家订单备注
	 */
	@Column(name = "remark", nullable = false, columnDefinition = "varchar(64) not null comment '买家订单备注'")
	private String remark;

	/**
	 * 订单取消原因
	 */
	@Column(name = "cancel_reason", nullable = false, columnDefinition = "varchar(64) not null comment '订单取消原因'")
	private String cancelReason;

	/**
	 * 完成时间
	 */
	@Column(name = "complete_time", nullable = false, columnDefinition = "varchar(64) not null comment '完成时间'")
	private LocalDateTime completeTime;

	/**
	 * 送货时间
	 */
	@Column(name = "logistics_time", nullable = false, columnDefinition = "varchar(64) not null comment '送货时间'")
	private LocalDateTime logisticsTime;

	/**
	 * 支付方式返回的交易号
	 */
	@Column(name = "pay_order_no", nullable = false, columnDefinition = "varchar(64) not null comment '支付方式返回的交易号'")
	private String payOrderNo;

	/**
	 * 订单来源
	 *
	 * @see ClientTypeEnum
	 */
	@Column(name = "client_type", nullable = false, columnDefinition = "varchar(64) not null comment '订单来源'")
	private String clientType;

	/**
	 * 是否需要发票
	 */
	@Column(name = "need_receipt", nullable = false, columnDefinition = "varchar(64) not null comment '是否需要发票'")
	private Boolean needReceipt;

	/**
	 * 是否为其他订单下的订单，如果是则为依赖订单的sn，否则为空
	 */
	@Column(name = "parent_order_sn", nullable = false, columnDefinition = "varchar(64) not null comment '是否为其他订单下的订单，如果是则为依赖订单的sn，否则为空'")
	private String parentOrderSn;

	/**
	 * 是否为某订单类型的订单，如果是则为订单类型的id，否则为空
	 */
	@Column(name = "promotion_id", nullable = false, columnDefinition = "varchar(64) not null comment '是否为某订单类型的订单，如果是则为订单类型的id，否则为空'")
	private String promotionId;


	/**
	 * 订单类型
	 *
	 * @see OrderTypeEnum
	 */
	@Column(name = "order_type", nullable = false, columnDefinition = "varchar(64) not null comment '订单类型'")
	private String orderType;

	/**
	 * 订单促销类型
	 *
	 * @see OrderPromotionTypeEnum
	 */
	@Column(name = "order_promotion_type", nullable = false, columnDefinition = "varchar(64) not null comment '订单促销类型'")
	private String orderPromotionType;

	/**
	 * 价格详情
	 */
	@Column(name = "price_detail", nullable = false, columnDefinition = "varchar(64) not null comment '价格详情'")
	private String priceDetail;

	/**
	 * 订单是否支持原路退回
	 */
	@Column(name = "can_return", nullable = false, columnDefinition = "varchar(64) not null comment '订单是否支持原路退回'")
	private Boolean canReturn;

	/**
	 * 提货码
	 */
	@Column(name = "verification_code", nullable = false, columnDefinition = "varchar(64) not null comment '提货码'")
	private String verificationCode;

	/**
	 * 分销员ID
	 */
	@Column(name = "distribution_id", nullable = false, columnDefinition = "varchar(64) not null comment '分销员ID'")
	private String distributionId;

	/**
	 * 使用的店铺会员优惠券id(,区分)
	 */
	@Column(name = "use_store_member_coupon_ids", nullable = false, columnDefinition = "varchar(64) not null comment '使用的店铺会员优惠券id(,区分)'")
	private String useStoreMemberCouponIds;

	/**
	 * 使用的平台会员优惠券id
	 */
	@Column(name = "use_platform_member_coupon_id", nullable = false, columnDefinition = "varchar(64) not null comment '使用的平台会员优惠券id'")
	private String usePlatformMemberCouponId;

	///**
	// * 构建订单
	// *
	// * @param cartVO   购物车VO
	// * @param tradeDTO 交易DTO
	// */
	//public Order(CartVO cartVO, TradeDTO tradeDTO) {
	//	String oldId = this.getId();
	//	BeanUtil.copyProperties(tradeDTO, this);
	//	BeanUtil.copyProperties(cartVO.getPriceDetailDTO(), this);
	//	BeanUtil.copyProperties(cartVO, this);
	//	//填写订单类型
	//	this.setTradeType(cartVO, tradeDTO);
	//	setId(oldId);
	//
	//	//设置默认支付状态
	//	this.setOrderStatus(OrderStatusEnum.UNPAID.name());
	//	this.setPayStatus(PayStatusEnum.UNPAID.name());
	//	this.setDeliverStatus(DeliverStatusEnum.UNDELIVERED.name());
	//	this.setTradeSn(tradeDTO.getSn());
	//	this.setRemark(cartVO.getRemark());
	//	this.setFreightPrice(tradeDTO.getPriceDetailDTO().getFreightPrice());
	//	//会员收件信息
	//	this.setConsigneeAddressIdPath(tradeDTO.getMemberAddress().getConsigneeAddressIdPath());
	//	this.setConsigneeAddressPath(tradeDTO.getMemberAddress().getConsigneeAddressPath());
	//	this.setConsigneeDetail(tradeDTO.getMemberAddress().getDetail());
	//	this.setConsigneeMobile(tradeDTO.getMemberAddress().getMobile());
	//	this.setConsigneeName(tradeDTO.getMemberAddress().getName());
	//	//平台优惠券判定
	//	if (tradeDTO.getPlatformCoupon() != null) {
	//		this.setUsePlatformMemberCouponId(
	//			tradeDTO.getPlatformCoupon().getMemberCoupon().getId());
	//	}
	//	//店铺优惠券判定
	//	if (tradeDTO.getStoreCoupons() != null && !tradeDTO.getStoreCoupons().isEmpty()) {
	//		StringBuilder storeCouponIds = new StringBuilder();
	//		for (String s : tradeDTO.getStoreCoupons().keySet()) {
	//			storeCouponIds.append(s).append(",");
	//		}
	//		this.setUseStoreMemberCouponIds(storeCouponIds.toString());
	//	}
	//
	//}
	//
	//
	///**
	// * 填写交易（订单）类型 1.判断是普通、促销订单 2.普通订单进行区分：实物订单、虚拟订单 3.促销订单判断货物进行区分实物、虚拟商品。 4.拼团订单需要填写父订单ID
	// *
	// * @param cartVO   购物车VO
	// * @param tradeDTO 交易DTO
	// */
	//private void setTradeType(CartVO cartVO, TradeDTO tradeDTO) {
	//	//判断是否为普通订单、促销订单
	//	if (tradeDTO.getCartTypeEnum().equals(CartTypeEnum.CART) || tradeDTO.getCartTypeEnum()
	//		.equals(CartTypeEnum.BUY_NOW)) {
	//		this.setOrderType(OrderTypeEnum.NORMAL.name());
	//	} else if (tradeDTO.getCartTypeEnum().equals(CartTypeEnum.VIRTUAL)) {
	//		this.setOrderType(OrderTypeEnum.VIRTUAL.name());
	//	} else {
	//		//促销订单（拼团、积分）-判断购买的是虚拟商品还是实物商品
	//		String goodsType = cartVO.getCheckedSkuList().get(0).getGoodsSku().getGoodsType();
	//		if (StrUtil.isEmpty(goodsType) || goodsType.equals(
	//			GoodsTypeEnum.PHYSICAL_GOODS.name())) {
	//			this.setOrderType(OrderTypeEnum.NORMAL.name());
	//		} else {
	//			this.setOrderType(OrderTypeEnum.VIRTUAL.name());
	//		}
	//		//填写订单的促销类型
	//		this.setOrderPromotionType(tradeDTO.getCartTypeEnum().name());
	//
	//		//判断是否为拼团订单，如果为拼团订单获取拼团ID，判断是否为主订单
	//		if (tradeDTO.getCartTypeEnum().name().equals(PromotionTypeEnum.PINTUAN.name())) {
	//			Optional<String> pintuanId = cartVO.getCheckedSkuList().get(0).getPromotions()
	//				.stream()
	//				.filter(i -> i.getPromotionType().equals(PromotionTypeEnum.PINTUAN.name()))
	//				.map(PromotionGoods::getPromotionId).findFirst();
	//			promotionId = pintuanId.get();
	//		}
	//	}
	//}
	//
	//public PriceDetailDTO getPriceDetailDTO() {
	//	try {
	//		return JSONUtil.toBean(priceDetail, PriceDetailDTO.class);
	//	} catch (Exception e) {
	//		return null;
	//	}
	//}
	//
	//public void setPriceDetailDTO(PriceDetailDTO priceDetail) {
	//	this.priceDetail = JSONUtil.toJsonStr(priceDetail);
	//}


	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getTradeSn() {
		return tradeSn;
	}

	public void setTradeSn(String tradeSn) {
		this.tradeSn = tradeSn;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getDeliverStatus() {
		return deliverStatus;
	}

	public void setDeliverStatus(String deliverStatus) {
		this.deliverStatus = deliverStatus;
	}

	public String getReceivableNo() {
		return receivableNo;
	}

	public void setReceivableNo(String receivableNo) {
		this.receivableNo = receivableNo;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public LocalDateTime getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(LocalDateTime paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getConsigneeMobile() {
		return consigneeMobile;
	}

	public void setConsigneeMobile(String consigneeMobile) {
		this.consigneeMobile = consigneeMobile;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getConsigneeAddressPath() {
		return consigneeAddressPath;
	}

	public void setConsigneeAddressPath(String consigneeAddressPath) {
		this.consigneeAddressPath = consigneeAddressPath;
	}

	public String getConsigneeAddressIdPath() {
		return consigneeAddressIdPath;
	}

	public void setConsigneeAddressIdPath(String consigneeAddressIdPath) {
		this.consigneeAddressIdPath = consigneeAddressIdPath;
	}

	public String getConsigneeDetail() {
		return consigneeDetail;
	}

	public void setConsigneeDetail(String consigneeDetail) {
		this.consigneeDetail = consigneeDetail;
	}

	public BigDecimal getFlowPrice() {
		return flowPrice;
	}

	public void setFlowPrice(BigDecimal flowPrice) {
		this.flowPrice = flowPrice;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public BigDecimal getFreightPrice() {
		return freightPrice;
	}

	public void setFreightPrice(BigDecimal freightPrice) {
		this.freightPrice = freightPrice;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public BigDecimal getUpdatePrice() {
		return updatePrice;
	}

	public void setUpdatePrice(BigDecimal updatePrice) {
		this.updatePrice = updatePrice;
	}

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

	public String getLogisticsCode() {
		return logisticsCode;
	}

	public void setLogisticsCode(String logisticsCode) {
		this.logisticsCode = logisticsCode;
	}

	public String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public LocalDateTime getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(LocalDateTime completeTime) {
		this.completeTime = completeTime;
	}

	public LocalDateTime getLogisticsTime() {
		return logisticsTime;
	}

	public void setLogisticsTime(LocalDateTime logisticsTime) {
		this.logisticsTime = logisticsTime;
	}

	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public Boolean getNeedReceipt() {
		return needReceipt;
	}

	public void setNeedReceipt(Boolean needReceipt) {
		this.needReceipt = needReceipt;
	}

	public String getParentOrderSn() {
		return parentOrderSn;
	}

	public void setParentOrderSn(String parentOrderSn) {
		this.parentOrderSn = parentOrderSn;
	}

	public String getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderPromotionType() {
		return orderPromotionType;
	}

	public void setOrderPromotionType(String orderPromotionType) {
		this.orderPromotionType = orderPromotionType;
	}

	public String getPriceDetail() {
		return priceDetail;
	}

	public void setPriceDetail(String priceDetail) {
		this.priceDetail = priceDetail;
	}

	public Boolean getCanReturn() {
		return canReturn;
	}

	public void setCanReturn(Boolean canReturn) {
		this.canReturn = canReturn;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getDistributionId() {
		return distributionId;
	}

	public void setDistributionId(String distributionId) {
		this.distributionId = distributionId;
	}

	public String getUseStoreMemberCouponIds() {
		return useStoreMemberCouponIds;
	}

	public void setUseStoreMemberCouponIds(String useStoreMemberCouponIds) {
		this.useStoreMemberCouponIds = useStoreMemberCouponIds;
	}

	public String getUsePlatformMemberCouponId() {
		return usePlatformMemberCouponId;
	}

	public void setUsePlatformMemberCouponId(String usePlatformMemberCouponId) {
		this.usePlatformMemberCouponId = usePlatformMemberCouponId;
	}
}
