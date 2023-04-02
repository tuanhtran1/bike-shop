package com.shop.bike.entity;

import com.shop.bike.entity.enumeration.ActionStatus;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A Address.
 */
@Entity
@Table(name = "address")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Address extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Size(max = 255)
    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "is_default")
    private Boolean isDefault;

    @Column(name = "is_shipping_address")
    private Boolean isShippingAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ActionStatus status;

    @Size(max = 255)
    @Column(name = "full_address", length = 255)
    private String fullAddress;

    @Size(max = 10)
    @Column(name = "user_id", length = 10)
    private String userId;

    @Column(name = "zip_code")
    private String zipCode;

    @ManyToOne
    private Country country;

    @ManyToOne
    private Province province;

    @ManyToOne
    private District district;

    @ManyToOne
    private Wards wards;
	
	@OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
	private MyOrder myOrder;
	

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Address id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }

    public Address address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getIsDefault() {
        return this.isDefault;
    }

    public Address isDefault(Boolean isDefault) {
        this.setIsDefault(isDefault);
        return this;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }
	

    public ActionStatus getStatus() {
        return this.status;
    }

    public Address status(ActionStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(ActionStatus status) {
        this.status = status;
    }

    public String getFullAddress() {
        return this.fullAddress;
    }

    public Address fullAddress(String fullAddress) {
        this.setFullAddress(fullAddress);
        return this;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getUserId() {
        return this.userId;
    }

    public Address userId(String userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public Address zipCode(String zipCode) {
        this.setZipCode(zipCode);
        return this;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

	public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Address country(Country country) {
        this.setCountry(country);
        return this;
    }

    public Province getProvince() {
        return this.province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Address province(Province province) {
        this.setProvince(province);
        return this;
    }

    public District getDistrict() {
        return this.district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Address district(District district) {
        this.setDistrict(district);
        return this;
    }

    public Wards getWards() {
        return this.wards;
    }

    public void setWards(Wards wards) {
        this.wards = wards;
    }

    public Address wards(Wards wards) {
        this.setWards(wards);
        return this;
    }

    public Boolean getIsShippingAddress() {
		return isShippingAddress;
	}

	public void setIsShippingAddress(Boolean isShippingAddress) {
		this.isShippingAddress = isShippingAddress;
	}
	
	public MyOrder getMyOrder() {
		return myOrder;
	}
	
	public void setMyOrder(MyOrder myOrder) {
		this.myOrder = myOrder;
	}
	
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }
        return id != null && id.equals(((Address) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Address{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            ", isDefault='" + getIsDefault() + "'" +
            ", status='" + getStatus() + "'" +
            ", fullAddress='" + getFullAddress() + "'" +
            ", userId='" + getUserId() + "'" +
            ", zipCode='" + getZipCode() + "'" +
            "}";
    }
}
