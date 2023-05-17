package com.shop.bike.service.impl;

import com.shop.bike.entity.Otp;
import com.shop.bike.entity.enumeration.ErrorEnum;
import com.shop.bike.repository.OtpRepository;
import com.shop.bike.service.AddressService;
import com.shop.bike.service.MailService;
import com.shop.bike.service.OtpService;
import com.shop.bike.service.dto.MailRequestDTO;
import com.shop.bike.service.dto.OtpDTO;
import com.shop.bike.service.dto.mapper.OtpMapper;
import com.shop.bike.utils.Utils;
import com.shop.bike.web.rest.errors.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@Primary
@Slf4j
public class OtpServiceImpl implements OtpService {
	
	@Autowired
	private OtpRepository otpRepository;
	
	@Autowired
	private OtpMapper otpMapper;
	
	@Autowired
	private MailService mailService;
	
	@Override
	public void sendOtp(String username) {
		Optional<Otp> optOTP = otpRepository.findByUserName(username);
		OtpDTO otpDTO = null;
		if (!optOTP.isPresent()) {
			otpDTO = new OtpDTO();
			otpDTO.setUserName(username);
		} else {
			Otp otp = optOTP.get();
			otpDTO = otpMapper.toDto(otp);
		}
		otpDTO.setResendOtp(otpDTO.getResendOtp() + 1);
		otpDTO.setOtp(Utils.generateOtp());
		log.debug("Init Otp: {}, for user: {}", otpDTO.getOtp(), username);
		MailRequestDTO mailRequestDTO = MailRequestDTO.builder()
				.to(username)
				.from("admin")
				.name("New member")
				.subject("Register")
				.build();
		Map<String, Object> model = new HashMap<>();
		model.put("otp", otpDTO.getOtp());
		mailService.sendEmail(mailRequestDTO, model);
		otpRepository.save(otpMapper.toEntity(otpDTO));
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = BadRequestAlertException.class)
	public Map<String, String> validateOtp(String otpCode, String username) {
		log.debug("Request to validate otp");
		Map<String, String> activeKey = new HashMap<>();
		Optional<Otp> optOTP = otpRepository.findByUserName(username);
		if (optOTP.isEmpty()) {
			throw new BadRequestAlertException(ErrorEnum.ACCOUNT_NOT_FOUND);
		}
		Otp otp = optOTP.get();
		if (otpCode.equals(otp.getOtp())) {
			otp.setIncorrectOtp(0);
			otp.setActiveKey(generateRandomAlphanumericString());
			activeKey.put("resetKey", otp.getActiveKey());
			otpRepository.save(otp);
		}else {
			otp.setIncorrectOtp(otp.getIncorrectOtp() == null ? 1 : otp.getIncorrectOtp() + 1);
			otpRepository.saveAndFlush(otp);
			throw new BadRequestAlertException(ErrorEnum.OTP_INCORRECT);
		}
		return activeKey;
	}
	
	private static final SecureRandom SECURE_RANDOM;
	
	static {
		SECURE_RANDOM = new SecureRandom();
		SECURE_RANDOM.nextBytes(new byte[64]);
	}
	
	public static String generateRandomAlphanumericString() {
		return RandomStringUtils.random(20, 0, 0, true, true, null, SECURE_RANDOM);
	}
}
