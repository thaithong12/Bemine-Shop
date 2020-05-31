package com.tq.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tq.entities.Orders;
import com.tq.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	public Orders saveOrder(Orders order) {
		return orderRepository.save(order);
	}

	public Orders findById(int id) {
		return orderRepository.findOne(id);
	}

	public List<Orders> SearchByDateFromTo(Date from, Date to) {
		return orderRepository.SearchByDateBetweenAnd(from,to);
	}
	
	/*
	 * public List<Orders> findByDateCreateBetween(Date from, Date to) {
	 * 
	 * return orderRepository.SearchByDateBetweenAnd(from,to); }
	 */

}
