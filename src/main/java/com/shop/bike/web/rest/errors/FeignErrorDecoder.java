package com.shop.bike.web.rest.errors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FeignErrorDecoder implements ErrorDecoder {
	private final Logger log = LoggerFactory.getLogger(FeignErrorDecoder.class);

	@Override
	public Exception decode(String methodKey, Response response) {
		ResponseBody reponseBody = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		switch (response.status()) {
		case 403:
		case 401:
			return new AccessDeniedException();
		case 404:	
			return new ResponseStatusException(HttpStatus.NOT_FOUND, methodKey);
		case 400:

			try {

				reponseBody = objectMapper.readValue(response.body().asInputStream().readAllBytes(),
						new TypeReference<ResponseBody>() {
						});
				return new BadRequestAlertException(reponseBody.getMessage(), reponseBody.getEntityName(),
						reponseBody.getErrorKey());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			return new Exception(response.reason());
		default:
			String message=null;
			try {
				message = new String(response.body().asInputStream().readAllBytes());
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			return new Exception(message);
		}
	}

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	static class ResponseBody {
		private String entityName;
		private String errorKey;
		private String type;
		private String title;
		private String data;
		private Integer status;
		private String message;
		private String params;
		private String detail;

	}

	@Data
	@JsonIgnoreProperties(ignoreUnknown = true)
	static class ResponseBody400 {
		private String entityName;
		private String errorKey;
		private String type;
		private String title;
		private String data;
		private Integer status;
		private String message;
		private String params;
		private String detail;

	}

}
