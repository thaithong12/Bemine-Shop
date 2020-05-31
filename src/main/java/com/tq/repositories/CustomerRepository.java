package com.tq.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tq.entities.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{
	
	  @Query("select c from Customer c " + "where c.phoneNumber = ?1 and c.email = ?2") Customer
	  findByphoneNumber(String phoneNumber , String email);
	  
	  @Query(value = "select * from customer where account_id = ?1", nativeQuery =  true)
	  public List<Customer> findByAccountId(int accountId);
	  
	 
}
