package com.tq.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tq.entities.ProductDetails;

@Repository
@Transactional
public interface ProductDetailRepository extends CrudRepository<ProductDetails, Integer>{
	@Query(value = "select sum(quantity)\r\n" + 
			"from product_details\r\n" + 
			"where product_id = ?1 \r\n" + 
			"group by product_id;", nativeQuery = true)
	public int getQuantity(int id);
	
	@Query(value = "select * from product_details "
			+ "where product_id = ?1 and color_id = ?2 ", nativeQuery =  true)
	List<ProductDetails> listProductDetails(int productId , int colorId);
	
	@Query(value = "select *\r\n" + 
			"from product_details \r\n" + 
			"group by product_id\r\n" + 
			"order by sum(quantity) desc\r\n" + 
			"limit 6" ,nativeQuery = true)
	public List<ProductDetails> getListSpecialDeal();
	
	@Query(value = "select * from product_details " + 
			"where  product_id = ?1 and color_id = ?2 and size_id = ?3", nativeQuery = true)
	ProductDetails GetQuantityInStock(int productId , int colorId, int sizeId);
	
	public ProductDetails findByproductDetailsId(int productDetailId);
}
