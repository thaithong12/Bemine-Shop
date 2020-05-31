package com.tq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tq.entities.OrderDetails;
import com.tq.entities.ProductEntity;
import com.tq.repositories.OrderDetailRepositoty;

@Service
public class OrderDetailService {
	@Autowired
	OrderDetailRepositoty orderDetailRepositoty;
	
	public List<ProductEntity> getHotProduct(){
		List<OrderDetails> details = orderDetailRepositoty.getProductHot();
		List<ProductEntity> entities = new ArrayList<ProductEntity>();
		if (details.size() > 0) {
			for(OrderDetails orderDetails : details) {
				ProductEntity productEntity = orderDetails.getProductEntity();
				entities.add(productEntity);
			}
		}
		return entities;
	}
	
	public OrderDetails SaveOrderDetail(OrderDetails dt) {
		return orderDetailRepositoty.save(dt);
	}
	
	public List<OrderDetails> getListOrderDetailByOrderId(int id){
		return orderDetailRepositoty.getListOrderDetailByOrderId(id);
	}
}
