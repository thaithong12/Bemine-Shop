package com.tq.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tq.entities.ProductCategory;
import com.tq.entities.ProductEntity;
import com.tq.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;

	
	/*
	 * public Page<ProductEntity> findAll(int a) { return
	 * productRepository.findAll(new PageRequest(a, 4)); }
	 */
	 
	@Transactional
	public List<ProductEntity> GetAllProduct() {
		return productRepository.findAll();
	}
	@Transactional
	public ProductEntity findOne(int id) {
		return productRepository.findByproductId(id);
	}
	
	public List<ProductEntity> getListByType(String type){
		return productRepository.findByType(type);
	}
	
	public Page<ProductEntity> getListByTypePaging(String type, Pageable pageable){
		return productRepository.findByType(type,pageable);
	}
	public List<ProductEntity> getNewProducts(){
		return productRepository.getNewProducts();
	}

	public List<ProductEntity> findByName(String name) {
		return productRepository.findByproductNameLike("%"+name+"%");
	}
	
	public Page<ProductEntity> PagingProduct(PageRequest a) {
		return productRepository.findProducts(a);
	}
	
	public Page<ProductEntity> PagingCategoryProduct(ProductCategory productCategory , Pageable pageable){
		return productRepository.findByCategory(productCategory, pageable);
	}
	
}
