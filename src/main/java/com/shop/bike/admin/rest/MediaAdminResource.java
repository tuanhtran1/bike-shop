package com.shop.bike.admin.rest;

import com.shop.bike.admin.service.MediaAdminService;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.utils.ResourceSerializable;
import com.shop.bike.vm.UploadVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/v1/admin")
public class MediaAdminResource {

	private final Logger log = LoggerFactory.getLogger(MediaAdminResource.class);

	private final MediaAdminService mediaAdminService;

	public MediaAdminResource(@Qualifier(ApplicationConstant.ADMIN) MediaAdminService mediaAdminService) {
		this.mediaAdminService = mediaAdminService;
	}
	
	@PostMapping("/upload")
	public ResponseEntity<List<UploadVM>> upload(
			@RequestPart(name = "files", required = true) MultipartFile[] uploadingFiles) {
		log.debug("REST request to upload images");
		List<UploadVM> list = mediaAdminService.uploadFilesByAdmin(uploadingFiles);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{folder}/{mediaType}/{imageName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<ResourceSerializable> getFile(@PathVariable(name = "folder", required = true) String folder,
														@PathVariable(name = "mediaType", required = true) String mediaType,
														@PathVariable(name = "imageName", required = true) String imageName,
														@RequestParam(name = "width", required = false) Integer width) {
		log.debug("REST request to get image: {}", imageName);
		ResourceSerializable resource = mediaAdminService.getFile(folder, mediaType, imageName, width);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@DeleteMapping(value = "/{folder}/{mediaType}/{imageName}")
	public ResponseEntity<Boolean> deleteFile(@PathVariable(name = "folder", required = true) String folder,
			@PathVariable(name = "mediaType", required = true) String mediaType,
			@PathVariable(name = "imageName", required = true) String imageName) {
		log.debug("REST request to get image: {}", imageName);
		Boolean deleteFile = mediaAdminService.deleteFile(folder, mediaType, imageName);
		return ResponseEntity.ok().body(deleteFile);
	}
}
