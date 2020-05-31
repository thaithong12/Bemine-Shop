package com.tq.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "orders_detail")
public class OrderDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_detail_id")
	private int orderDetailId;
	
	private int quantity;
	
	private String color;
	
	private String size;
	
	private double price;
	
	@Transient
	private double unitPrice;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	Orders orders;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	ProductEntity productEntity;

	
	public OrderDetails(int orderDetailId, int quantity, String color, String size, double price, double unitPrice,
			Orders orders, ProductEntity productEntity) {
		super();
		this.orderDetailId = orderDetailId;
		this.quantity = quantity;
		this.color = color;
		this.size = size;
		this.price = price;
		this.unitPrice = unitPrice;
		this.orders = orders;
		this.productEntity = productEntity;
	}
	public OrderDetails() {
		// TODO Auto-generated constructor stub
	}
	public double getTotalPrice() {
		return this.price*this.quantity;
	}
	
	public int getOrderDetailId() {
		return orderDetailId;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

	public String getColor() {
		return color;
	}

	public String getSize() {
		return size;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setSize(String size) {
		this.size = size;
	}

	
}
