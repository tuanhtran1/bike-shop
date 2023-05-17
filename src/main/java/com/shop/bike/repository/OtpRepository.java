package com.shop.bike.repository;

import com.shop.bike.entity.Address;
import com.shop.bike.entity.Otp;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Primary
public interface OtpRepository extends JpaRepository<Otp, Long> {
	
	Optional<Otp> findByUserName(String userName);
	
	Optional<Otp> findByUserNameAndActiveKey(String userName, String activationKey);
}
