package com.shop.bike.service;

import com.shop.bike.entity.User;

import java.util.Optional;

public interface UserService {
	
	Optional<User> findById(Long id);

}
