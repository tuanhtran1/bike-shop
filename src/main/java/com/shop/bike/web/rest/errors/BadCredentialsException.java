package com.shop.bike.web.rest.errors;


import com.shop.bike.entity.enumeration.ErrorEnum;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class BadCredentialsException extends AbstractThrowableProblem {

	private static final long serialVersionUID = 1L;

	private final String entityName;

	private final String errorKey;

	public BadCredentialsException() {
		this(ErrorConstants.DEFAULT_TYPE, ErrorEnum.UNAUTHORIZED.getMessage(), ErrorEnum.UNAUTHORIZED.getEntityName(),
				ErrorEnum.UNAUTHORIZED.getErrorKey());
	}

	private BadCredentialsException(URI type, String defaultMessage, String entityName, String errorKey) {
		super(type, defaultMessage, Status.UNAUTHORIZED, null, null, null,
				getAlertParameters(entityName, errorKey, null));
		this.entityName = entityName;
		this.errorKey = errorKey;
	}

	public String getEntityName() {
		return entityName;
	}

	public String getErrorKey() {
		return errorKey;
	}

	private static Map<String, Object> getAlertParameters(String entityName, String errorKey, Object data) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("message", "error" + entityName + errorKey);
		parameters.put("params", entityName);
		parameters.put("data", data);
		return parameters;
	}
}
