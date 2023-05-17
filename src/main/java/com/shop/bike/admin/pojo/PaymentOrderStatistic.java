/**
 * 
 */
package com.shop.bike.admin.pojo;

import java.math.BigDecimal;

/**
 * @author tu.tran
 *
 */
public interface PaymentOrderStatistic {
	
	public Integer getTotalTransaction();
	
	public BigDecimal getTotalTransactionAmount();
	
	public Integer getSuccessTransaction();
	
	public BigDecimal getSuccessTransactionAmount();

	public Integer getPendingTransaction();
	
	public BigDecimal getPendingTransactionAmount();
	
}
