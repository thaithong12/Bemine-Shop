package com.tq.repositories;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tq.entities.ProductCategory;
import com.tq.entities.ProductEntity;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
	public List<ProductEntity> findAll();
	
	public ProductEntity findByproductId(int id);
	
	public List<ProductEntity> findByType(String type);
	
	@Query("select e from ProductEntity e where e.type = ?1")
	public Page<ProductEntity> findByType(String type , Pageable pageable);
	
	@Query(value = "select * from product\r\n" + 
			"order by date_input desc\r\n" + 
			"limit 8" , nativeQuery =  true)
	List<ProductEntity> getNewProducts();
	
	public List<ProductEntity> findByproductNameLike(String name);
	
	@Query("SELECT e FROM ProductEntity  e")
	Page<ProductEntity> findProducts(Pageable pageable);
	
	@Query("select e from ProductEntity e where e.category = ?1")
	public Page<ProductEntity> findByCategory(ProductCategory productCategory ,Pageable pageable);
	
}
