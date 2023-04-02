package com.shop.bike.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.shop.bike.entity.enumeration.DeliveryStatus;
import com.shop.bike.entity.enumeration.OrderStatus;
import com.shop.bike.entity.enumeration.PaymentGateway;
import com.shop.bike.entity.enumeration.PaymentStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;


/**
 * A MyOrder.
 */
@Entity
@Table(name = "my_order")
@Getter
@Setter
public class MyOrder extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_gateway")
    private PaymentGateway paymentGateway;

    @Column(name = "payment_id")
    private Long paymentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "deliver_status")
    private DeliveryStatus deliverStatus;

    @Column(name = "sub_total", precision = 21, scale = 2)
    private BigDecimal subTotal;

    @Column(name = "discount", precision = 21, scale = 2)
    private BigDecimal discount;

    @Column(name = "shipping_fee", precision = 21, scale = 2)
    private BigDecimal shippingFee;

    @Size(max = 5000)
    @Column(name = "shipping_fee_cache", length = 5000)
    private String shippingFeeCache;

    @NotNull
    @Column(name = "total", precision = 21, scale = 2, nullable = false)
    private BigDecimal total;

    @Size(max = 5000)
    @Column(name = "audit", length = 5000)
    private String audit;

    @Column(name = "buyer_id")
    private Long buyerId;
	
    @Column(name = "coupon_discount", precision = 21, scale = 2)
    private BigDecimal couponDiscount;

    @Column(name = "coupon_discount_id")
    private Long couponDiscountId;

    @Size(max = 5000)
    @Column(name = "coupon_discount_cache", length = 5000)
    private String couponDiscountCache;

    @Column(name = "note")
    private String note;

    @Column(name = "date")
    private Instant date;

    @Column(name = "shipping_code")
    private String shippingCode;

    @Column(name = "shipping_address_cache")
    private String shippingAddressCache;
	
	@OneToOne
	@JoinColumn(unique = true)
	private Address address;

    @OneToMany(mappedBy = "order")
    private Set<MyOrderDetails> orderDetails = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
}
