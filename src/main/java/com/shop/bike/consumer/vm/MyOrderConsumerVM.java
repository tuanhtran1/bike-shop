package com.shop.bike.consumer.vm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.bike.entity.enumeration.DeliveryStatus;
import com.shop.bike.entity.enumeration.OrderStatus;
import com.shop.bike.entity.enumeration.PaymentGateway;
import com.shop.bike.entity.enumeration.PaymentStatus;
import com.shop.bike.utils.JsonConverter;
import com.shop.bike.vm.CouponDiscountVM;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Getter
@Setter
public class MyOrderConsumerVM {

    private Long id;
	
    private String code;
    
//	private OrderStatusUIFilter uiStatus;

    private OrderStatus status;

    private PaymentGateway paymentGateway;

    private Long paymentId;

    private PaymentStatus paymentStatus;

    private DeliveryStatus deliverStatus;

    private BigDecimal subTotal;

    private BigDecimal discount;

    private BigDecimal shippingFee;

    private BigDecimal total;

    @JsonProperty("audit")
    private Object auditObject;
    @JsonIgnore
    private String audit;
	
    private String buyerId;

    private String buyerName;

    private BigDecimal couponDiscount;

    private Long couponDiscountId;

    @JsonIgnore
    private String couponDiscountCache;

    @JsonIgnore
    private String shippingCache;
	
    private String note;

    private Instant createdDate;

    private Instant date;
    
    private Long shippingAddressId;
    
    private Set<MyOrderDetailsConsumerVM> orderDetails;
    
    @JsonIgnore
    private String shippingAddressCache;
	
    @JsonProperty("couponDiscountCache")
	public CouponDiscountVM getCouponDiscountCacheObject() {
		return JsonConverter.toObject(this.couponDiscountCache, CouponDiscountVM.class);
	}

    @JsonProperty("shippingCache")
	public Object getShippingCacheObject() {
		return JsonConverter.toObject(this.shippingCache, Object.class);
	}

    @JsonProperty("shippingAddress")
    public Object getshippingAddressObject() {
    	if(this.shippingAddressCache != null) return JsonConverter.toObject(this.shippingAddressCache, Object.class);
    	return null;
    }
    
    public void setAudit(String audit) {
    	this.audit = audit;
    	if(StringUtils.isNotBlank(audit)) {
    		this.auditObject = JsonConverter.toObject(audit, Object.class);
    	}
    }
    
}
