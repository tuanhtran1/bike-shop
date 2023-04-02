package com.shop.bike.web.rest.errors;

import com.shop.bike.entity.enumeration.ErrorEnum;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class AccessDeniedException extends AbstractThrowableProblem {

	private static final long serialVersionUID = 1L;

	private final String entityName;

	private final String errorKey;

	public AccessDeniedException(URI type, String defaultMessage, String entityName, String errorKey) {
		super(type, defaultMessage, Status.BAD_REQUEST, null, null, null, getAlertParameters(entityName, errorKey));
		this.entityName = entityName;
		this.errorKey = errorKey;
	}

	public AccessDeniedException() {
		super(null, null, Status.FORBIDDEN, null, null, null, getAlertParameters(
				ErrorEnum.USER_ACCESS_DENIED.getEntityName(), ErrorEnum.USER_ACCESS_DENIED.getErrorKey()));
		this.entityName = ErrorEnum.USER_ACCESS_DENIED.getEntityName();
		this.errorKey = ErrorEnum.USER_ACCESS_DENIED.getErrorKey();
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
