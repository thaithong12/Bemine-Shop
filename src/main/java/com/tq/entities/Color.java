package com.tq.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "color")
public class Color {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "color_id")
	private int colorId;

	@Column(length = 40, name = "color_name")
	private String colorName;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "color")
	List<ProductDetails> productDetails;

	public Color(int colorId, String colorName, List<ProductDetails> productDetails) {
		super();
		this.colorId = colorId;
		this.colorName = colorName;
		this.productDetails = productDetails;
	}

	public Color() {
	}

	public int getColorId() {
		return colorId;
	}

	public String getColorName() {
		return colorName;
	}

	public List<ProductDetails> getProductDetails() {
		return productDetails;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public void setProductDetails(List<ProductDetails> productDetails) {
		this.productDetails = productDetails;
	}

}
