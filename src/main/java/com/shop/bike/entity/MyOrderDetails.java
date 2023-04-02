package com.shop.bike.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A MyOrderDetails.
 */
@Entity
@Table(name = "my_order_details")
@Getter
@Setter
public class MyOrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(name = "unit_price", precision = 21, scale = 2, nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "discount", precision = 21, scale = 2)
    private BigDecimal discount;

    @NotNull
    @Column(name = "total", precision = 21, scale = 2, nullable = false)
    private BigDecimal total;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "images")
    private String images;

    @NotNull
    @Column(name = "trading_product_id", nullable = false)
    private Long tradingProductId;

    @Size(max = 5000)
    @Column(name = "trading_product_cache", length = 5000)
    private String tradingProductCache;

    @Column(name = "promotion_id")
    private Long promotionId;

    @Size(max = 5000)
    @Column(name = "promotion_cache", length = 5000)
    private String promotionCache;

    @NotNull
    @Column(name = "weight", precision = 21, scale = 2, nullable = false)
    private BigDecimal weight;

    @ManyToOne
    @JsonIgnoreProperties(value = { "orderDetails" }, allowSetters = true)
    private MyOrder order;

}
