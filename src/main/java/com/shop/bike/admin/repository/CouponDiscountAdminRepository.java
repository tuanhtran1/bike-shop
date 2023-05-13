package com.shop.bike.admin.repository;

import com.shop.bike.admin.dto.CouponDiscountFiltersDTO;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.entity.CouponDiscount;
import com.shop.bike.entity.enumeration.CouponStatus;
import com.shop.bike.repository.CouponDiscountRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier(ApplicationConstant.ADMIN)
public interface CouponDiscountAdminRepository extends CouponDiscountRepository {

	List<CouponDiscount> findAllByStatusNot(CouponStatus status);
	
	@Query("select c from CouponDiscount c where c.status<>'DELETED' "
			+ "and ((:#{#filters.keyword} is null) "
			+ "or (lower(unaccent(c.name)) like concat('%',lower(unaccent(:#{#filters.keyword})),'%')) "
			+ "or (lower(unaccent(c.couponCode)) like concat('%',lower(unaccent(:#{#filters.keyword})),'%'))) "
			+ "and ((:#{#filters.couponCode} is null) or (c.couponCode like %:#{#filters.couponCode}%)) "
			+ "and ((:#{#filters.status} is null) or (c.status =:#{#filters.status})) "
			+ "and ((:#{#filters.name} is null) or (lower(unaccent(c.name)) like concat('%',lower(unaccent(:#{#filters.name})),'%'))) "
			+ "and (:#{#filters.endDateFrom} is null or c.endDate>=:#{#filters.endDateFrom}) "
			+ "and (:#{#filters.endDateTo} is null or c.endDate<=:#{#filters.endDateTo}) "
			+ "and (:#{#filters.startDateFrom} is null or c.startDate>=:#{#filters.startDateFrom}) "
			+ "and (:#{#filters.startDateTo} is null or c.startDate<=:#{#filters.startDateTo}) "
	)
	Page<CouponDiscount> findAll(@Param("filters") CouponDiscountFiltersDTO filters, Pageable pageable);
}
