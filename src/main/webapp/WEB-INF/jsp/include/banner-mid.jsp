<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="sec"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="hot-products">
                  <h3 class="title wow bounceIn"><strong>Hot</strong> Products</h3>
                  <div class="control"><a id="prev_hot" class="prev" href="#">&lt;</a><a id="next_hot" class="next" href="#">&gt;</a></div>
                  <ul id="hot">
                     <li>
                        <div class="row">
                       
                        <c:forEach items="${hotproducts1}" var="p1">
                           <div class="col-md-3 col-sm-6">
                            <form action="" id="form-data" class="form-data">
                              <div class="products">
                                 <img alt="hot" class="icon-new" src="<c:url value="/resources/images/products/all/best_seller.png"/>">
                                 <div class="thumbnail test-zoom" data-image= "${p1.getOneImage()}"><a href="<c:url value="/ViewDetail/${p1.productId}"/>">
                                 <img style="width :260px; height : 285px;" src="<c:url value="/resources/images/products/all/${p1.getOneImage()}"/>" alt="Product Name">
                                 </a></div>
                                 <div class="productname">${p1.productName}</div>
                                 <%--  <c:if test="${fn:length(p1.promotions) > 0}"> --%>
                                   <c:if test="${p1.CheckExistPromotion() >= 0}">
	                                  <h4 class="price" data-price = "${p1.getNewPrice()}">
	                                  <fmt:formatNumber type="number" pattern="###,###" value="${p1.getNewPrice()}" /> đ</h4>                                		                                 	
		                                 	<del style="color: black;"><fmt:formatNumber type="number" pattern="###,###" value=" ${p1.price}" /> đ</del>
		                                 	<div class="product-sale">
			                                 		<span> -${p1.namePromotion}%</span>
			                                </div>      	                                 
                                  </c:if>
                                 
                                
                                 <%--  <c:if test="${fn:length(p1.promotions) == 0 }"> --%>
                                  <c:if test="${p1.CheckExistPromotion() < 0}">
                                  	<h4 class="price" data-price = "${p1.getNewPrice()}"> 
                                  	<fmt:formatNumber type="number" pattern="###,###" value="${p1.getNewPrice()}" /> đ</h4>
                                  </c:if>
                                
                                 <div class="button_group">
                                 <button id="submit-detail" class="button add-cart" data-id="${p1.productId}" type="submit">Add To Cart</button>
                                 <button class="button compare" type="button">
                                 	<i class="fa fa-exchange"></i>
                                 </button>
                                <button class="button wishlist add-favourite" data-account="${user.id}" type="button">
                                	<i class="fa fa-heart-o"></i>
                                </button>
                            
                                </div>
                                <input type="number" value="${p1.productId}" name="productId" hidden=""  />
                                <input type="number" value="${p1.getFisrtDetail().getColor().getColorId()}" name="colorId" hidden=""  />
                                <input type="number" value="${p1.getFisrtDetail().getSize().getSizeId()}" name="sizeId" hidden=""  />
                                <input type="number" value="1" name="quantity" hidden=""  />
                              </div>
                              </form> 
                           </div>
                        </c:forEach>  
                       
                         </div>
                     </li>
                     <li>
                        <div class="row">
                       	<c:forEach items="${hotproducts2}" var="p2">
                           <div class="col-md-3 col-sm-6">
                           <form action="" class="form-data">
                              <div class="products">
                                 <img alt="hot" class="icon-new" src="<c:url value="/resources/images/products/all/best_seller.png"/>">
                                 <div class="thumbnail test-zoom" data-image= "${p.getOneImage()}"><a href="<c:url value="/ViewDetail/${p2.productId}"/>"><img style="width :260px; height : 285px;" src="<c:url value="/resources/images/products/all/${p2.getOneImage()}"/>" alt="Product Name"></a></div>
                                 <div class="productname">${p2.productName}</div>
                               <%--   <c:if test="${fn:length(p2.promotions) > 0}"> --%>
                                  <c:if test="${p2.CheckExistPromotion() >= 0}">
	                                  <h4 class="price" data-price = "${p2.getNewPrice()}">
	                                 
	                                  <fmt:formatNumber type="number" pattern="###,###" value="${p2.getNewPrice()}" /> đ                                
		                                 	<del style="color: black;">
		                                 	<fmt:formatNumber type="number" pattern="###,###" value="${p2.price}" /> đ
		                                 	</del>
		                                 	<div class="product-sale">
			                                 		<span>-${p2.namePromotion}%</span>
			                                </div>      	                                 
	                                  </h4>
                                  </c:if>
                                  
                                 <%--  <c:if test="${fn:length(p2.promotions) == 0}"> --%>
                                <c:if test="${p2.CheckExistPromotion() <0}">
                                  	<h4 class="price" data-price = "${p2.getNewPrice()}">
                                  	<fmt:formatNumber type="number" pattern="###,###" value="${p2.getNewPrice()}" /> đ</h4>
                                  </c:if>
                                 <div class="button_group"><button class="button add-cart" data-id="${p2.productId}" type="submit">Add To Cart</button><button class="button compare" type="button"><i class="fa fa-exchange"></i></button>
                                 <button class="button wishlist add-favourite" data-account="${user.id}" type="button"><i class="fa fa-heart-o"></i></button></div>
                                <input type="number" value="${p2.productId}" name="productId" hidden=""  />
                                <input type="number" value="${p2.getFisrtDetail().getColor().getColorId()}" name="colorId" hidden=""  />
                                <input type="number" value="${p2.getFisrtDetail().getSize().getSizeId()}" name="sizeId" hidden=""  />
                                <input type="number" value="1" name="quantity" hidden=""  />
                              </div>
                             </form>
                           </div>
                        </c:forEach>   
                          
                        </div>
                     </li>
                  </ul>
</div>