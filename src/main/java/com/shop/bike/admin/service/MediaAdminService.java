package com.shop.bike.admin.service;

import com.shop.bike.service.MediaService;
import com.shop.bike.utils.ResourceSerializable;
import com.shop.bike.vm.UploadVM;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MediaAdminService extends MediaService {
	
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
	List<UploadVM> uploadFilesByAdmin(MultipartFile[] uploadingFiles);
	
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
	ResourceSerializable getFile(String folder, String mediaType, String imageName, Integer width);
	
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
	Boolean deleteFile(String folder, String mediaType, String imageName);
}
