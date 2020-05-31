<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>TQ Shop</title>
    <jsp:include page="include/head-css.jsp"/>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="include/header.jsp"/>
		   <div class="clearfix">
        </div>
        <div class="page-index" style="background-color: #bdad7c;">
          <div class="container" >
        	<div class="col-md-12 mb-0" >
        		<a href='<c:url value="/"/>'>Home</a>
        		<span class="mx-2 mb-0">/</span>
        		<strong class="text-black">Cart</strong>
        	</div>
          </div>
        </div>
        <div class="clearfix"></div>
        <div style="background-color:  white;">
                	<div class="row" style="text-align: center;">
        		<div class="col-md-12" style="padding-top: 3%;">
        			<h2 style="font-family: cursive;">Ordered Details</h2>
        		</div>
        	</div>
        	<div class="row">
        		<div class="col-md-1"></div>
        		<div class="col-md-11" style="padding-left: 24px;padding-bottom: 2%;">
        			<h5 style="font-family: monospace;">Order Date : ${now}</h5>
        		</div>
        	</div>
        </div>
        <div class="row mb-5" style="background-color: white;">
        <div class="container">
        <form action="" class="col-md-12">
        	<div class="site-blocks-table" style="font-family: cursive;">
        		<table class="table table-bordered" id="table-checkout">
	        		<thead>
	        			<tr>
	        				<th style="width: 26%;" class="product-thumbnail">Image</th>
	        				<th class="product-name">Product</th>
	        				<th class="product-price">Price</th>
	        				<th class="product-price">Color</th>
	        				<th class="product-price">Size</th>
	        				<th class="product-quantity">Quantity</th>
	        				<th class="product-total">Total</th>
	        				<th class="product-remove">Update</th>
	        				<th class="product-remove">Remove</th>
	        			</tr>
	        		</thead>	
	        		<tbody>
		        		<c:forEach items="${listItem}" var="item">
		        			<tr>
		        				<td ><img style="height: 153px; widows: 120px;" class="img-fluid" src="<c:url value="/resources/images/products/all/${item.image}"/>" alt="" /></td>
		        				<td class="product-name-checkout" data-product="${item.productId}" >${item.productName}</td>
		        				<td class="price-c" data-price= "${item.price}"><fmt:formatNumber  type="number" pattern="###,###" value="${item.price}" /> </td>
		        				<td class="qty">
		        					
		        					 <select class="a" data-product="${item.productId}" name="color" data-product ="" 
		        					 style="color: currentColor;background: #f2f5fa;font-weight: 600;height: 32px;text-align: center;border: 1px solid #f2f5fa;
		        					 border-radius: 1px;">
				                     	<c:forEach items="${listP}" var="p">
                      						<c:if test="${p.productId == item.productId}">
                      							<c:forEach items="${p.getListColorFromDetail()}" var="c">
                      								<c:if test="${c.colorId == item.colorId }">
                      									<option value="${c.colorId}" selected="selected">${c.colorName }</option>
                      								</c:if>
                      									<c:if test="${c.colorId != item.colorId }">
                      									<option value="${c.colorId}">${c.colorName }</option>
                      								</c:if>
                      							</c:forEach>
                      						</c:if>
                      					</c:forEach>
                      				</select>
		        				</td>
		        				<td class="qty2">
		        					<select class="select-size" name="size" style="color: currentColor;background: #f2f5fa;font-weight: 600;height: 32px;text-align: center;border: 1px solid #f2f5fa;border-radius: 1px;">
                      					<option value="${item.sizeId}">${item.sizeName}</option>
                      				</select>
		        				
		        				</td>
		        				<td>
									<div class="button_group" style="">
				                    	<input style="border: none;" type="number" min="1" name="quantity" value="${item.quantity}" class="quantity-selector"/>
                    				</div>
								</td>

		        				<td class="total-c">${item.getSumPrice()}</td>
		        					<td>
		        					<span class="btn btn-warning height-auto btn-sm btn-update-checkout" data-cart ="${item.cartId}">Update</span>
		        				</td>
		        				<td>
		        					<span class="btn btn-danger height-auto btn-sm btn-delete-checkout">X</span>
		        				</td>
		        			</tr>
		        		</c:forEach>	
	        		</tbody>
        		</table>
        	</div>
        </form>
        </div>
        </div>
        <div class="clearfix"></div>
        
        <div class="container-fluid">
        	<div class="row" style="background-color: white;">
        	  <div class="col-md-6">
           
              <div class="col-md-6">
                <button onclick="window.location='/TQShop/'" class="btn btn-success" style="width: 82%; margin-left: 35%;" id="continue-shopping">Continue Shopping</button>
            </div>
          </div>
          <div class="col-md-6 pl-5">
            <div class="row">
              <div class="col-md-7" style="float: right;">
                <div class="row" style="padding-bottom: 5%;">
                  <div class="col-md-12 mb-5">
                    <h3 class="total-checkout-c" id="sum-cart">Cart Totals  <strong style="padding-left: 10%;">0.00</strong></h3>
                  </div> 
                </div>

                <div class="row" style="padding-bottom: 10%;">
                  <div class="col-md-12">
                    <button style="margin-left: 23%;font-family: 'Raleway', sans-serif;" class="btn btn-info" onclick="window.location='CheckOut'">Proceed To Checkout</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        	</div>
     <div class="clearfix"></div>
     <jsp:include page="include/footer.jsp"/>
        </div>
	 <jsp:include page="include/footer-script.jsp"/>

</body>
</html>