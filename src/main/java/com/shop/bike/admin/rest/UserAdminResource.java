package com.shop.bike.admin.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.bike.admin.dto.ProfileFilterDTO;
import com.shop.bike.admin.service.UserAdminService;
import com.shop.bike.admin.vm.ProfileAdminVM;
import com.shop.bike.admin.vm.ProfileConsumerForAdminVM;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.entity.enumeration.AuthorityType;
import com.shop.bike.entity.enumeration.ErrorEnum;
import com.shop.bike.security.jwt.JwtAuthenticationFilter;
import com.shop.bike.security.jwt.TokenProvider;
import com.shop.bike.utils.PaginationUtil;
import com.shop.bike.vm.LoginVM;
import com.shop.bike.web.rest.errors.UsernameNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/v1/admin")
@Slf4j
public class UserAdminResource {
	

	private final AuthenticationManager authenticationManager;

	private final TokenProvider jwtTokenUtil;
	
	@Autowired
	@Qualifier(ApplicationConstant.ADMIN)
	private UserAdminService userAdminService;

	public UserAdminResource(AuthenticationManager authenticationManager, TokenProvider jwtTokenUtil) {
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
		boolean isAdmin = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.anyMatch(AuthorityType.ROLE_ADMIN.toString()::equals);
		if(!isAdmin) throw new UsernameNotFoundException(ErrorEnum.UNAUTHORIZED, null);
		
		final String token = jwtTokenUtil.generateToken(authentication);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtAuthenticationFilter.AUTHORIZATION_HEADER, "Bearer " + token);
		return new ResponseEntity<>(new JWTToken(token), httpHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/profiles")
	public ResponseEntity<ProfileAdminVM> getProfileAdmin() {
		log.debug("REST request to get profile of Consumers");
		return ResponseEntity.ok().body(userAdminService.getCurrentProfileAdmin());
	}
	
	@GetMapping("/consumer/profiles")
	public ResponseEntity<List<ProfileConsumerForAdminVM>> findAllConsumer(ProfileFilterDTO filters, Pageable pageable) {
		Page<ProfileConsumerForAdminVM> page = userAdminService.findAllConsumer(filters, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}
	
	@GetMapping("/consumer/{id}")
	public ResponseEntity<ProfileConsumerForAdminVM> getDetailProfileConsumer(@PathVariable Long id) {
		log.debug("REST detail profile Consumer list");
		ProfileConsumerForAdminVM profile = userAdminService.getDetailProfileConsumer(id);
		return ResponseEntity.ok().body(profile);
	}
	
	@PutMapping(path = "/consumer/account/block/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> blockConsumerAccount(@PathVariable("id") Long id) {
		userAdminService.block(id, AuthorityType.ROLE_CONSUMER);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(path = "/consumer/account/unblock/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> unblockConsumerAccount(@PathVariable("id") Long id) {
		userAdminService.unblock(id, AuthorityType.ROLE_CONSUMER);
		return ResponseEntity.noContent().build();
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
