package com.shop.bike.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shop.bike.entity.enumeration.ActionStatus;
import com.shop.bike.entity.enumeration.ApproveStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A TradingProduct.
 */
@Entity
@Table(name = "trading_product")
@Getter
@Setter
public class TradingProduct extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "price", precision = 21, scale = 2)
    private BigDecimal price;

    @Size(max = 1000)
    @Column(name = "media", length = 5000)
    private String media;

    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name = "low_stock_threshold")
    private Integer lowStockThreshold;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ActionStatus status;

    @Column(name = "note")
    private String note;

    @Column(name = "is_feature")
    private Boolean isFeature;

    @Column(name = "is_new")
    private Boolean isNew;

	@Column(name = "brand_id")
	private Long brandId;

    @Column(name = "original_price", precision = 21, scale = 2)
    private BigDecimal originalPrice;

    @Column(name = "approval_date")
    private Instant approvalDate;

    @Column(name = "approver")
    private String approver;

    @Enumerated(EnumType.STRING)
    @Column(name = "approve_status")
    private ApproveStatus approveStatus;

//    @Column(name = "quantity_min", nullable = false)
//    private Double quantityMin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "tradingProducts", "brand"}, allowSetters = true)
    private Product product;
	
	@OneToMany(mappedBy = "tradingProduct")
	@JsonIgnoreProperties(value = { "tradingProduct" }, allowSetters = true)
	private Set<ProductAttribute> attributes = new HashSet<>();

}
