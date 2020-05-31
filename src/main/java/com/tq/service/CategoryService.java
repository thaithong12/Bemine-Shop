package com.tq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tq.entities.ProductCategory;

import com.tq.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<ProductCategory> getAll(){
			return (List<ProductCategory>)categoryRepository.findAll();
	}
	
	public ProductCategory findByCategoryId(int id) {
		return  categoryRepository.findOne(id);
		
	}
}
