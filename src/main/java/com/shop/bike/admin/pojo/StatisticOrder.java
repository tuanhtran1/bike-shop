package com.shop.bike.admin.pojo;

public interface StatisticOrder {
	
	Long getTotalOrder();
	
	Long getTotalWaiting();
	
	Long getTotalAccepted();
	
	Long getTotalCanceled();
	
	Long getTotalRejected();
	
	Long getTotalDone();
	
	Long getTotalDonePrice();
}
