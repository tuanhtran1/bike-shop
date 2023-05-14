package com.shop.bike.consumer.repository;

import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.entity.CouponDiscount;
import com.shop.bike.repository.CouponDiscountRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(ApplicationConstant.CONSUMER)
public interface CouponDiscountConsumerRepository extends CouponDiscountRepository {
	
	@Query("select c from CouponDiscount c where c.status<>'DELETED' "
			+ "and ((:keyword is null) or (lower(unaccent(c.name)) like concat('%',lower(unaccent(:keyword)),'%'))) "
			+ "and ((c.endDate is null and c.startDate <= now()) "
			+ "or (c.endDate is not null and now() between c.startDate and c.endDate)) "
	)
	Page<CouponDiscount> findAll(@Param("keyword") String keyword, Pageable pageable);
}
