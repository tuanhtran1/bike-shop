package com.shop.bike.admin.service.impl;

import com.shop.bike.admin.dto.BrandAdminDTO;
import com.shop.bike.admin.dto.mapper.BrandAdminMapper;
import com.shop.bike.admin.repository.BrandAdminRepository;
import com.shop.bike.admin.service.BrandAdminService;
import com.shop.bike.admin.vm.BrandAdminVM;
import com.shop.bike.admin.vm.mapper.BrandAdminVMMapper;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.entity.Brand;
import com.shop.bike.entity.enumeration.ActionStatus;
import com.shop.bike.entity.enumeration.ErrorEnum;
import com.shop.bike.security.SecurityUtils;
import com.shop.bike.service.impl.BrandServiceImpl;
import com.shop.bike.vm.BrandVM;
import com.shop.bike.web.rest.errors.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Qualifier(ApplicationConstant.ADMIN)
@Slf4j
public class BrandAdminServiceImpl extends BrandServiceImpl implements BrandAdminService {

	@Autowired
	@Qualifier(ApplicationConstant.ADMIN)
	private BrandAdminRepository brandAdminRepository;
	
	@Autowired
	private BrandAdminMapper mapper;
	
	@Autowired
	private BrandAdminVMMapper vmMapper;
	
	/*************************************************************
	 *
	 * @decription: get list option brand
	 *
	 * @param: none
	 * @return: list vm
	 *
	 * @date: 06/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	@Override
	public List<BrandVM> getListOptionAdmin() {
		return this.getListOption();
	}
	
	/*************************************************************
	 *
	 * @decription: find one by admin
	 *
	 * @param: id
	 * @return: vm
	 *
	 * @date: 06/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 *************************************************************/
	@Override
	public Optional<BrandAdminVM> findOneByAdmin(Long id) {
		Optional<Brand> brandOpt = brandAdminRepository.findByIdAndStatusNot(id, ActionStatus.DELETED);
		if(brandOpt.isEmpty()){
			throw new BadRequestAlertException(ErrorEnum.BRAND_NOT_FOUND);
		}
		log.debug("Request to get a Brand : {}", brandOpt.get());
		return brandOpt.map(vmMapper::toDto);
	}
	
	/*************************************************************
	 *
	 * @decription: create brand by admin
	 *
	 * @param: dto
	 * @return: vm
	 *
	 * @date: 06/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 *************************************************************/
	@Override
	public BrandAdminVM saveBrandByAdmin(BrandAdminDTO dto) {
		
		Brand brand = new Brand();
		
		Long userId = Long.valueOf(SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.USER_NOT_FOUND)));
		
		if(dto.getId() == null){ // case create
			//check duplicate name
			checkDuplicateName(dto);
			brand = mapper.toEntity(dto);
			brand.setStatus(ActionStatus.ACTIVATED);
			brand.setUserId(userId);
		}else {
			//check duplicate name
			checkDuplicateName(dto);
			brand = brandAdminRepository.findByIdAndStatusNot(dto.getId(), ActionStatus.DELETED)
					.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.BRAND_NOT_FOUND));
			mapper.partialUpdate(brand, dto);
		}
		return vmMapper.toDto(brandAdminRepository.save(brand));
	}
	
	/*************************************************************
	 *
	 * @decription: delete brand by admin
	 *
	 * @param: id
	 * @return: none
	 *
	 * @date: 06/05/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	@Override
	public void deleteBrandByAdmin(Long id) {
		Optional<Brand> brandOpt = brandAdminRepository.findByIdAndStatusNot(id, ActionStatus.DELETED);
		if(brandOpt.isEmpty()){
			throw new BadRequestAlertException(ErrorEnum.BRAND_NOT_FOUND);
		}
		Brand brand = brandOpt.get();
		log.debug("Request to delete a Brand : {}", brand);
		brand.setStatus(ActionStatus.DELETED);
		brandAdminRepository.save(brand);
	}
	
	private void checkDuplicateName(BrandAdminDTO dto) {
		Optional<Brand> currentBrand = brandAdminRepository.findOneByNameAndStatusNot(dto.getName(), ActionStatus.DELETED);
		if (currentBrand.isPresent()){
			throw new BadRequestAlertException(ErrorEnum.BRAND_NAME_DUPLICATE);
		}
	}
}
