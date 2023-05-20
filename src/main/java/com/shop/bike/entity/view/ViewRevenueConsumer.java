package com.shop.bike.entity.view;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "v_shop_revenue_statistic_by_consumer")
@Data
public class ViewRevenueConsumer {
	
	@Id
	private Long id;
	
	private Long buyerId;
	
	private String buyerName;
	
	private Integer orderQuantity;
	
	private Integer productQuantity;
	
	private Integer promotionDiscount;
	
	private BigDecimal shippingFee;
	
	private BigDecimal subTotal;
	
	private BigDecimal revenue;
	
//	private Instant createdDate;
	
}
