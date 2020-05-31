package com.tq.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "size")
public class Size {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "size_id")
	private int sizeId;
	
	@Column(length = 4, name = "size_name")
	private String sizeName;
	
	@OneToMany(mappedBy = "size", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	List<ProductDetails> list;

	public int getSizeId() {
		return sizeId;
	}

	public String getSizeName() {
		return sizeName;
	}

	public List<ProductDetails> getList() {
		return list;
	}

	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public void setList(List<ProductDetails> list) {
		this.list = list;
	}
	
	
}
