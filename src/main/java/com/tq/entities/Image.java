package com.tq.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "image")
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "image_id")
	private int imageId;
	
	@Column(name = "image_name", length = 100)
	private String nameImage;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	ProductEntity productEntity;

	public int getImageId() {
		return imageId;
	}

	public String getNameImage() {
		return nameImage;
	}

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public void setNameImage(String nameImage) {
		this.nameImage = nameImage;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

	public Image(int imageId, String nameImage, ProductEntity productEntity) {
		super();
		this.imageId = imageId;
		this.nameImage = nameImage;
		this.productEntity = productEntity;
	}
	
	public Image() {
		// TODO Auto-generated constructor stub
	}
}
