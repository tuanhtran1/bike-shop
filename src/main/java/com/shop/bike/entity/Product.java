package com.shop.bike.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shop.bike.entity.enumeration.ActionStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name = "sequenceGenerator")
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "name", nullable = false)
    private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "old_code")
	private String oldCode;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "product_status", nullable = false)
	private ActionStatus productStatus;

	@Size(max = 10000)
	@Column(name = "description", length = 10000)
	private String description;

	@Size(max = 100000)
	@Column(name = "details", length = 100000)
	private String details;
	
	@Column(name = "images",length = 100000)
	private String images;

	@Size(max = 10000)
	@Column(name = "specifications", length = 10000)
	private String specifications;

	@Column(name = "approval_date")
	private Instant approvalDate;

	@Column(name = "approver")
	private String approver;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "origin")
	private String origin;
	
	@Column(name = "amount_orders")
	private Integer amountOrders;

	@Column(name = "min_price", precision = 21, scale = 2)
	private BigDecimal minPrice;

	@Column(name = "max_price", precision = 21, scale = 2)
	private BigDecimal maxPrice;

	@OneToMany(mappedBy = "product")
	@JsonIgnoreProperties(value = { "attributes", "product", "promotions" }, allowSetters = true)
	private Set<TradingProduct> tradingProducts = new HashSet<>();

	@ManyToOne
	private Brand brand;
}
