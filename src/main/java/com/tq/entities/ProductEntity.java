package com.tq.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.util.CollectionUtils;

@Entity
@Table(name = "product")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int productId;

	@Column(length = 100, name = "product_name")
	private String productName;

	private double price;

	@Column(length = 10)
	private String type;

	@Column(length = 1000)
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_input")
	private Date dateInput;

	public ProductEntity(int productId, String productName, double price, String type, String description,
			Date dateInput, ProductCategory category, List<Promotion> promotions, List<Image> images,
			List<Favourite> favourites, List<ProductDetails> productDetails) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.type = type;
		this.description = description;
		this.dateInput = dateInput;
		this.category = category;
		this.promotions = promotions;
		this.images = images;
		this.favourites = favourites;
		this.productDetails = productDetails;
	}

	@ManyToOne
	@JoinColumn(name = "category_id")
	ProductCategory category;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "productEntities")
	List<Promotion> promotions;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "productEntity")
	List<Image> images;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "productEntities")
	List<Favourite> favourites;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "productEntity")
	List<ProductDetails> productDetails;

	@OneToMany(cascade = CascadeType.ALL , mappedBy = "productEntity")
	List<Review> reviews;
	
	public String getOneImage() {
		if (!CollectionUtils.isEmpty(images)) {
			return images.get(0).getNameImage();
		}
		return "khongtimthay.jpg";
	}

	@Transient
	public int namePromotion;

	public double getNewPrice() {

		try {
			/*
			 * Date date = new Date(); String dateFormat = new
			 * SimpleDateFormat("yyyy-MM-dd").format(date); Date date2 = new
			 * SimpleDateFormat("yyyy-MM-dd").parse(dateFormat);
			 */
//			if (!CollectionUtils.isEmpty(promotions)) {
//				for (Promotion promotion : promotions) {
//					if (!promotion.getDateStart().after(date2) && !promotion.getDateEnd().before(date2)) {
//						this.setNamePromotion(promotion.getDiscount());
//						return (this.price - this.price * promotion.getDiscount() / 100);
//					}
//				}
//			}
			int check  =  CheckExistPromotion();
			if(check >=0) {
				this.setNamePromotion(promotions.get(check).getDiscount());
				return (this.price - this.price * promotions.get(check).getDiscount() / 100);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return this.price;
	}

	public int  CheckExistPromotion() {
		if (!CollectionUtils.isEmpty(promotions)) {

			try {
				Date date = new Date();
				String dateFormat = new SimpleDateFormat("yyyy-MM-dd").format(date);
				Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(dateFormat);
				/*
				 * for (Promotion promotion : promotions) { if
				 * (!promotion.getDateStart().after(date2) &&
				 * !promotion.getDateEnd().before(date2)) {
				 * 
				 * return true; } }
				 */
				for(int i = 0 ; i < promotions.size() ; i ++) {
					if(!promotions.get(i).getDateStart().after(date2) && !promotions.get(i).getDateEnd().before(date2)) {
						return i ;
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		return -1;
	}

	public int GetIdFirstDetail() {
		if (!CollectionUtils.isEmpty(productDetails)) {
			int i = productDetails.get(0).getProductDetailsId();
			return i;
		} else
			return 0;
	}

	public ProductDetails getFisrtDetail() {
		if (!CollectionUtils.isEmpty(productDetails)) {
			return this.getProductDetails().get(0);
		} else
			return null;
	}

	public Set<Color> getListColorFromDetail() {
		Set<Color> colors = new HashSet<Color>();
		if (!CollectionUtils.isEmpty(this.productDetails)) {
			for (ProductDetails d : this.productDetails) {
				colors.add(d.getColor());
			}
		}
		return colors;
	}

	public ProductEntity() {

	}

	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public double getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public List<Image> getImages() {
		return images;
	}

	public List<Favourite> getFavourites() {
		return favourites;
	}

	public List<ProductDetails> getProductDetails() {
		return productDetails;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public void setFavourites(List<Favourite> favourites) {
		this.favourites = favourites;
	}

	public void setProductDetails(List<ProductDetails> productDetails) {
		this.productDetails = productDetails;
	}

	public Date getDateInput() {
		return dateInput;
	}

	public void setDateInput(Date dateInput) {
		this.dateInput = dateInput;
	}

	public List<Promotion> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public int getNamePromotion() {
		return namePromotion;
	}

	public void setNamePromotion(int namePromotion) {
		this.namePromotion = namePromotion;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

}
