package com.shop.bike.web.rest;


import com.shop.bike.admin.service.MediaAdminService;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.utils.ResourceSerializable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/v1/global")
public class GlobalResource {
	private final Logger log = LoggerFactory.getLogger(GlobalResource.class);
	
	private final MediaAdminService mediaAdminService;
	
	public GlobalResource(@Qualifier(ApplicationConstant.ADMIN) MediaAdminService mediaAdminService) {
		this.mediaAdminService = mediaAdminService;
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
}
