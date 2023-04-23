package com.shop.bike.admin.service;

import com.shop.bike.admin.dto.ProductAdminDTO;
import com.shop.bike.admin.dto.ProductFilterDTO;
import com.shop.bike.admin.vm.ProductAdminVM;
import com.shop.bike.entity.Product;
import com.shop.bike.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductAdminService extends ProductService {
	
	/*************************************************************
	 *
	 * @decription: find all product filters
	 *
	 * @param: filters, pageable
	 * @return: page
	 *
	 * @date: 22/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	Page<ProductAdminVM> findAllProductByAdmin(ProductFilterDTO filters, Pageable pageable);
	
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
	ProductAdminVM saveProductByAdmin(ProductAdminDTO productAdminDTO);
	
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
	void deleteProductByAdmin(Long id);
	
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
	Optional<ProductAdminVM> findOneByVendor(Long id);
	
	void setMinMaxPrice(Product product);
}
