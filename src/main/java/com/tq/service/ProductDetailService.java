package com.tq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tq.entities.ProductDetails;
import com.tq.repositories.ProductDetailRepository;

@Service
public class ProductDetailService {
	@Autowired
	ProductDetailRepository detailRepository;
	
	public int getQuantity(int id) {
		return detailRepository.getQuantity(id);
	}
	
	public ProductDetails findById(int id) {
		return detailRepository.findOne(id);
	}
	public List<ProductDetails> getProductDetailByProductIdAndColorId(int productId , int colorId){
		return detailRepository.listProductDetails(productId, colorId);
	}
	public ProductDetails GetQuantityInStock(int productId, int colorId ,int sizeId) {
		return detailRepository.GetQuantityInStock(productId, colorId, sizeId);
	}
	
	public List<ProductDetails> getSepecialDealProduct(){
		return detailRepository.getListSpecialDeal();
	}
}
