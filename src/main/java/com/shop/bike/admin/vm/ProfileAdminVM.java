package com.shop.bike.admin.vm;

import com.shop.bike.vm.UserVM;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProfileAdminVM  extends UserVM {
	private Integer status;
	
}
