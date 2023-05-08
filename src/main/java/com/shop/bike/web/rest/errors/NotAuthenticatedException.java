package com.shop.bike.web.rest.errors;

import com.shop.bike.entity.enumeration.ErrorEnum;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class NotAuthenticatedException extends AbstractThrowableProblem {

	private static final long serialVersionUID = 1L;

	private final String entityName;

	private final String errorKey;

	public NotAuthenticatedException(URI type, String defaultMessage, String entityName, String errorKey) {
		super(type, defaultMessage, Status.BAD_REQUEST, null, null, null, getAlertParameters(entityName, errorKey));
		this.entityName = entityName;
		this.errorKey = errorKey;
	}

	public NotAuthenticatedException() {
		super(null, null, Status.UNAUTHORIZED, null, null, null, getAlertParameters(
				ErrorEnum.USER_NOT_AUTHENTICATED.getEntityName(), ErrorEnum.USER_NOT_AUTHENTICATED.getErrorKey()));
		this.entityName = ErrorEnum.USER_NOT_AUTHENTICATED.getEntityName();
		this.errorKey = ErrorEnum.USER_NOT_AUTHENTICATED.getErrorKey();
	}

	public String getEntityName() {
		return entityName;
	}

	public String getErrorKey() {
		return errorKey;
	}

	private static Map<String, Object> getAlertParameters(String entityName, String errorKey) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("message", "error" + entityName + errorKey);
		parameters.put("params", entityName);
		return parameters;
	}
}
