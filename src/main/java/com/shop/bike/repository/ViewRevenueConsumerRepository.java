package com.shop.bike.repository;

import com.shop.bike.entity.view.ViewRevenueConsumer;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
@Primary
public interface ViewRevenueConsumerRepository extends JpaRepository<ViewRevenueConsumer, Long> {
	
	@Query(value = "select v from ViewRevenueConsumer v where 1 = 1 "
//			+ "and (:fromDate is null or v.createdDate>=:fromDate) "
//			+ "and (:toDate is null or v.createdDate<=:toDate) "
			+ "")
	Page<ViewRevenueConsumer> getViewRevenueConsumer(
//			 										 @Param("fromDate") Instant fromDate,
//													 @Param("toDate") Instant toDate,
													 Pageable pageable);
}
