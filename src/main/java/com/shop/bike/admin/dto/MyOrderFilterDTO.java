package com.shop.bike.admin.dto;

import com.shop.bike.entity.enumeration.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class MyOrderFilterDTO {
	
	private Long id;
	
	private String keyword;
	
	private String buyerId;
	
	private BigDecimal totalFrom;
	
	private BigDecimal totalTo;
	
	private String code;
	
	private OrderStatus status;
	
	private Long paymentId;
	
	private Instant date;
	
	private Instant createdDateFrom;
	
	private Instant createdDateTo;
	
	private Instant lastModifiedDateFrom;
	
	private Instant lastModifiedDateTo;
	
	private String buyerName;
	
	private List<OrderStatus> ignoreOrderStatus;
	
	private String sellerName;
}
