package com.shop.bike.admin.repository;

import com.shop.bike.admin.pojo.StatisticOrder;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.repository.MyOrderRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
@Qualifier(ApplicationConstant.ADMIN)
public interface MyOrderAdminRepository extends MyOrderRepository {
	
	@Query(value = " SELECT count(*) totalOrder," +
			"coalesce(sum(CASE when o.status like 'WAITING' THEN 1 ELSE 0 END), 0) totalWaiting, " +
			"coalesce(sum(CASE when o.status like 'ACCEPTED' THEN 1 ELSE 0 END), 0) totalAccepted, " +
			"coalesce(sum(CASE WHEN o.status like 'CANCELED' THEN 1 ELSE 0 END), 0) totalCanceled, " +
			"coalesce(sum(CASE WHEN o.status like 'REJECTED' THEN 1 ELSE 0 END), 0) totalRejected, " +
			"coalesce(sum(CASE WHEN o.status like 'DONE' THEN 1 ELSE 0 END), 0) totalDone, " +
			"coalesce(sum(CASE WHEN o.status like 'DONE' THEN o.total ELSE 0 END), 0) totalDonePrice " +
			"FROM my_order AS o where 1 = 1 " +
			"AND (CAST(:fromDate AS CHAR(10)) IS NULL OR o.created_date >= CAST(:fromDate AS DATE)) " +
			"AND (CAST(:toDate AS CHAR(10)) IS NULL OR o.created_date <= CAST(:toDate AS DATE))"
			+ "",
			nativeQuery = true)
	StatisticOrder orderStatistic(@Param("fromDate") Instant fromDate,
								  @Param("toDate") Instant toDate);
}
