package com.tq.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tq.entities.Orders;

@Repository
public interface OrderRepository extends CrudRepository<Orders, Integer> {

	@Query(value = "select * from orders o " + "where o.date_create >= ?1 and o.date_create <= ?2", nativeQuery = true)
	public List<Orders> SearchByDateBetweenAnd(Date from,Date to);

	
	public List<Orders> findByDateCreateBetween(Date from,Date to);
	/*
	 * public List<Orders> findByDateCreateBetween(Date from, Date to);
	 * 
	 * @Query(value = "select * from orders \r\n" +
	 * "where date_create between ?1 and ?2",nativeQuery = true) List<Orders>
	 * findByDateCreateBetween(String from , String to);
	 */

}
