package com.shop.bike.web.rest;


import com.shop.bike.admin.service.MediaAdminService;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.service.MailService;
import com.shop.bike.service.dto.MailRequestDTO;
import com.shop.bike.utils.ResourceSerializable;
import com.shop.bike.vm.MailResponseVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/global")
public class GlobalResource {
	private final Logger log = LoggerFactory.getLogger(GlobalResource.class);
	
	private final MediaAdminService mediaAdminService;
	
	private final MailService mailService;
	
	public GlobalResource(@Qualifier(ApplicationConstant.ADMIN) MediaAdminService mediaAdminService,
						  MailService mailService) {
		this.mediaAdminService = mediaAdminService;
		this.mailService = mailService;
	}
	
	@GetMapping(value = "/{folder}/{mediaType}/{imageName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<ResourceSerializable> getFile(@PathVariable(name = "folder", required = true) String folder,
														@PathVariable(name = "mediaType", required = true) String mediaType,
														@PathVariable(name = "imageName", required = true) String imageName,
														@RequestParam(name = "width", required = false) Integer width) {
		log.debug("REST request to get image: {}", imageName);
		ResourceSerializable resource = mediaAdminService.getFile(folder, mediaType, imageName, width);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@PostMapping("/send-mail")
	public ResponseEntity<MailResponseVM> sendMail(@RequestBody MailRequestDTO mailRequestDTO){
		Map<String, Object> model = new HashMap<>();
		model.put("otp", "1234567");
		MailResponseVM vm = mailService.sendEmail(mailRequestDTO, model);
		return ResponseEntity.ok(vm);
	}
}
