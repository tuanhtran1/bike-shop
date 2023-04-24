package com.shop.bike.admin.service.impl;

import com.shop.bike.admin.service.MediaAdminService;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.constant.SystemConstant;
import com.shop.bike.entity.enumeration.ActionStatus;
import com.shop.bike.repository.MediaRepository;
import com.shop.bike.service.impl.MediaServiceImpl;
import com.shop.bike.utils.ResourceSerializable;
import com.shop.bike.vm.UploadVM;
import com.shop.bike.vm.mapper.UploadVMMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Qualifier(ApplicationConstant.ADMIN)
@Slf4j
public class MediaAdminServiceImpl extends MediaServiceImpl implements MediaAdminService {
	
	@Autowired
	private UploadVMMapper uploadVMMapper;
	
	@Autowired
	private MediaRepository mediaRepository;
	
	public static final Integer MAX_WIDTH = 1280;
	public static final Integer MIN_WIDTH = 50;
	
	/*************************************************************
	 *
	 * @decription: upload list files
	 *
	 * @param: uploadingFiles
	 * @return: list
	 *
	 * @date: 24/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	@Override
	public List<UploadVM> uploadFilesByAdmin(MultipartFile[] uploadingFiles) {
		log.debug("upload files by Consumer");
		return this.uploadFiles(uploadingFiles).stream().map(uploadVMMapper::toDto).collect(Collectors.toList());
	}
	
	
	/*************************************************************
	 *
	 * @decription: get file
	 *
	 * @param: folder, mediaType, imageName ,width(scale)
	 * @return: data
	 *
	 * @date: 24/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	@Override
	public ResourceSerializable getFile(String folder, String mediaType, String fileName, Integer width) {
		log.debug("Read file on server");
		try {
			File file = null;
			file = new File(SystemConstant.BASE_UPLOAD + folder + File.separator + mediaType, fileName);
			if (file.exists()) {
				String extension = getFileExtension(fileName);
				if (ApplicationConstant.IMAGE_TYPE_ALLOW.contains(extension.toLowerCase())) {
					return findImage(folder,mediaType, fileName, width);
				} else {
					return new ResourceSerializable(file.toURI());
				}
			} else {
				return new ResourceSerializable(
						new File(SystemConstant.BASE_UPLOAD + "/init/noImage.png").toURI());
			}
		} catch (IOException e) {
			log.debug("Load file error: ", e.getMessage());
		}
		return null;
	}
	
	/*************************************************************
	 *
	 * @decription: delete file
	 *
	 * @param: folder, mediaType, imageName
	 * @return: boolean
	 *
	 * @date: 24/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	@Override
	public Boolean deleteFile(String folder, String mediaType, String imageName) {
		log.debug("Read file on server");
		File file = new File(SystemConstant.BASE_UPLOAD + folder + File.separator + mediaType,
				imageName);
		if (!file.isFile())
			return true;
		Boolean isDelete = file.delete();
		if (isDelete.equals(true)) {
			String path = folder + File.separator + mediaType + File.separator + imageName;
			deleteByPath(path);
		}
		return isDelete;
	}
	
	public void deleteByPath(String path) {
		mediaRepository.findByPath(path).ifPresent(file -> file.setStatus(ActionStatus.DELETED));
	}
	
	public ResourceSerializable findImage(String folder,String mediaType , String imageName, Integer width) {
		log.debug("Read file on server");
		String uploadDir = SystemConstant.BASE_UPLOAD;
		try {
			File file = new File(uploadDir + folder + File.separator + mediaType, imageName);
			if (file != null && file.exists()) {
				if (width != null) {
					Float rWidth = Float.valueOf(width) / 100;
					int scaleWith = (Math.round(rWidth) * 100);
					if (scaleWith == 0) {
						scaleWith = 100;
					} else if (scaleWith > MAX_WIDTH) {
						scaleWith = MAX_WIDTH;
					} else if (scaleWith < MIN_WIDTH) {
						scaleWith = MIN_WIDTH;
					}
					File resize = new File(uploadDir + folder + "/" + scaleWith, imageName);
					if (resize.exists()) {
						log.debug("exist image path: " + resize.toURI());
						return new ResourceSerializable(resize.toURI());
					} else {
						Path path = resize(file.getAbsolutePath(), resize.getAbsolutePath(), scaleWith);
						return new ResourceSerializable(path.toUri());
					}
				} else {
					return new ResourceSerializable(file.toURI());
				}
			} else {
				return new ResourceSerializable(new File(uploadDir + "/init/noImage.png").toURI());
			}
		} catch (IOException e) {
			log.debug("Load Image error: ", e.getMessage());
		}
		return null;
	}
	
	public Path resize(String inputImagePath, String outputImagePath, int toWidth) throws IOException {
		File inputFile = new File(inputImagePath);
		BufferedImage inputImage = ImageIO.read(inputFile);
		getHeighRatio(inputImage);
		int scaledWidth = toWidth;
		int scaledHeight = (int) (toWidth * getHeighRatio(inputImage));
		BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());
		Graphics2D g2d = outputImage.createGraphics();
		g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
		g2d.dispose();
		
		int indexLastDot = outputImagePath.lastIndexOf('.');
		String formatName = outputImagePath.substring(indexLastDot + 1);
		File directory = new File(new File(outputImagePath).getParent());
		try {
			FileUtils.forceMkdir(directory);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		ImageIO.write(outputImage, formatName, new File(outputImagePath));
		return new File(outputImagePath).toPath();
	}
	
	private float getHeighRatio(BufferedImage inputImage) {
		int width = inputImage.getWidth();
		int height = inputImage.getHeight();
		return height/(1.0f * width);
	}
}
