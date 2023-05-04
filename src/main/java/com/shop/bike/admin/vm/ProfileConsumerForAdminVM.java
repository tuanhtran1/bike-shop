package com.shop.bike.admin.vm;

import com.shop.bike.vm.UserVM;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProfileConsumerForAdminVM extends UserVM {
	private Integer status;
	
	private Instant createdDate;
}
