package com.shop.bike.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailRequestDTO {
	private String name;
	private String to;
	private String from;
	private String subject;
}
