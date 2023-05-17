package com.shop.bike.admin.pojo;

public interface StatisticOrder {
	
	Long getTotalOrder();
	
	Long getTotalWaiting();
	
	Long getTotalAccepted();
	
	Long getTotalShipping();
	
	Long getTotalCanceled();
	
	Long getTotalRejected();
	
	Long getTotalDone();
	
	Long getTotalDonePrice();
}
