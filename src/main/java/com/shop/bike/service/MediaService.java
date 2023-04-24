package com.shop.bike.service;

import com.shop.bike.entity.Media;
import com.shop.bike.entity.enumeration.MediaType;
import com.shop.bike.service.dto.MediaDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaService {
	
	Media saveFile(MediaDTO mediaDTO);
	
	Media uploadFile(MultipartFile uploadingFiles, MediaType typeMedia);
	
	List<Media> uploadFiles(MultipartFile[] uploadingFiles);
}
