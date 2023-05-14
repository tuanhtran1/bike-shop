package com.shop.bike.repository;

import com.shop.bike.entity.CouponDiscount;
import com.shop.bike.entity.enumeration.CouponStatus;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Primary
public interface CouponDiscountRepository extends JpaRepository<CouponDiscount, Long> {
	
	Optional<CouponDiscount> findByIdAndStatusNot(Long id, CouponStatus status);
	
	@Query("select p from CouponDiscount p where p.status<>'DELETED'  "
			+ " and :code is null or p.couponCode like :code"
			+ " and ((p.endDate is null and p.startDate <= now()) "
			+ " or (p.endDate is not null and now() between p.startDate and p.endDate)) "
	)
	Optional<CouponDiscount> getRunningCouponByCode(@Param("code") String code);
}
