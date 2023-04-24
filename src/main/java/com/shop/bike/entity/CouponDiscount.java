package com.shop.bike.entity;


import com.shop.bike.entity.enumeration.ActionStatus;
import com.shop.bike.entity.enumeration.CouponStatus;
import com.shop.bike.entity.enumeration.DiscountType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * A CouponDiscount.
 */
@Entity
@Table(name = "coupon_discount")
public class CouponDiscount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;
	
	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

    @NotNull
    @Column(name = "coupon_code", nullable = false)
    private String couponCode;

    @Column(name = "quantity_limit")
    private Integer quantityLimit;

    @NotNull
    @Column(name = "discount", precision = 21, scale = 2, nullable = false)
    private BigDecimal discount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private DiscountType type;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private CouponStatus status;

    @Column(name = "max_discount", precision = 21, scale = 2)
    private BigDecimal maxDiscount;

    @Column(name = "quantity_limit_for_user")
    private Integer quantityLimitForUser;
	
	@Column(name = "start_date")
	private Instant startDate;
	
	@Column(name = "end_date")
	private Instant endDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CouponDiscount id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCouponCode() {
        return this.couponCode;
    }

    public CouponDiscount couponCode(String couponCode) {
        this.setCouponCode(couponCode);
        return this;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Integer getQuantityLimit() {
        return this.quantityLimit;
    }

    public CouponDiscount quantityLimit(Integer quantityLimit) {
        this.setQuantityLimit(quantityLimit);
        return this;
    }

    public void setQuantityLimit(Integer quantityLimit) {
        this.quantityLimit = quantityLimit;
    }

    public BigDecimal getDiscount() {
        return this.discount;
    }

    public CouponDiscount discount(BigDecimal discount) {
        this.setDiscount(discount);
        return this;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public DiscountType getType() {
        return this.type;
    }

    public CouponDiscount type(DiscountType type) {
        this.setType(type);
        return this;
    }

    public void setType(DiscountType type) {
        this.type = type;
    }

    public BigDecimal getMaxDiscount() {
        return this.maxDiscount;
    }

    public CouponDiscount maxDiscount(BigDecimal maxDiscount) {
        this.setMaxDiscount(maxDiscount);
        return this;
    }

    public void setMaxDiscount(BigDecimal maxDiscount) {
        this.maxDiscount = maxDiscount;
    }

    public Integer getQuantityLimitForUser() {
        return this.quantityLimitForUser;
    }

    public CouponDiscount quantityLimitForUser(Integer quantityLimitForUser) {
        this.setQuantityLimitForUser(quantityLimitForUser);
        return this;
    }

    public void setQuantityLimitForUser(Integer quantityLimitForUser) {
        this.quantityLimitForUser = quantityLimitForUser;
    }
	
	public Instant getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Instant startDate) {
		this.startDate = startDate;
	}
	
	public Instant getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Instant endDate) {
		this.endDate = endDate;
	}
	
	public CouponStatus getStatus() {
		return status;
	}
	
	public void setStatus(CouponStatus status) {
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CouponDiscount)) {
            return false;
        }
        return id != null && id.equals(((CouponDiscount) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CouponDiscount{" +
            "id=" + getId() +
            ", couponCode='" + getCouponCode() + "'" +
            ", quantityLimit=" + getQuantityLimit() +
            ", discount=" + getDiscount() +
            ", type='" + getType() + "'" +
            ", maxDiscount=" + getMaxDiscount() +
            ", quantityLimitForUser=" + getQuantityLimitForUser() +
            "}";
    }
}
