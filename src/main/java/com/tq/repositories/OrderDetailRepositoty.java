package com.tq.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tq.entities.OrderDetails;
@Repository
public interface OrderDetailRepositoty extends CrudRepository<OrderDetails, Integer>{
	@Query(value = "select order_detail_id, color , price , sum(quantity) as quantity, size, order_id, product_id\r\n" + 
			"from orders_detail \r\n" + 
			"group by product_id\r\n" + 
			"order by quantity desc\r\n" + 
			"limit 8;" , nativeQuery = true)
	List<OrderDetails> getProductHot();
	
	@Query(value = "select * from orders_detail where order_id = ?1", nativeQuery =  true)
	List<OrderDetails> getListOrderDetailByOrderId(int id);
}
