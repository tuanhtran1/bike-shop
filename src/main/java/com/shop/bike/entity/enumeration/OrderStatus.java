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

    //This status is used after technician confirm received order
    DONE,
    
    ACCEPTED,
    REJECTED,
    FULFILLED,
    CANCELED,

    SHIPPING,
    DELIVERED,
    DELETED,
    PACKAGED, 
    
    
}
