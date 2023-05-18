package com.shop.bike.service.impl;

import com.shop.bike.consumer.dto.CreateOrderDetailDTO;
import com.shop.bike.entity.MyOrder;
import com.shop.bike.entity.MyOrderDetails;
import com.shop.bike.entity.TradingProduct;
import com.shop.bike.entity.enumeration.ErrorEnum;
import com.shop.bike.repository.MyOrderDetailRepository;
import com.shop.bike.repository.TradingProductRepository;
import com.shop.bike.service.MyOrderDetailService;
import com.shop.bike.service.MyOrderService;
import com.shop.bike.service.ProductService;
import com.shop.bike.service.TradingProductService;
import com.shop.bike.utils.JsonConverter;
import com.shop.bike.vm.TradingProductBaseVM;
import com.shop.bike.web.rest.errors.BadRequestAlertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@Primary
public class MyOrderDetailServiceImpl implements MyOrderDetailService {
	
	@Autowired
	private MyOrderDetailRepository myOrderDetailRepository;
	
//	@Autowired
//	private TradingProductRepository tradingProductRepository;
//
	@Autowired
	private TradingProductService tradingProductService;
	
	@Autowired
	private ProductService productService;
	
	@Override
	public Set<MyOrderDetails> create(MyOrder newOrder, List<CreateOrderDetailDTO> orderDetailsDTO) {
		
		BigDecimal subTotal = BigDecimal.ZERO;
		Set<MyOrderDetails> myOrderDetails = new HashSet<MyOrderDetails>();
		
		for (CreateOrderDetailDTO orderDetailDTO : orderDetailsDTO) {
			
			TradingProductBaseVM tradingProduct = tradingProductService.findOneById(orderDetailDTO.getTradingProductId());
			
			//Initialize an orderDetail
			MyOrderDetails orderDetail = new MyOrderDetails();
			
			//Set quantity
			orderDetail.setQuantity(orderDetailDTO.getQuantity());
			
			//Set unit price
			orderDetail.setUnitPrice(orderDetailDTO.getPrice());
			
			//Process total discount
			//TO-DO: input coupon to be continue...
			orderDetail.setDiscount(BigDecimal.ZERO);
			
			//Set total
			BigDecimal total = orderDetail.getUnitPrice().multiply(BigDecimal.valueOf(orderDetail.getQuantity()))
					.subtract(orderDetail.getDiscount());
			orderDetail.setTotal(total);
			subTotal = subTotal.add(total);
			
			//Set product info
			processOrderDetail(orderDetail, tradingProduct);
			
			//Save order detail
			orderDetail.setOrder(newOrder);
			myOrderDetails.add(orderDetail);
			myOrderDetailRepository.save(orderDetail);
		}
		
		//Set order details
		newOrder.setOrderDetails(myOrderDetails);
		
		//Calculate sub total
		newOrder.setSubTotal(subTotal);
		
		return myOrderDetails;
	}
	
	public void processOrderDetail(MyOrderDetails orderDetail, TradingProductBaseVM tradingProduct) {
		orderDetail.setProductId(tradingProduct.getProductId());
		orderDetail.setProductName(tradingProduct.getName());
		orderDetail.setTradingProductId(tradingProduct.getId());
		orderDetail.setTradingProductCache(JsonConverter.toJson(tradingProduct));
		orderDetail.setImages(tradingProduct.getImages());
		productService.increaseAmountOrder(tradingProduct.getProductId(), 1);
		tradingProductService.saveStockQuantity(tradingProduct.getId(), orderDetail.getQuantity());
	}
}
