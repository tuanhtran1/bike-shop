package com.shop.bike.consumer.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.bike.admin.service.UserAdminService;
import com.shop.bike.admin.vm.ProfileAdminVM;
import com.shop.bike.config.Constants;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.consumer.dto.OtpPayload;
import com.shop.bike.consumer.dto.ProfileConsumerDTO;
import com.shop.bike.consumer.dto.RegisterConsumerDTO;
import com.shop.bike.consumer.service.UserConsumerService;
import com.shop.bike.consumer.vm.ProfileConsumerVM;
import com.shop.bike.entity.enumeration.AuthorityType;
import com.shop.bike.entity.enumeration.ErrorEnum;
import com.shop.bike.security.jwt.JwtAuthenticationFilter;
import com.shop.bike.security.jwt.TokenProvider;
import com.shop.bike.service.OtpService;
import com.shop.bike.utils.Utils;
import com.shop.bike.vm.LoginVM;
import com.shop.bike.web.rest.errors.BadRequestAlertException;
import com.shop.bike.web.rest.errors.UsernameNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/consumer")
@Slf4j
public class UserConsumerResource {

	private final AuthenticationManager authenticationManager;

	private final TokenProvider jwtTokenUtil;
	
	@Autowired
	@Qualifier(ApplicationConstant.CONSUMER)
	private UserConsumerService userConsumerService;
	
	@Autowired
	private OtpService otpService;

	public UserConsumerResource(AuthenticationManager authenticationManager, TokenProvider jwtTokenUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<JWTToken> register(@RequestBody @Valid LoginVM login) throws AuthenticationException {

		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						login.getUsername(),
						login.getPassword()
				)
		);
		boolean isConsumer = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.anyMatch(AuthorityType.ROLE_CONSUMER.toString()::equals);
		if(!isConsumer) throw new UsernameNotFoundException(ErrorEnum.UNAUTHORIZED, null);
		
		final String token = jwtTokenUtil.generateToken(authentication);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtAuthenticationFilter.AUTHORIZATION_HEADER, "Bearer " + token);
		return new ResponseEntity<>(new JWTToken(token), httpHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/register/check/{email}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> checkEmail(@PathVariable(name = "email") @Valid @Pattern(regexp = Constants.LOGIN_REGEX) String email){
		if (!Utils.isValidateEmail(email)) {
			throw new BadRequestAlertException(ErrorEnum.EMAIL_FORMATTED_INCORRECTLY);
		}
		userConsumerService.checkExist(email);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping({"/otp/init/{loginName}","/otp/resend/{loginName}"})
	public ResponseEntity<Void> initOTP(@PathVariable("loginName") String loginName) {
		log.debug("REST request to init OTP");
		otpService.sendOtp(loginName);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/otp/validate/{loginName}")
	public ResponseEntity<Map<String, String>> validateOTP(@Valid @RequestBody OtpPayload dto,
														   @PathVariable("loginName") String loginName) {
		log.debug("REST request to validate OTP");
		Map<String, String> result = otpService.validateOtp(dto.getOtp(), loginName);
		return ResponseEntity.ok().body(result);
	}
	
	@PostMapping("/register")
	public ResponseEntity<ProfileConsumerVM> registerConsumer(@Valid @RequestBody RegisterConsumerDTO dto){
		log.debug("REST request to register profile consumer");
		ProfileConsumerVM profileConsumerVM = userConsumerService.createProfileConsumer(dto);
		return ResponseEntity.ok().body(profileConsumerVM);
	}
	
	@GetMapping("/profiles")
	public ResponseEntity<ProfileConsumerVM> getProfileConsumer() {
		log.debug("REST request to get profile of Consumers");
		return ResponseEntity.ok().body(userConsumerService.getCurrentProfileConsumer());
	}
	
	@PutMapping("/profiles")
	public ResponseEntity<ProfileConsumerVM> updateProfile(@Valid @RequestBody ProfileConsumerDTO dto){
		log.debug("REST request to register profile consumer");
		ProfileConsumerVM profileConsumerVM = userConsumerService.updateProfile(dto);
		return ResponseEntity.ok().body(profileConsumerVM);
	}
	
	/**
	 * Object to return as body in JWT Authentication.
	 */
	static class JWTToken {
		
		private String idToken;
		
		JWTToken(String idToken) {
			this.idToken = idToken;
		}
		
		@JsonProperty("token")
		String getIdToken() {
			return idToken;
		}
		
		void setIdToken(String idToken) {
			this.idToken = idToken;
		}
	}
}
