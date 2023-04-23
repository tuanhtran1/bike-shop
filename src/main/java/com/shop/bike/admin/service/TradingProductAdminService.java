package com.shop.bike.admin.service;

import com.shop.bike.admin.dto.TradingProductAdminDTO;
import com.shop.bike.admin.dto.TradingProductFilterDTO;
import com.shop.bike.admin.vm.TradingProductAdminVM;
import com.shop.bike.service.TradingProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TradingProductAdminService extends TradingProductService {
	
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
	Page<TradingProductAdminVM> findAllByAdmin(TradingProductFilterDTO filters, Pageable pageable);
	
	/*************************************************************
	 *
	 * @decription: find one by admin
	 *
	 * @param:
	 * @return:
	 *
	 * @date: 23/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	**************************************************************/
	Optional<TradingProductAdminVM> findOneByAdmin(Long id);
	
	/*************************************************************
	 *
	 * @decription: create trading product by admin
	 *
	 * @param: tradingProductAdminDTO
	 * @return: vm
	 *
	 * @date: 23/04/2023
	 * @author: tuanhtran1 (tu.tran@ecaraid.com)
	 **************************************************************/
	TradingProductAdminVM createTradingProductByAdmin(TradingProductAdminDTO tradingProductAdminDTO);
	
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
	TradingProductAdminVM updateTradingProductByAdmin(TradingProductAdminDTO tradingProductAdminDTO);
	
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
	void deleteTradingProductByAdmin(Long id);
}
