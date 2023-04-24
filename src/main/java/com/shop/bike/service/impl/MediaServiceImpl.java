package com.shop.bike.service.impl;

import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.constant.SystemConstant;
import com.shop.bike.entity.Media;
import com.shop.bike.entity.enumeration.ActionStatus;
import com.shop.bike.entity.enumeration.ErrorEnum;
import com.shop.bike.entity.enumeration.MediaType;
import com.shop.bike.repository.MediaRepository;
import com.shop.bike.service.MediaService;
import com.shop.bike.service.dto.MediaDTO;
import com.shop.bike.service.dto.mapper.MediaMapper;
import com.shop.bike.web.rest.errors.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Primary
@Slf4j
public class MediaServiceImpl implements MediaService {
	
	@Autowired
	private MediaMapper mediaMapper;
	
	@Autowired
	private MediaRepository mediaRepository;
	
	/*******************************************************************
	 * get extension of file
	 *
	 * @param uploadedFile
	 * @return extension
	 *
	 * @date: May 15, 2021
	 *******************************************************************/
	public String getFileExtension(String name) {
		log.debug("get files extension by uploadedFile= {}", name);
		return FilenameUtils.getExtension(name);
	}
	
	@Override
	public Media saveFile(MediaDTO mediaDTO) {
		log.debug("Request to save Media : {}", mediaDTO);
		Media media = mediaMapper.toEntity(mediaDTO);
		media = mediaRepository.save(media);
		return media;
	}
	
	@Override
	public Media uploadFile(MultipartFile uploadedFile, MediaType typeMedia) {
		String originalName = uploadedFile.getOriginalFilename();
		log.debug("upload files by uploadedFile= {}", originalName);
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
		String uuid = UUID.randomUUID().toString();
		String extension = getFileExtension(originalName);
		String fileName = uuid + "." + extension;
		log.debug("Generate file name: {}", fileName);
		BigDecimal fileSize = BigDecimal.valueOf(uploadedFile.getSize());
		log.debug("File size: {}", fileSize);
		String filePath = fm.format(new Date()) + File.separator + typeMedia.toString().toLowerCase() + File.separator
				+ fileName;
		log.debug("Upload file to path: {}", filePath);
		log.debug("Root folder: {}", SystemConstant.BASE_UPLOAD);
		String filName = SystemConstant.BASE_UPLOAD + filePath;
		log.debug("File path on server: {}", filName);
		try {
			FileUtils.copyInputStreamToFile(uploadedFile.getInputStream(), new File(filName));
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return insertInfo(filePath, fileName, originalName, typeMedia, extension, fileSize);
	}
	
	@Override
	public List<Media> uploadFiles(MultipartFile[] uploadingFiles) {
		log.debug("upload files by uploadingFiles");
		List<Media> list = new ArrayList<>();
		for (MultipartFile uploadedFile : uploadingFiles) {
			log.debug("upload files by uploadedFile= {}", uploadedFile);
			String fileName = uploadedFile.getOriginalFilename();
			Media media = null;
			String extension = getFileExtension(fileName);
			if (ApplicationConstant.IMAGE_TYPE_ALLOW.contains(extension.toLowerCase())) {
				media = this.uploadFile(uploadedFile, MediaType.IMAGE);
			} else if (ApplicationConstant.VIDEO_TYPE_ALLOW.contains(extension.toLowerCase())) {
				media = this.uploadFile(uploadedFile, MediaType.VIDEO);
			} else if (ApplicationConstant.AUDIO_TYPE_ALLOW.contains(extension.toLowerCase())) {
				media = this.uploadFile(uploadedFile, MediaType.AUDIO);
			} else
				throw new BadRequestAlertException(ErrorEnum.TYPE_NOT_SUPPORT);
			if (media != null)
				list.add(media);
		}
		return list;
	}
	
	/*******************************************************************
	 * Insert Info file
	 *
	 * @param uploadedFile, typeMedia
	 * @return MediaExtDTO
	 *
	 * @date: May 15, 2021
	 *******************************************************************/
	public Media insertInfo(String filePath, String name, String originalName, MediaType typeMedia, String extension,
							BigDecimal fileSize) {
		log.debug("upload files by uploadedFile= {}", filePath);
		MediaDTO file = new MediaDTO();
		file.setName(name);
		file.setPath(filePath);
		file.setExtension(extension);
		file.setOriginalName(originalName);
		file.setFileSize(fileSize);
		file.setStatus(ActionStatus.ACTIVATED);
		file.setMediaType(typeMedia);
		log.debug("Upload success: !");
		return this.saveFile(file);
	}
}
