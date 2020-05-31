  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
  <%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="featured-products">
                  <h3 class="title wow bounceInRight "><strong>New </strong> Products</h3>
                  <div class="control"><a id="prev_featured" class="prev" href="#">&lt;</a><a id="next_featured" class="next" href="#">&gt;</a></div>
                  <ul id="featured">
                     <li>
                        <div class="row">
                        	 <c:forEach items="${newproducts1}" var="n1">
	                           <div class="col-md-3 col-sm-6">
	                           <form action="" class="form-data">
	                              <div class="products">
	                                 <img alt="new" class="icon-new" src="<c:url value="/resources/images/products/all/new.png"/>">
                                
	                                 <div class="thumbnail test-zoom"data-image= "${n1.getOneImage()}"><a href="<c:url value="/ViewDetail/${n1.productId}"/>"><img style="width :260px; height : 285px;" src="<c:url value="/resources/images/products/all/${n1.getOneImage()}"/>" alt="Product Name"></a></div>
	                                 <div class="productname">${n1.productName}</div>
	                                 <%--  <c:if test="${fn:length(n1.promotions) > 0}"> --%>
	                                  <c:if test="${n1.CheckExistPromotion() >= 0}">
		                                  <h4 class="price" data-price = "${n1.getNewPrice()}">
		                                  <fmt:formatNumber type="number" pattern="###,###" value="${n1.getNewPrice()}" /> đ                                 
			                                 	<del style="color: black;">
			                                 	<fmt:formatNumber type="number" pattern="###,###" value="${n1.price}" /> đ
			                                 	</del>	
			                                 	<div class="product-sale">
			                                 		<span>-${n1.namePromotion} %</span>
			                                 	</div>                                 
		                                  </h4>
	                                  </c:if>
	                                  	
	                                  
	                                 <%--  <c:if test="${fn:length(n1.promotions) == 0}"> --%>
	                                  <c:if test="${n1.CheckExistPromotion() < 0}">
	                                  	<h4 class="price" data-price = "${n1.getNewPrice()}">
	                                  	<fmt:formatNumber type="number" pattern="###,###" value="${n1.getNewPrice()}" /> đ
	                                  	</h4>
	                                  </c:if>
	                                 <div class="button_group"><button class="button add-cart" data-id="${n1.productId}" type="submit">Add To Cart</button>
	                                 <button class="button compare"  type="button"><i class="fa fa-exchange"></i></button>
	                                 <button class="button wishlist add-favourite" data-account="${user.id}" type="button"><i class="fa fa-heart-o"></i></button></div>
	                           	 <input type="number" value="${n1.productId}" name="productId" hidden=""  />
                                 <input type="number" value="${n1.getFisrtDetail().getColor().getColorId()}" name="colorId" hidden=""  />
                                 <input type="number" value="${n1.getFisrtDetail().getSize().getSizeId()}" name="sizeId" hidden=""  />
                                 <input type="number" value="1" name="quantity" hidden=""  />
	                              </div>
	                       
                                </form>
	                           </div>
                       		 </c:forEach>  
                        </div>
                     </li>
                     <li>
                        <div class="row">
                         	<c:forEach items="${newproducts2}" var="n2">
	                           <div class="col-md-3 col-sm-6">
	                             <form action="" class="form-data">
	                              <div class="products">
	                                 <img alt="new" class="icon-new" src="<c:url value="/resources/images/products/all/new.png"/>">
	                                 <div class="thumbnail test-zoom" data-image= "${n2.getOneImage()}"><a href="<c:url value="/ViewDetail/${n2.productId}"/>"><img style="width :260px; height : 285px;" src="<c:url value="/resources/images/products/all/${n2.getOneImage()}"/>" alt="Product Name"></a></div>
	                                 <div class="productname">${n2.productName}</div>
	                                 <%--  <c:if test="${fn:length(n2.promotions) > 0}"> --%>
	                                  <c:if test="${n2.CheckExistPromotion()  >= 0}">
	                                  
		                                  <h4 class="price" data-price = "${n2.getNewPrice()}">         
		                                  <fmt:formatNumber type="number" pattern="###,###" value="${n2.getNewPrice()}"/>đ                        
			                                 	<del style="color: black;">
			                                 		<fmt:formatNumber type="number" pattern="###,###" value="${n2.price}" /> đ
			                                 	</del>
			                                 	<div class="product-sale">
			                                 		<span>-${n2.namePromotion}%</span>
			                                 	</div> 	                                 
		                                  </h4>
	                                  </c:if>
	                                  
	                                 <%--  <c:if test="${fn:length(n2.promotions) == 0}"> --%>
	                                  <c:if test="${n2.CheckExistPromotion() < 0}">
	                                  	<h4 class="price" data-price = "${n2.getNewPrice()}">
	                                  		<fmt:formatNumber type="number" pattern="###,###" value="${n2.getNewPrice()}" />đ
	                                  	</h4>
	                                  </c:if>
	                                 <div class="button_group"><button class="button add-cart" data-id="${n2.productId}" type="submit">Add To Cart</button>
	                                 <button class="button compare "  type="button"><i class="fa fa-exchange"></i></button>
	                                 <button class="button wishlist add-favourite" data-account="${user.id}" type="button"><i class="fa fa-heart-o"></i></button></div>
	                              	  <input type="number" value="${n2.productId}" name="productId" hidden=""  />
                                	<input type="number" value="${n2.getFisrtDetail().getColor().getColorId()}" name="colorId" hidden=""  />
                                	<input type="number" value="${n2.getFisrtDetail().getSize().getSizeId()}" name="sizeId" hidden=""  />
                                	<input type="number" value="1" name="quantity" hidden=""  />
	                              </div>
	                              </form>
	                           </div>
                       		 </c:forEach>  
                        </div>
                     </li>
                  </ul>
               </div>
               
