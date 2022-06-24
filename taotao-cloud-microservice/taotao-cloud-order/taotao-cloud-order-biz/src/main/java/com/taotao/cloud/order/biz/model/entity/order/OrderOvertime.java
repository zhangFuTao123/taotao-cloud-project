package com.taotao.cloud.order.biz.model.entity.order;


import com.baomidou.mybatisplus.annotation.TableName;
import com.taotao.cloud.web.base.entity.BaseSuperEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 订单超时信息表
 *
 * @author shuigedeng
 * @since 2020/4/30 15:44
 */
@Getter
@Setter
@ToString(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TableName(OrderOvertime.TABLE_NAME)
@Table(name = OrderOvertime.TABLE_NAME)
@org.hibernate.annotations.Table(appliesTo = OrderOvertime.TABLE_NAME, comment = "订单超时信息表")
public class OrderOvertime extends BaseSuperEntity<OrderOvertime,Long> {

	public static final String TABLE_NAME = "order_overtime";

    /**
     * 收货人姓名
     */
    @Column(name = "receiver_name", columnDefinition = "varchar(32) not null comment '收货人姓名'")
    private String receiverName;

    /**
     * 收货人电话
     */
    @Column(name = "receiver_phone", columnDefinition = "varchar(32) not null comment '收货人电话'")
    private String receiverPhone;

    /**
     * 支付时间--支付成功后的时间
     */
    @Column(name = "pay_success_time", columnDefinition = "datetime comment '支付时间--支付成功后的时间'")
    private LocalDateTime paySuccessTime;

    /**
     * 超时类型
     */
    @Column(name = "type", columnDefinition = "int not null default 0 comment '超时类型 0-未支付超时 1-未处理售后超时'")
    private Integer type;

    /**
     * 超时时间
     */
    @Column(name = "over_time", columnDefinition = "datetime not null comment '超时时间'")
    private LocalDateTime overTime;

	@Override
	public boolean equals(Object o) {
				if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		OrderOvertime that = (OrderOvertime) o;
		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}