package com.shop.bike.admin.vm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.bike.consumer.vm.ShippingAddressVM;
import com.shop.bike.entity.enumeration.DeliveryStatus;
import com.shop.bike.entity.enumeration.OrderStatus;
import com.shop.bike.entity.enumeration.PaymentGateway;
import com.shop.bike.entity.enumeration.PaymentStatus;
import com.shop.bike.utils.JsonConverter;
import com.shop.bike.vm.MyOrderDetailsVM;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Data
public class MyOrderAdminVM {

    private Long id;

    private String code;

    private OrderStatus status;

    private PaymentGateway paymentGateway;

    private Long paymentId;

    private PaymentStatus paymentStatus;

    private DeliveryStatus deliverStatus;

    private BigDecimal subTotal;

    private BigDecimal discount;

    private BigDecimal shippingFee;

    private BigDecimal total;

    @JsonIgnore
    private String audit;

    private String buyerId;

    private String buyerName;

    @JsonIgnore
    private String sellerInfoCache;
	

    private BigDecimal couponDiscount;

    private Long couponDiscountId;

    @JsonIgnore
    private String couponDiscountCache;
	
	@JsonProperty("shippingAddress")
	private ShippingAddressVM address;

    private String note;

    private Instant createdDate;

    private Instant lastModifiedDate;

    private Instant date;

//    private Long shippingAddressId;

//    private String shippingCode;

//    private String shippingAddressCache;

    private Set<MyOrderDetailsVM> orderDetails;

    @JsonProperty("audit")
    public Object getAuditObject() {
        if(this.audit != null) return JsonConverter.toObject(this.audit, Object.class);
        return null;
    }

    @JsonProperty("couponDiscountCache")
    public Object getCouponDiscountCacheObject() {
        if(this.couponDiscountCache != null) return JsonConverter.toObject(this.couponDiscountCache, Object.class);
        return null;
    }

//    @JsonProperty("shippingCache")
//    public Object getShippingCacheObject() {
//        if(this.shippingCache != null) return JsonConverter.toObject(this.shippingCache, Object.class);
//        return null;
//    }
}
