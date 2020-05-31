package com.tq.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tq.entities.ProductCategory;

@Repository
public interface CategoryRepository extends CrudRepository<ProductCategory, Integer> {
	public List<ProductCategory> findBycategoryNameLike(String name);
}
