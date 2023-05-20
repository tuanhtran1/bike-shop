package com.shop.bike.entity;

import com.shop.bike.entity.enumeration.WareHouseStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;


/**
 * A MyOrder.
 */
@Entity
@Table(name = "ware_house")
@Getter
@Setter
public class WareHouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private WareHouseStatus status;
	
	@Column(name = "origin_price", precision = 21, scale = 2)
	private BigDecimal originPrice;

    @Column(name = "quantity")
    private Integer quantity;
	
	@Column(name = "trading_product_id")
	private Long tradingProductId;
	
	@Column(name = "date")
	private Instant date;
}
