package com.tq.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "promotion")
public class Promotion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "promotion_id")
	private int promotionId;
	
	@Column(name = "promotion_name", length = 100)
	private String promotionName;
	
	@Column(length = 200)
	private String description;
	
	private int discount;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "date_start")
	private Date dateStart;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "date_end")
	private Date dateEnd;
	
	private String status;
	
	@Column(length = 200)
	private String image;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "promotion_product" ,joinColumns = {@JoinColumn(name ="promotion_id")},
	inverseJoinColumns = {@JoinColumn(name = "product_id")})
	List<ProductEntity> productEntities;
	
	public Promotion(int promotionId, String promotionName, String description, int discount, Date dateStart,
			Date dateEnd, String status, String image, List<ProductEntity> productEntities) {
		super();
		this.promotionId = promotionId;
		this.promotionName = promotionName;
		this.description = description;
		this.discount = discount;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.status = status;
		this.image = image;
		this.productEntities = productEntities;
	}
	public Promotion() {
		// TODO Auto-generated constructor stub
	}

	public int getPromotionId() {
		return promotionId;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public String getDescription() {
		return description;
	}

	public int getDiscount() {
		return discount;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public String isStatus() {
		return status;
	}
	
	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public String getImage() {
		return image;
	}

	public List<ProductEntity> getProductEntities() {
		return productEntities;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setProductEntities(List<ProductEntity> productEntities) {
		this.productEntities = productEntities;
	}
	
}
