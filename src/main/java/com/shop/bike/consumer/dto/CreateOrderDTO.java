package com.shop.bike.consumer.dto;

import com.shop.bike.entity.enumeration.PaymentGateway;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreateOrderDTO {
	
	private Long id;
	@NotNull
	private AddressDTO address;
	
	private String couponCode;
	
	private String note;
	
//	@NotNull
//	private PaymentGateway paymentGateway;
	
	@NotNull
	private BigDecimal shippingFee;
	
	private BigDecimal discount;
	
	@NotEmpty
	private List<CreateOrderDetailDTO> orderDetails = new ArrayList<>();
	
	public void addOrderDetail(List<CreateOrderDetailDTO> orderDetails) {
		this.orderDetails.addAll(orderDetails);
	}
}
