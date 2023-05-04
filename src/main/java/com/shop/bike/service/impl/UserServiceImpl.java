package com.shop.bike.service.impl;

import com.shop.bike.entity.User;
import com.shop.bike.entity.enumeration.AuthorityType;
import com.shop.bike.repository.UserRepository;
import com.shop.bike.service.CouponDiscountService;
import com.shop.bike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
@Primary
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	protected Optional<User> findConsumerById(Long id) {
		return userRepository.findByIdAndAuthorityAndStatus(id,
				Collections.singletonList(AuthorityType.ROLE_CONSUMER.toString()), 1);
	}
	
	protected Optional<User> findAdminById(Long id) {
		return userRepository.findByIdAndAuthorityAndStatus(id,
				Collections.singletonList(AuthorityType.ROLE_ADMIN.toString()), 1);
	}
}
