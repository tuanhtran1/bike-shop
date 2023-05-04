package com.shop.bike.repository;

import com.shop.bike.admin.dto.MyOrderFilterDTO;
import com.shop.bike.entity.MyOrder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface MyOrderRepository extends JpaRepository<MyOrder, Long> {
	
	@Query("from MyOrder o where o.status <>'DELETED' "
			+ "and (COALESCE(:#{#filters.ignoreOrderStatus}, null) is null or o.status not in :#{#filters.ignoreOrderStatus}) "
			+ "and ((:#{#filters.keyword} is null) or (lower(unaccent(o.code)) like concat('%',lower(unaccent(:#{#filters.keyword})),'%')) "
			+ "or (o.shippingCode like concat('%',lower(unaccent(:#{#filters.keyword})),'%'))) "
			+ "and ((:#{#filters.totalFrom} is null) or (o.total >=:#{#filters.totalFrom})) "
			+ "and ((:#{#filters.totalTo} is null) or (o.total <=:#{#filters.totalTo})) "
			+ "and ((:#{#filters.buyerId} is null) or (o.buyerId =:#{#filters.buyerId})) "
			+ "and ((:#{#filters.paymentId} is null) or (o.paymentId =:#{#filters.paymentId}))"
			+ "and ((:#{#filters.status} is null) or (o.status =:#{#filters.status})) "
			+ "and (:#{#filters.createdDateFrom} is null or o.createdDate>=:#{#filters.createdDateFrom}) "
			+ "and (:#{#filters.createdDateTo} is null or o.createdDate<=:#{#filters.createdDateTo}) "
			+ "and (:#{#filters.lastModifiedDateFrom} is null or o.lastModifiedDate>=:#{#filters.lastModifiedDateFrom}) "
			+ "and (:#{#filters.lastModifiedDateTo} is null or o.lastModifiedDate<=:#{#filters.lastModifiedDateTo}) "
			+ "and ((:#{#filters.code} is null) or (lower(unaccent(o.code)) like concat('%',lower(unaccent(:#{#filters.code})),'%'))) ")
	Page<MyOrder> findAll(@Param("filters") MyOrderFilterDTO filters, Pageable pageable);
}
