package com.shop.bike.admin.service;

import com.shop.bike.admin.dto.ProfileFilterDTO;
import com.shop.bike.admin.vm.ProfileAdminVM;
import com.shop.bike.admin.vm.ProfileConsumerForAdminVM;
import com.shop.bike.entity.enumeration.AuthorityType;
import com.shop.bike.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserAdminService extends UserService {
	
	/*************************************************************
	 *
	 * @decription: get current detail user
	 *
	 * @param: token when authenticate
	 * @return: profile vm
	 *
	 * @date: 04/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	ProfileAdminVM getCurrentProfileAdmin();
	
	/*************************************************************
	 *
	 * @decription: get list consumer for admin
	 *
	 * @param: filters, pageable
	 * @return: page
	 *
	 * @date: 04/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	Page<ProfileConsumerForAdminVM> findAllConsumer(ProfileFilterDTO filters, Pageable pageable);
	
	/*************************************************************
	 *
	 * @decription: get profile detail consumer
	 *
	 * @param: id
	 * @return: cm
	 *
	 * @date: 04/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	ProfileConsumerForAdminVM getDetailProfileConsumer(Long id);
	
	/*************************************************************
	 *
	 * @decription: block user
	 *
	 * @param: id, userRole
	 * @return: none
	 *
	 * @date: 04/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	void block(Long id, AuthorityType userRole);
	
	/*************************************************************
	 *
	 * @decription: block user
	 *
	 * @param: id, userRole
	 * @return: none
	 *
	 * @date: 04/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	void unblock(Long id, AuthorityType userRole);
}
