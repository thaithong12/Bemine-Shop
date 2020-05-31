package com.tq.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tq.enums.StatusOrder;

@Entity
@Table(name = "orders")
public class Orders {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int orderId;
	
	private String note;
	@Enumerated(EnumType.STRING)
	private StatusOrder status = StatusOrder.PROCESSING;
	
	@Column(name = "total_price")
	private double totalPrice;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_create")
	private Date dateCreate = new Date();
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	Customer customer;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
	List<OrderDetails> orderDetails;

	
	
	public Orders(String note, StatusOrder status, double totalPrice, Customer customer, List<OrderDetails> orderDetails) {
		super();
		this.note = note;
		this.status = status;
		this.totalPrice = totalPrice;
		this.customer = customer;
		this.orderDetails = orderDetails;
	}
	public Orders(String note, StatusOrder status, double totalPrice, Customer customer) {
		super();
		this.note = note;
		this.status = status;
		this.totalPrice = totalPrice;
		this.customer = customer;
	}
	public Orders(int orderId, String note, StatusOrder status, double totalPrice, Date dateCreate, Customer customer,
			List<OrderDetails> orderDetails) {
		super();
		this.orderId = orderId;
		this.note = note;
		this.status = status;
		this.totalPrice = totalPrice;
		this.dateCreate = dateCreate;
		this.customer = customer;
		this.orderDetails = orderDetails;
	}
	public Orders() {
		
	}
	
	public int getOrderId() {
		return orderId;
	}

	public String getNode() {
		return note;
	}

	public StatusOrder isStatus() {
		return status;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public void setNode(String note) {
		this.note = note;
	}

	public void setStatus(StatusOrder status) {
		this.status = status;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public String getNote() {
		return note;
	}
	public StatusOrder getStatus() {
		return status;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
 