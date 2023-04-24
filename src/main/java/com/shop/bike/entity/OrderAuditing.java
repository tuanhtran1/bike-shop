package com.shop.bike.entity;


import com.shop.bike.utils.JsonConverter;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;


import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for the {@link com.malu.base.OrderAuditing.service.dto.OrderAuditingDTO} entity.
 *
 */
@Data
public class OrderAuditing implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7916013672964427680L;

	public OrderAuditing() {
		this.createdDate = this.lastModifiedDate = Instant.now();
	}

	public OrderAuditing(String json) {
		if(!StringUtils.isBlank(json)) {
			OrderAuditing object = JsonConverter.toObject(json, OrderAuditing.class);
			if(object == null) object = new OrderAuditing();
			if(!object.equals(this)) this.lastModifiedDate = Instant.now();
			this.createdDate = object.getCreatedDate();
			this.waitingDate = object.getWaitingDate();
			this.acceptedDate = object.getAcceptedDate();
			this.rejectedDate = object.getRejectedDate();
			this.canceledDate = object.getCanceledDate();
			this.deliveredDate = object.getDeliveredDate();
			this.deletedDate = object.getDeletedDate();
			this.shippingDate = object.getShippingDate();
			this.paymentDate = object.getPaymentDate();
			this.doneDate = object.getDoneDate();
			//Add more info
			
		} else {
			this.createdDate = this.lastModifiedDate = Instant.now();
		}
	}

	private Instant waitingDate;
	
	private Instant acceptedDate;
	
	private Instant rejectedDate;

	private Instant canceledDate;
	
	private Instant deliveredDate;

	private Instant deletedDate;
	
    private Instant createdDate;

    private Instant shippingDate;
	
	private Instant paymentDate;

    private Instant lastModifiedDate;
	
    private Instant doneDate;

	public String toString() {
		return JsonConverter.toJson(this);
	}
	
}
