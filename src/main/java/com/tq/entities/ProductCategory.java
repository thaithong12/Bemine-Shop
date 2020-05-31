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
@Table(name = "category_product")
public class ProductCategory {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	@Column(name = "category_id")
	private int categoryId;
	
	@Column(length = 50, name = "category_name")
	private String categoryName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	List<ProductEntity> productEntities ;
	
	private String image;
	
	public ProductCategory(int categoryId, String categoryName, List<ProductEntity> productEntities, String image) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.productEntities = productEntities;
		this.image = image;
	}
	public ProductCategory() {
		// TODO Auto-generated constructor stub
	}

	public int getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public List<ProductEntity> getProductEntities() {
		return productEntities;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setProductEntities(List<ProductEntity> productEntities) {
		this.productEntities = productEntities;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
