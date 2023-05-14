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
import com.shop.bike.utils.JsonConverter;
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
	
	@Autowired
	private TradingProductRepository tradingProductRepository;
	
	@Autowired
	private ProductService productService;
	
	@Override
	public Set<MyOrderDetails> create(MyOrder newOrder, List<CreateOrderDetailDTO> orderDetailsDTO) {
		
		BigDecimal subTotal = BigDecimal.ZERO;
		Set<MyOrderDetails> myOrderDetails = new HashSet<MyOrderDetails>();
		
		for (CreateOrderDetailDTO orderDetailDTO : orderDetailsDTO) {
			TradingProduct tradingProduct = tradingProductRepository.findById(orderDetailDTO.getTradingProductId())
					.orElseThrow(() -> new BadRequestAlertException(ErrorEnum.TRADING_PRODUCT_NOT_FOUND));
			
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
	
	public void processOrderDetail(MyOrderDetails orderDetail, TradingProduct tradingProduct) {
		orderDetail.setProductId(tradingProduct.getProduct().getId());
		orderDetail.setProductName(tradingProduct.getName());
		orderDetail.setImages(JsonConverter.toJson(tradingProduct.getMedia()));
		orderDetail.setTradingProductId(tradingProduct.getId());
		orderDetail.setTradingProductCache(JsonConverter.toJson(tradingProduct));
		productService.increaseAmountOrder(tradingProduct.getProduct(), 1);
		if(tradingProduct.getStockQuantity() >= orderDetail.getQuantity()){
			tradingProduct.setStockQuantity(tradingProduct.getStockQuantity() - orderDetail.getQuantity());
			tradingProductRepository.save(tradingProduct);
		}
		else throw new BadRequestAlertException(ErrorEnum.STOCK_QUANTITY_NOT_ENOUGH);
		
	}
}
