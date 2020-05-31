package com.tq.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer extends PersonalInfo{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private int customerId;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	List<Orders> orders;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	AccountEntity accountEntity;

	
	public Customer(int customerId, List<Orders> orders, AccountEntity accountEntity) {
		super();
		this.customerId = customerId;
		this.orders = orders;
		this.accountEntity = accountEntity;
	}
	public Customer() {
		
	}

	public int getCustomerId() {
		return customerId;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public AccountEntity getAccountEntity() {
		return accountEntity;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public void setAccountEntity(AccountEntity accountEntity) {
		this.accountEntity = accountEntity;
	}
	
}
