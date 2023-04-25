package com.shop.bike.repository;

import com.shop.bike.entity.CouponDiscount;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface CouponDiscountRepository extends JpaRepository<CouponDiscount, Long> {

}
