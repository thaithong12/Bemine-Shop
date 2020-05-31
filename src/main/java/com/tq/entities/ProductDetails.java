package com.tq.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_details")
public class ProductDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_detail_id")
	private int productDetailsId;
	
	private int quantity;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	ProductEntity productEntity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "color_id")
	Color color;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "size_id")
	Size size;

	
	public ProductDetails(int productDetailsId, int quantity, ProductEntity productEntity, Color color, Size size) {
		super();
		this.productDetailsId = productDetailsId;
		this.quantity = quantity;
		this.productEntity = productEntity;
		this.color = color;
		this.size = size;
	}
	public ProductDetails() {
		// TODO Auto-generated constructor stub
	}

	public int getProductDetailsId() {
		return productDetailsId;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public Color getColor() {
		return color;
	}

	public Size getSize() {
		return size;
	}

	public void setProductDetailsId(int productDetailsId) {
		this.productDetailsId = productDetailsId;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setSize(Size size) {
		this.size = size;
	}
}
