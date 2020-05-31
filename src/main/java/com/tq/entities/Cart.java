package com.tq.entities;

public class Cart {
	private int cartId;
	
	private int productId;
	
	private int productDetailId;
	
	private int colorId;
	
	private int sizeId;
	
	private double price;
	
	private String productName;
	
	private String sizeName;
	
	private String colorName;
	
	private int quantity;
	
	private String image;
	
	public Cart() {
		
	}
	public double getSumPrice() {
		return this.price * quantity;
	}
	@Override
	public String toString() {
		return cartId + " " + productId + " " + productDetailId + " " + colorId + " " + sizeId ;
	}

	public Cart( int productId, int productDetailId, double price, String productName, String sizeName, String colorName,
			int quantity, String image, int colorId , int sizeId ) {
		super();
		
		this.productId = productId;
		this.productDetailId = productDetailId;
		this.price = price;
		this.productName = productName;
		this.sizeName = sizeName;
		this.colorName = colorName;
		this.quantity = quantity;
		this.image = image;
		this.colorId = colorId;
		this.sizeId = sizeId;
	}



	public Cart(int cartId,int productId, int colorId, int sizeId, int quantity) {
		super();
		this.cartId = cartId;
		this.productId = productId;
		this.colorId = colorId;
		this.sizeId = sizeId;
		this.quantity = quantity;
	}
	public Cart(int cartId ,int productId, double price, String productName, String sizeName, String colorName, int quantity,
			String image) {
		super();
		this.cartId = cartId;
		this.productId = productId;
		this.price = price;
		this.productName = productName;
		this.sizeName = sizeName;
		this.colorName = colorName;
		this.quantity = quantity;
		this.image = image;
	}
	
	public int getProductId() {
		return productId;
	}

	public int getProductDetailId() {
		return productDetailId;
	}

	public double getPrice() {
		return price;
	}

	public String getProductName() {
		return productName;
	}

	public String getSizeName() {
		return sizeName;
	}

	public String getColorName() {
		return colorName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setProductDetailId(int productDetailId) {
		this.productDetailId = productDetailId;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getColorId() {
		return colorId;
	}

	public int getSizeId() {
		return sizeId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}

	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
}
