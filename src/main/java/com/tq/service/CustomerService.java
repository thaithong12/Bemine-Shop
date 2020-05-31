package com.tq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tq.entities.Customer;
import com.tq.repositories.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;

	public Customer saveCustomer(Customer c) {
		return customerRepository.save(c);
	}

	public Customer CheckCustomerExist(String phoneNumber, String email) {
		return customerRepository.findByphoneNumber(phoneNumber,email);
	}
	public List<Customer> findByAccountId(int accountId) {
		return customerRepository.findByAccountId(accountId);
	}
}
