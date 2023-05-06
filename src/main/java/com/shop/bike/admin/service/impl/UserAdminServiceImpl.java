package com.shop.bike.admin.service.impl;

import com.shop.bike.admin.dto.ProfileFilterDTO;
import com.shop.bike.admin.service.UserAdminService;
import com.shop.bike.admin.vm.ProfileAdminVM;
import com.shop.bike.admin.vm.ProfileConsumerForAdminVM;
import com.shop.bike.admin.vm.mapper.ProfileAdminVMMapper;
import com.shop.bike.admin.vm.mapper.ProfileConsumerForAdminVMMapper;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.entity.User;
import com.shop.bike.entity.enumeration.AuthorityType;
import com.shop.bike.entity.enumeration.ErrorEnum;
import com.shop.bike.repository.UserRepository;
import com.shop.bike.security.SecurityUtils;
import com.shop.bike.service.impl.UserServiceImpl;
import com.shop.bike.web.rest.errors.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
@Qualifier(ApplicationConstant.ADMIN)
@Slf4j
public class UserAdminServiceImpl extends UserServiceImpl implements UserAdminService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProfileAdminVMMapper profileAdminVMMapper;
	
	@Autowired
	private ProfileConsumerForAdminVMMapper profileConsumerForAdminVMMapper;
	
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
	@Override
	public ProfileAdminVM getCurrentProfileAdmin() {
		Long userId = Long.valueOf(SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.USER_NOT_FOUND)));
		return profileAdminVMMapper.toDto(this.findAdminById(userId)
				.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.USER_BLOCKED)));
	}
	
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
	@Override
	public Page<ProfileConsumerForAdminVM> findAllConsumer(ProfileFilterDTO filters, Pageable pageable) {
		return userRepository.findAll(filters, Collections.singletonList(AuthorityType.ROLE_CONSUMER.toString()), pageable)
				.map(profileConsumerForAdminVMMapper::toDto);
	}
	
	/*************************************************************
	 *
	 * @decription: get profile detail consumer
	 *
	 * @param: id
	 * @return: cm
	 *
	 * @date: 04/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 *************************************************************/
	@Override
	public ProfileConsumerForAdminVM getDetailProfileConsumer(Long id) {
		return profileConsumerForAdminVMMapper.toDto(this.findConsumerById(id)
				.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.USER_NOT_FOUND)));
	}
	
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
	@Override
	public void block(Long id, AuthorityType userRole) {
		Optional<User> profile = Optional.empty();
		switch (userRole) {
			case ROLE_CONSUMER:
				profile = findConsumerById(id);
				break;
			case ROLE_ADMIN:
				profile = findAdminById(id);
				break;
			default:
				break;
		}
		profile.ifPresent(t -> {
			t.setStatus(0); //BLOCKED
			log.debug("Block user account: userId={}", t.getId());
		});
	}
	
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
	@Override
	public void unblock(Long id, AuthorityType userRole) {
		Optional<User> profile = Optional.empty();
		switch (userRole) {
			case ROLE_CONSUMER:
				profile = findConsumerById(id);
				break;
			case ROLE_ADMIN:
				profile = findAdminById(id);
				break;
			default:
				break;
		}
		profile.ifPresent(t -> {
			t.setStatus(1); //UNBLOCKED
			log.debug("unBlock user account: userId={}", t.getId());
		});
	}
	
}
