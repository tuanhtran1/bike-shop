package com.shop.bike.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * A Otp.
 */
@Entity
@Table(name = "otp")
public class Otp extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "resend_otp")
    private Integer resendOtp;

    @Size(max = 10)
    @Column(name = "otp", length = 10)
    private String otp;

    @Column(name = "incorrect_otp")
    private Integer incorrectOtp;

    @Size(max = 100)
    @Column(name = "user_name", length = 100)
    private String userName;

    @Size(max = 50)
    @Column(name = "active_key", length = 50)
    private String activeKey;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Otp id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getResendOtp() {
        return this.resendOtp;
    }

    public Otp resendOtp(Integer resendOtp) {
        this.setResendOtp(resendOtp);
        return this;
    }

    public void setResendOtp(Integer resendOtp) {
        this.resendOtp = resendOtp;
    }

    public String getOtp() {
        return this.otp;
    }

    public Otp otp(String otp) {
        this.setOtp(otp);
        return this;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Integer getIncorrectOtp() {
        return this.incorrectOtp;
    }

    public Otp incorrectOtp(Integer incorrectOtp) {
        this.setIncorrectOtp(incorrectOtp);
        return this;
    }

    public void setIncorrectOtp(Integer incorrectOtp) {
        this.incorrectOtp = incorrectOtp;
    }

    public String getUserName() {
        return this.userName;
    }

    public Otp userName(String userName) {
        this.setUserName(userName);
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActiveKey() {
        return this.activeKey;
    }

    public Otp activeKey(String activeKey) {
        this.setActiveKey(activeKey);
        return this;
    }

    public void setActiveKey(String activeKey) {
        this.activeKey = activeKey;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Otp)) {
            return false;
        }
        return id != null && id.equals(((Otp) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Otp{" +
            "id=" + getId() +
            ", resendOtp=" + getResendOtp() +
            ", otp='" + getOtp() + "'" +
            ", incorrectOtp=" + getIncorrectOtp() +
            ", userName='" + getUserName() + "'" +
            ", activeKey='" + getActiveKey() + "'" +
            "}";
    }
}
