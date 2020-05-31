package com.tq.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "review")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private int reviewId;
	
	private String contend;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date reviewDate;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	AccountEntity accountEntity;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	ProductEntity productEntity;

	public int getReviewId() {
		return reviewId;
	}

	public String getContend() {
		return contend;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public AccountEntity getAccountEntity() {
		return accountEntity;
	}

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public void setContend(String contend) {
		this.contend = contend;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public void setAccountEntity(AccountEntity accountEntity) {
		this.accountEntity = accountEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

	public Review(String contend, Date reviewDate, AccountEntity accountEntity, ProductEntity productEntity) {
		super();
		this.contend = contend;
		this.reviewDate = reviewDate;
		this.accountEntity = accountEntity;
		this.productEntity = productEntity;
	}
	
	public Review() {
		// TODO Auto-generated constructor stub
	}
}
