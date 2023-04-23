package com.shop.bike.admin.service.impl;

import com.shop.bike.admin.dto.ProductAdminDTO;
import com.shop.bike.admin.dto.ProductFilterDTO;
import com.shop.bike.admin.dto.mapper.ProductAdminMapper;
import com.shop.bike.admin.repository.ProductAdminRepository;
import com.shop.bike.admin.repository.TradingProductAdminRepository;
import com.shop.bike.admin.service.ProductAdminService;
import com.shop.bike.admin.vm.ProductAdminVM;
import com.shop.bike.admin.vm.mapper.ProductAdminVMMapper;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.entity.Brand;
import com.shop.bike.entity.Product;
import com.shop.bike.entity.TradingProduct;
import com.shop.bike.entity.enumeration.ActionStatus;
import com.shop.bike.entity.enumeration.ApproveStatus;
import com.shop.bike.entity.enumeration.ErrorEnum;
import com.shop.bike.repository.BrandRepository;
import com.shop.bike.security.SecurityUtils;
import com.shop.bike.service.impl.ProductServiceImpl;
import com.shop.bike.utils.GenerateIDUtil;
import com.shop.bike.web.rest.errors.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@Qualifier(ApplicationConstant.ADMIN)
@Slf4j
public class ProductAdminServiceImpl extends ProductServiceImpl implements ProductAdminService {
	
	@Autowired
	@Qualifier(ApplicationConstant.ADMIN)
	private ProductAdminRepository productAdminRepository;
	
	@Autowired
	@Qualifier(ApplicationConstant.ADMIN)
	private TradingProductAdminRepository tradingProductAdminRepository;
	
	@Autowired
	private ProductAdminMapper productAdminMapper;
	
	@Autowired
	private ProductAdminVMMapper productAdminVMMapper;
	
	@Autowired
	private BrandRepository brandRepository;
	
	/*************************************************************
	 *
	 * @decription: find one by admin
	 *
	 * @param: id
	 * @return: ProductAdminVM
	 *
	 * @date: 22/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	@Override
	@Transactional(readOnly = true)
	public Optional<ProductAdminVM> findOneByVendor(Long id) {
		Optional<Product> productOpt = productAdminRepository.findByIdAndProductStatusNot(id, ActionStatus.DELETED);
		if(productOpt.isEmpty()){
			throw new BadRequestAlertException(ErrorEnum.PRODUCT_NOT_FOUND);
		}
		log.debug("Request to get a Product : {}", productOpt.get());
		return productOpt.map(productAdminVMMapper::toDto);
	}
	
	
	/*************************************************************
	 *
	 * @decription: find all product filters
	 *
	 * @param: filters, pageable
	 * @return: page
	 *
	 * @date: 22/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 ************************************************************/
	@Override
	@Transactional(readOnly = true)
	public Page<ProductAdminVM> findAllProductByAdmin(ProductFilterDTO filters, Pageable pageable) {
		return productAdminRepository.findAll(filters, pageable).map(productAdminVMMapper::toDto);
	}
	
	/*************************************************************
	 *
	 * @decription: save product by admin
	 *
	 * @param: productAdminDTO
	 * @return: vm
	 *
	 * @date: 20/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	@Override
	public ProductAdminVM saveProductByAdmin(ProductAdminDTO productAdminDTO) {
		
		Product product = new Product();
		
		if(productAdminDTO.getId() == null){
			log.debug("Request to create a new Product : {}", productAdminDTO);
			product = productAdminMapper.toEntity(productAdminDTO);
			
			log.debug("Set product status activated");
			product.setProductStatus(ActionStatus.ACTIVATED);
			product.setApprovalDate(Instant.now());
			product.setCode(GenerateIDUtil.getProductCode());
			
			log.debug("Set user id");
			product.setUserId(Long.valueOf(SecurityUtils.getCurrentUserLogin().get()));
			product.setApprover(SecurityUtils.getCurrentUserLogin().get());
			
		}else {
			log.debug("Request to update a Product : {}", productAdminDTO);
			product = productAdminRepository.findByIdAndProductStatusNot(productAdminDTO.getId(), ActionStatus.DELETED)
					.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.PRODUCT_NOT_FOUND));
			
			if(productAdminDTO.getName() != null){
				log.debug("Save name trading products");
				Set<TradingProduct> tradingProducts = product.getTradingProducts().stream()
						.filter(t -> t.getStatus() != ActionStatus.DELETED)
						.peek(t -> t.setName(productAdminDTO.getName()))
						.collect(Collectors.toSet());
				tradingProductAdminRepository.saveAll(tradingProducts);
			}
			productAdminMapper.partialUpdate(product, productAdminDTO);
		}
		log.debug("Save brand");
		if(productAdminDTO.getBrandId() != null){
			Brand brand = brandRepository.findById(productAdminDTO.getBrandId())
					.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.BRAND_NOT_FOUND));
			product.setBrand(brand);
		}
		log.debug("Saving a product");
		return productAdminVMMapper.toDto(productAdminRepository.save(product));
	}
	
	/*************************************************************
	 *
	 * @decription: delete by admin
	 *
	 * @param: id
	 * @return: none
	 *
	 * @date: 22/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	@Override
	public void deleteProductByAdmin(Long id) {
		Optional<Product> productOpt = productAdminRepository.findByIdAndProductStatusNot(id, ActionStatus.DELETED);
		if(productOpt.isEmpty()){
			throw new BadRequestAlertException(ErrorEnum.PRODUCT_NOT_FOUND);
		}
		Product product = productOpt.get();
		log.debug("Request to delete a Product : {}", product);
		product.setProductStatus(ActionStatus.DELETED);
		productAdminRepository.save(product);
	}
	
	@Override
	public void setMinMaxPrice(Product product) {
		if(product.getTradingProducts() != null && !product.getTradingProducts().isEmpty()) {
			BigDecimal minPrice, maxPrice;
			minPrice = maxPrice = product.getTradingProducts().stream().findFirst()
					.map(TradingProduct::getPrice)
					.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.TRADING_PRODUCT_IS_EMPTY));
			Set<TradingProduct> tradingProductsRunning = product.getTradingProducts().stream()
					.filter(tp -> tp.getStatus() == ActionStatus.ACTIVATED && tp.getApproveStatus() == ApproveStatus.APPROVED)
					.collect(Collectors.toSet());
			for (TradingProduct tp : tradingProductsRunning) {
				if(tp.getPrice().compareTo(minPrice) < 0) minPrice = tp.getPrice();
				if(tp.getPrice().compareTo(maxPrice) > 0) maxPrice = tp.getPrice();
			}
			product.setMinPrice(minPrice);
			product.setMaxPrice(maxPrice);
			productAdminRepository.save(product);
		}
	}
	
}
