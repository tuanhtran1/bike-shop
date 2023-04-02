package com.shop.bike.web.rest.errors;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.bike.entity.enumeration.ErrorEnum;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class AccountLoginException extends AbstractThrowableProblem {

	private static final long serialVersionUID = 1L;

	private final String entityName;

	private final String errorKey;

	public AccountLoginException() {
		this(ErrorConstants.DEFAULT_TYPE, ErrorEnum.ACCOUNT_HAS_JUST_BEEN_LOGIN_ANOTHER_DEVICE.getMessage(),
				ErrorEnum.ACCOUNT_HAS_JUST_BEEN_LOGIN_ANOTHER_DEVICE.getEntityName(),
				ErrorEnum.ACCOUNT_HAS_JUST_BEEN_LOGIN_ANOTHER_DEVICE.getErrorKey());
	}

	private AccountLoginException(URI type, String defaultMessage, String entityName, String errorKey) {
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

	public String toString() {
		Map<String, Object> params= new HashMap<>();
		params.putAll(getParameters());
		params.put("title",getTitle());
		params.put("type",getType());
		params.put("status",getStatus().getStatusCode());
		params.put("detail",getDetail());
		params.put("entityName",getEntityName());
		params.put("errorKey",getErrorKey());
		try {
			return new ObjectMapper().writeValueAsString(params);
		} catch (JsonProcessingException e) {
		}
		return null;
	}
}
