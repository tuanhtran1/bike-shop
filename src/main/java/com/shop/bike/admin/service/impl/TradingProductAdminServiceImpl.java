package com.shop.bike.admin.service.impl;

import com.shop.bike.admin.dto.ProductAttributeDTO;
import com.shop.bike.admin.dto.TradingProductAdminDTO;
import com.shop.bike.admin.dto.TradingProductFilterDTO;
import com.shop.bike.admin.dto.mapper.TradingProductAdminMapper;
import com.shop.bike.admin.repository.ProductAdminRepository;
import com.shop.bike.admin.repository.TradingProductAdminRepository;
import com.shop.bike.admin.service.ProductAdminService;
import com.shop.bike.admin.service.TradingProductAdminService;
import com.shop.bike.admin.vm.TradingProductAdminVM;
import com.shop.bike.admin.vm.mapper.TradingProductAdminVMMapper;
import com.shop.bike.constant.ApplicationConstant;
import com.shop.bike.entity.Product;
import com.shop.bike.entity.ProductAttribute;
import com.shop.bike.entity.TradingProduct;
import com.shop.bike.entity.enumeration.ActionStatus;
import com.shop.bike.entity.enumeration.ApproveStatus;
import com.shop.bike.entity.enumeration.ErrorEnum;
import com.shop.bike.repository.ProductAttributeRepository;
import com.shop.bike.service.impl.TradingProductServiceImpl;
import com.shop.bike.utils.GenerateIDUtil;
import com.shop.bike.web.rest.errors.BadRequestAlertException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@Qualifier(ApplicationConstant.ADMIN)
@Slf4j
public class TradingProductAdminServiceImpl extends TradingProductServiceImpl implements TradingProductAdminService {
	
	@Autowired
	@Qualifier(ApplicationConstant.ADMIN)
	private TradingProductAdminRepository tradingProductAdminRepository;
	
	@Autowired
	@Qualifier(ApplicationConstant.ADMIN)
	private ProductAdminRepository productAdminRepository;
	
	@Autowired
	@Qualifier(ApplicationConstant.ADMIN)
	private ProductAdminService productAdminService;
	
	@Autowired
	private TradingProductAdminMapper tradingProductAdminMapper;
	
	@Autowired
	private TradingProductAdminVMMapper tradingProductAdminVMMapper;
	
	@Autowired
	private ProductAttributeRepository productAttributeRepository;
	
	
	/*************************************************************
	 *
	 * @decription: find all filters trading product
	 *
	 * @param: filters, pageable
	 * @return: page
	 *
	 * @date: 23/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	@Override
	public Page<TradingProductAdminVM> findAllByAdmin(TradingProductFilterDTO filters, Pageable pageable) {
		return tradingProductAdminRepository.findAll(filters, pageable).map(tradingProductAdminVMMapper::toDto);
	}
	
	/*************************************************************
	 *
	 * @decription: find one by admin
	 *
	 * @param: id
	 * @return: vm
	 *
	 * @date: 23/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	@Override
	public Optional<TradingProductAdminVM> findOneByAdmin(Long id) {
		Optional<TradingProduct> tradingProductOpt = tradingProductAdminRepository.findByIdAndStatusNot(id, ActionStatus.DELETED);
		if(tradingProductOpt.isEmpty()){
			throw new BadRequestAlertException(ErrorEnum.PRODUCT_NOT_FOUND);
		}
		log.debug("Request to get a Product : {}", tradingProductOpt.get());
		return tradingProductOpt.map(tradingProductAdminVMMapper::toDto);
	}
	
	/*************************************************************
	 *
	 * @decription: create trading product by admin
	 *
	 * @param: tradingProductAdminDTO
	 * @return: vm
	 *
	 * @date: 23/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 *************************************************************/
	@Override
	public TradingProductAdminVM createTradingProductByAdmin(TradingProductAdminDTO dto) {
		checkAttributeTradingProduct(dto);
		TradingProduct tradingProduct = tradingProductAdminMapper.toEntity(dto);
		tradingProduct.setStatus(ActionStatus.ACTIVATED);
		tradingProduct.setApproveStatus(ApproveStatus.APPROVED);
		tradingProduct.setItemCode(GenerateIDUtil.getItemCode());
		tradingProduct = tradingProductAdminRepository.save(tradingProduct);
		setAttributes(dto.getAttributes(), tradingProduct);
		setProduct(dto, tradingProduct);
		return tradingProductAdminVMMapper.toDto(tradingProductAdminRepository.save(tradingProduct));
	}
	
	/*************************************************************
	 *
	 * @decription: update trading product by admin
	 *
	 * @param: tradingProductAdminDTO
	 * @return: vm
	 *
	 * @date: 23/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	@Override
	public TradingProductAdminVM updateTradingProductByAdmin(TradingProductAdminDTO dto) {
		TradingProduct tradingProduct = tradingProductAdminRepository.findById(dto.getId())
				.orElseThrow(()-> new BadRequestAlertException(ErrorEnum.TRADING_PRODUCT_NOT_FOUND));
		if(dto.getAttributes() != null){
			checkAttributeTradingProduct(dto);
			productAttributeRepository.deleteAllByTradingProductId(tradingProduct.getId());
			setAttributes(dto.getAttributes(), tradingProduct);
			dto.setAttributes(null);
		}
		if(dto.getPrice() != null){
			tradingProduct.setPrice(dto.getPrice());
			productAdminService.setMinMaxPrice(tradingProduct.getProduct());
			dto.setPrice(null);
		}
		log.info("saving trading product....");
		tradingProductAdminMapper.partialUpdate(tradingProduct, dto);
		return tradingProductAdminVMMapper.toDto(tradingProductAdminRepository.save(tradingProduct));
	}
	
	/*************************************************************
	 *
	 * @decription: delete trading product
	 *
	 * @param: id
	 * @return: none
	 *
	 * @date: 23/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	@Override
	public void deleteTradingProductByAdmin(Long id) {
		TradingProduct tradingProduct = tradingProductAdminRepository.findById(id).get();
		tradingProduct.setStatus(ActionStatus.DELETED);
		tradingProductAdminRepository.save(tradingProduct);
	}
	
	private void checkAttributeTradingProduct(TradingProductAdminDTO dto) {
		List<TradingProduct> tradingProducts = tradingProductAdminRepository.findAllByProductId(dto.getProductId());
		List<ProductAttributeDTO> attributesDTO = dto.getAttributes().stream().collect(Collectors.toList());
		if(!tradingProducts.isEmpty()){
			for(TradingProduct t: tradingProducts){
				List<ProductAttribute> attributes = t.getAttributes().stream().collect(Collectors.toList());
				if(isSameAttributes(attributesDTO, attributes)){
					throw new BadRequestAlertException(ErrorEnum.TRADING_PRODUCT_EXISTED);
				}
			}
		}
	}
	
	private void setProduct(TradingProductAdminDTO dto, TradingProduct tradingProduct) {
		Product product = productAdminRepository.findByIdAndProductStatusNot(dto.getProductId(), ActionStatus.DELETED)
				.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.PRODUCT_NOT_FOUND));
		tradingProduct.setProduct(product);
		tradingProduct.setName(product.getName());
		product.getTradingProducts().add(tradingProduct);
		productAdminRepository.save(product);
		productAdminService.setMinMaxPrice(product);
	}
	
	private void setAttributes(Set<ProductAttributeDTO> attributes, TradingProduct tradingProduct) {
		if (!attributes.isEmpty() && attributes != null) {
			Set<ProductAttribute> productAttributes = new HashSet<>();
			for (ProductAttributeDTO item : attributes) {
				ProductAttribute productAttribute = new ProductAttribute();
				productAttribute.setName(item.getName());
				productAttribute.setValue(item.getValue());
				productAttribute.setTradingProduct(tradingProduct);
				productAttributes.add(productAttribute);
				productAttributeRepository.save(productAttribute);
			}
			tradingProduct.setAttributes(productAttributes);
		}
	}
	private boolean isSameAttributes(List<ProductAttributeDTO> attributesDTO, List<ProductAttribute> attributes) {
		if ((attributesDTO == null && attributes == null) || (attributesDTO.isEmpty() && attributes.isEmpty()))
			return true;
		if (attributes != null && attributesDTO != null && attributes.size() == attributesDTO.size()
				&& !attributesDTO.isEmpty() && !attributes.isEmpty()) {
			for (ProductAttributeDTO atrributeDTO : attributesDTO) {
				for (ProductAttribute attribute : attributes) {
					if (atrributeDTO.getName().equals(attribute.getName()) && !atrributeDTO.getValue().equals(attribute.getValue())) {
						return false;
					}
				}
			}
			return true;
		}
		return false;
	}
}
