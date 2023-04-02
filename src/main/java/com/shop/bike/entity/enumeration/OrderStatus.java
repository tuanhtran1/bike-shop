package com.shop.bike.entity.enumeration;

/**
 * The OrderStatus enumeration.
 */
public enum OrderStatus {
	//DRAFT  	init status when creating a new order
    WAITING, 							
    
    //This status is used after processing an order and waiting approval by admin (bank transfer)
    WAITING_BANK_TRANSFERRED_CONFIRM,	
    
    //This status is used after initialize a bank transfer transaction
    WAITING_BANK_TRANSFER, 

    //This status is used after processing payment successfully (after creating cash payment, after approving transfer transaction	by admin, ...)		
    PAYMENT_RECEIVED_BY_ECARAID, 

    //This status is used after technician confirm received order
    DONE,
    
    //This status is used when creating Viettel Post order failed.
    UNABLE_TO_DELIVER,
    
    //This status is used when receiving the webhook response is delayed more than 1 minute
    VIETTEL_POST_PENDING,

    //This status is used when VTP can't delivery the order
    SUCCESSFUL_DELIVERING_BACK_TO_SUPPLIER, 
    
    ACCEPTED,
    ACCEPTED_BY_VENDOR,		//This status is used when vendor create an order for their customer
    REJECTED,
    FULFILLED,
    CANCELED,
    REQUESTED_RETURN,
    RETURNED,
    SHIPPING,
    DELIVERED,
    DELETED,
    PACKAGED, 
    
    
}
