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
@Table(name = "favourite")
public class Favourite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "favourite_id")
	private int favouriteId;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	AccountEntity account;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	ProductEntity productEntities;

	
	public Favourite(int favouriteId, AccountEntity account, ProductEntity productEntities) {
		super();
		this.favouriteId = favouriteId;
		this.account = account;
		this.productEntities = productEntities;
	}
	public Favourite() {
		// TODO Auto-generated constructor stub
	}

	public int getFavouriteId() {
		return favouriteId;
	}


	public void setFavouriteId(int favouriteId) {
		this.favouriteId = favouriteId;
	}

	public AccountEntity getAccount() {
		return account;
	}


	public void setAccount(AccountEntity account) {
		this.account = account;
	}


	public ProductEntity getProductEntities() {
		return productEntities;
	}


	public void setProductEntities(ProductEntity productEntities) {
		this.productEntities = productEntities;
	}
	
	
}
