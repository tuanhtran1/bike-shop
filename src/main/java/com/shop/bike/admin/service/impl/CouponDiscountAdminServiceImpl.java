package com.shop.bike.admin.service.impl;

import com.shop.bike.admin.dto.CouponDiscountAdminDTO;
import com.shop.bike.admin.dto.CouponDiscountFiltersDTO;
import com.shop.bike.admin.dto.mapper.CouponDiscountAdminMapper;
import com.shop.bike.admin.repository.CouponDiscountAdminRepository;
import com.shop.bike.admin.service.CouponDiscountAdminService;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.entity.CouponDiscount;
import com.shop.bike.entity.enumeration.CouponStatus;
import com.shop.bike.entity.enumeration.ErrorEnum;
import com.shop.bike.security.SecurityUtils;
import com.shop.bike.service.CouponDiscountService;
import com.shop.bike.service.impl.CouponDiscountServiceImpl;
import com.shop.bike.vm.CouponDiscountVM;
import com.shop.bike.vm.mapper.CouponDiscountVMMapper;
import com.shop.bike.web.rest.errors.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
@Qualifier(ApplicationConstant.ADMIN)
@Slf4j
public class CouponDiscountAdminServiceImpl extends CouponDiscountServiceImpl implements CouponDiscountAdminService {
	
	@Autowired
	private CouponDiscountAdminMapper couponDiscountAdminMapper;
	
	@Autowired
	private CouponDiscountVMMapper couponDiscountVMMapper;
	
	@Autowired
	@Qualifier(ApplicationConstant.ADMIN)
	private CouponDiscountAdminRepository couponDiscountAdminRepository;
	
	/*************************************************************
	 *
	 * @decription: find all filters coupons
	 *
	 * @param:
	 * @return:
	 *
	 * @date: 24/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	@Override
	public Page<CouponDiscountVM> findAllByAdmin(CouponDiscountFiltersDTO filters, Pageable pageable) {
		return couponDiscountAdminRepository.findAll(filters, pageable).map(couponDiscountVMMapper::toDto);
	}
	
	/*************************************************************
	 *
	 * @decription: get detail coupon by admin
	 *
	 * @param: id
	 * @return: cm
	 *
	 * @date: 24/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 *************************************************************/
	@Override
	public CouponDiscountVM getDetailByAdmin(Long id) {
		return couponDiscountVMMapper.toDto(couponDiscountAdminRepository.findByIdAndStatusNot(id, CouponStatus.DELETED)
				.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.COUPON_NOT_FOUND)));
	}
	
	/*************************************************************
	 *
	 * @decription: create coupon discount
	 *
	 * @param: couponDiscountAdminDTO
	 * @return: vm
	 *
	 * @date: 24/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	@Override
	public CouponDiscountVM saveCouponByAdmin(CouponDiscountAdminDTO couponDiscountAdminDTO) {
		
		String userId = SecurityUtils.getCurrentUserLogin()
				.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.USER_NOT_FOUND));
		CouponDiscount coupon = new CouponDiscount();
		
		if(couponDiscountAdminDTO.getId() == null){ //case create
			coupon = couponDiscountAdminMapper.toEntity(couponDiscountAdminDTO);
			
			log.debug("Set action status");
			coupon.setStatus(CouponStatus.ACTIVATED);
			
			log.debug("Set code coupon");
			saveCodeCoupon(couponDiscountAdminDTO, coupon);
		}
		else { //case update
			coupon = couponDiscountAdminRepository.findByIdAndStatusNot(couponDiscountAdminDTO.getId(), CouponStatus.DELETED)
							.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.COUPON_NOT_FOUND));
			couponDiscountAdminMapper.partialUpdate(coupon, couponDiscountAdminDTO);
		}
		
		log.debug("Saving coupon...");
		return couponDiscountVMMapper.toDto(couponDiscountAdminRepository.save(coupon));
	}
	
	/*************************************************************
	 *
	 * @decription: delete coupon discount
	 *
	 * @param: id
	 * @return: none
	 *
	 * @date: 24/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	@Override
	public void deleteCouponByAdmin(Long id) {
		CouponDiscount coupon = couponDiscountAdminRepository.findByIdAndStatusNot(id, CouponStatus.DELETED)
				.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.COUPON_NOT_FOUND));
		coupon.setStatus(CouponStatus.DELETED);
		couponDiscountAdminRepository.save(coupon);
	}
	
	private void saveCodeCoupon(CouponDiscountAdminDTO couponDiscountAdminDTO, CouponDiscount coupon) {
		
		List<String> existedCodes = couponDiscountAdminRepository.findAllByStatusNot(CouponStatus.DELETED)
				.stream().map(CouponDiscount::getCouponCode).collect(Collectors.toList());
		
		if (couponDiscountAdminDTO.getCouponCode() == null) {
			String couponCode = createRandomCode(7);
			while (existedCodes.contains(couponCode)) {
				couponCode = createRandomCode(7);
			}
			coupon.setCouponCode(couponCode); //waiting review
		}else {
			if(existedCodes.contains(couponDiscountAdminDTO.getCouponCode())){
				throw new BadRequestAlertException(ErrorEnum.COUPON_CODE_EXIST);
			}
		}
	}
	
	private String createRandomCode(int codeLength){
		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new SecureRandom();
		for (int i = 0; i < codeLength; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}
}
