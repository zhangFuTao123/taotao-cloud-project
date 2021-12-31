package com.taotao.cloud.goods.biz.entity;

import com.taotao.cloud.data.jpa.entity.JpaSuperEntity;
import java.math.BigDecimal;
import javax.persistence.Table;

/**
 * @author shuigedeng
 */
//@Entity
@Table(name = "tt_product_spec_detail")
@org.hibernate.annotations.Table(appliesTo = "tt_product_spec_detail", comment = "商品信息扩展表")
public class ProductSpecDetail extends JpaSuperEntity {

    private Product product;

    private String shelfNum;

    private String name;

    private String attributeJson;

    private int inventory;

    private BigDecimal offerPrice;

    private BigDecimal costPrice;

    private BigDecimal minSellPrice;

    private BigDecimal maxSellPrice;

    private String remark;

    private int sellCount;

    private String sourceId;
}