package com.shop.bike.web.rest.errors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Qualifier("customAccessDeniedHandler")
public class CustomAccessDeniedHandler implements AuthenticationEntryPoint, AccessDeniedHandler {
	
	private final ObjectMapper mapper;
	
	@Autowired
	public CustomAccessDeniedHandler(final ObjectMapper mapper) {
		this.mapper = mapper;
	}
	
	@Override
	public void commence(final HttpServletRequest request, final HttpServletResponse response,
						 final AuthenticationException authException) throws IOException, ServletException {
		if(request.getAttribute("AccountLoginFail")!=null) {
			throw new AccountLoginException();
		}
		throw new NotAuthenticatedException();
	}
	
	@Override
	public void handle(final HttpServletRequest request, final HttpServletResponse response,
					   final AccessDeniedException accessDeniedException) throws IOException, ServletException {
		throw new com.shop.bike.web.rest.errors.AccessDeniedException();
	}
	
}

