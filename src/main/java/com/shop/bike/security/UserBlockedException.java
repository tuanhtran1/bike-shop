package com.shop.bike.security;


import com.shop.bike.entity.enumeration.ErrorEnum;
import com.shop.bike.web.rest.errors.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * This exception is thrown in case of a not activated user trying to
 * authenticate.
 */
public class UserBlockedException extends AbstractThrowableProblem {

	private static final long serialVersionUID = 1L;

	private final String entityName;

	private final String errorKey;

	public UserBlockedException() {
		this(ErrorConstants.DEFAULT_TYPE, ErrorEnum.USER_BLOCKED.getMessage(),
				ErrorEnum.USER_BLOCKED.getEntityName(), ErrorEnum.USER_BLOCKED.getErrorKey());
	}

	private UserBlockedException(URI type, String defaultMessage, String entityName, String errorKey) {
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
