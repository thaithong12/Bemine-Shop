<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="sec"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  <div class="container_fullwidth">
        <div class="container">
          <div class="row">
            <div class="col-md-9">
              <div class="products-details">
                <div class="preview_image">
                  <div class="preview-small">
                    <img id="zoom_03" src="<c:url value="/resources/images/products/all/${product.getOneImage() }"/>" data-zoom-image="<c:url value="/resources/images/products/all/${product.getOneImage() }"/>" alt="">
                  </div>
                  <div class="thum-image">
                    <ul id="gallery_01" class="prev-thum">
                    <c:forEach items="${product.images }" var="image">
                    	<li>
	                        <a href="#" data-image="<c:url value="/resources/images/products/all/${image.nameImage}"/>" data-zoom-image="<c:url value="/resources/images/products/all/${image.nameImage}"/>">
	                          <img src="<c:url value="/resources/images/products/all/${image.nameImage}"/>" alt="">
	                        </a>
                      	</li>
                    </c:forEach>
                    </ul>
                    <a class="control-left" id="thum-prev" href="javascript:void(0);">
                      <i class="fa fa-chevron-left">
                      </i>
                    </a>
                    <a class="control-right" id="thum-next" href="javascript:void(0);">
                      <i class="fa fa-chevron-right">
                      </i>
                    </a>
                  </div>
                </div>
                <form action="" id="form-data" class="form-data">
                <div class="products-description">
                  <h5 class="name" >
                  	<input style="font-family : cursive ; border: none;font-size : 20px  ;color: red; padding-left : 1px; margin: 0;" readonly="readonly" type="text" name="dataname" value="${product.productName}">                 
                  </h5>
                  <p>
                    <img alt="" src="<c:url value="/resources/images/star.png"/>">
                    <a class="review_num" href="#">
                      ${fn:length(product.reviews)} Review(s)
                    </a>
                  </p>
                  <p>
                    Availability: 
                    <span class="light-red" id="status-update">
                     	${status}
                    </span>
                  </p>
                  <p >
                    	thong dep zai ${product.description}
                  </p>
                  <hr class="border">
                  <div class="price" style="font-family: cursive;">
                    Price : 
            
                     <c:if test="${product.CheckExistPromotion() >= 0}">
                    	<span class="new_price" style="font-size: 22px;font-weight: bold;">
                      		
                      		<fmt:formatNumber type="number" pattern="###,###" value="${product.getNewPrice()}" /> <sup>đ</sup>
                    	</span>
                    	<span class="old_price">                		
                      		<fmt:formatNumber type="number" pattern="###,###" value="${product.price}"/> <sup>đ</sup>
                    	</span>
                    
                    </c:if>
                    
                     <c:if test="${product.CheckExistPromotion() < 0}">
                   		<span class="new_price" style="font-size: 22px;font-weight: bold;">
                   		<fmt:formatNumber type="number" pattern="###,###" value="${product.getNewPrice() }"/> <sup>đ</sup>
                    	</span>
                   		
                   	</c:if>
                  </div>
                  <hr class="border">
                  <div class="wided">
                     <div class="qty">
	                      Color &nbsp;&nbsp;: 
	                      <select class="select-color" id="select-color" name="colorId" data-product ="${product.productId}" style="color: currentColor;
	                      background: #f2f5fa;font-weight: 600;height: 32px;text-align: center;border: 1px solid #f2f5fa;border-radius: 1px;">
	                     	 <c:forEach items="${colors}" var="c">
		                        <option value="${c.colorId}">
		                          	${c.colorName}
		                        </option>
	                        </c:forEach>   
	                      </select>
	   					 <input name="productId" value="${product.productId}" hidden="">
                    </div>
                     <div class="qty" style="padding-left: 5px;">
	                      Size &nbsp;&nbsp;: 
	                      <select class="select-size" id="select-size" name="sizeId" style="color: currentColor;background: #f2f5fa;font-weight: 600;
	                      height: 32px;text-align: center;border: 1px solid #f2f5fa;border-radius: 1px;">
	                      		
	                      </select>
	                      
                    </div>
                    <div class="button_group" style="padding-right: 5%;">
                 		<span style="float: left;padding-right: 8px;padding-top: 9px;">Qty &nbsp;&nbsp;: </span>
                  		<input type="button" id="minus" value="-" class="qty-btn minus">
                    	<input type="text" id="quantity" name="quantity" value="1" min="1" class="quantity-selector">
                    	<input type="button" id="plus" value="+" class="qty-btn plus">
                    </div>
                  </div>
                  <div class="clearfix">
                  </div>
                  <hr class="border">
                  <c:if test="${status == 'In Stock' }">
                  		   <button class="button add-cart" type="submit" data-check='0' style="width: 94%;margin-left: 3%;">
                        Add To Cart
                      </button>
                  </c:if>
                        <c:if test="${status == 'Out Stock' }">
                  		   <button disabled="disabled" class="button add-cart" type="submit" data-check='0' style="width: 94%;margin-left: 3%;">
                        Add To Cart
                      </button>
                  </c:if>
				
                 
                </div>
               	</form> 
              </div>
              <div class="clearfix">
              </div>
              <div class="tab-box">
                <div id="tabnav">
                  <ul>
                    <li>
                      <a href="#Descraption">
                        DESCRIPTION
                      </a>
                    </li>
                    <li>
                      <a href="#Reviews">
                        REVIEW
                      </a>
                    </li>
                  </ul>
                </div>
                <div class="tab-content-wrap">
                  <div class="tab-content" id="Descraption">
                   <h6 style="font-family:  cursive;">${product.description}</h6>
                  </div>
                  <div class="tab-content" id="Reviews" style="overflow-y: scroll; max-height: 500px;">
                  <div id="temp">
                  <c:if test="${product.reviews == null || fn:length(product.reviews) == 0}">
                  		<h6 style="text-align: center; color: forestgreen;font-family: cursive;">No reviews yet, please <a style="color: red;" href="<c:url value="/login"/>">Sig In</a> to rate the product !</h6>
                  </c:if>
                  <c:if test="${product.reviews != null || fn:length(product.reviews) > 0}">
                  		<c:forEach items="${product.reviews}" var="r">
		                  	<div class="review">
		                  		<c:if test="${empty r.accountEntity.avatar}">
		                  				<img style="width: 80px;float: left;height: 84px;margin-right: 4%;" class="img-responsive user-photo" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">
		                  		</c:if>
		                  		<c:if test="${not empty r.accountEntity.avatar}">
		                  				<img style="width: 80px;float: left;height: 84px;margin-right: 4%;" class="img-responsive user-photo" src="<c:url value="/resources/images/avatar/${r.accountEntity.avatar}"/>">
		                  		</c:if>
		                  		
			                    <p class="rating">
			                        <i class="fa fa-star light-red">
			                        </i>
			                        <i class="fa fa-star light-red">
			                        </i>
			                        <i class="fa fa-star light-red">
			                        </i>
			                        <i class="fa fa-star-half-o gray">
			                        </i>
			                        <i class="fa fa-star-o gray">
			                        </i>
			                   </p>
		                      
		                      <h5 class="reviewer">${r.accountEntity.customerName}</h5>
		                      <p class="review-date">${r.reviewDate}</p>
		                      <p>${r.contend}</p>
		                    </div>
                    </c:forEach>
                  </c:if>
                
                    </div>
                    <sec:authorize access="isAuthenticated()">
						<sec:authorize access="hasRole('ROLE_USER')">
								     <div class="review">
								     
						<c:if test="${empty user.avatar}">
		                  				<img style="margin-top :7% ;width: 80px;float: left;height: 84px;margin-right: 4%;" class="img-responsive user-photo" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">
		                  		</c:if>
		                  		<c:if test="${not empty user.avatar}">
		                  				<img style="margin-top :7% ;width: 80px;float: left;height: 84px;margin-right: 4%;" class="img-responsive user-photo" alt="avatar" src="<c:url value="/resources/images/avatar/${user.avatar}"/>">
		                  		</c:if>			     
                  	
                      <p class="rating">
                        <i class="fa fa-star light-red">
                        </i>
                        <i class="fa fa-star light-red">
                        </i>
                        <i class="fa fa-star light-red">
                        </i>
                        <i class="fa fa-star-half-o gray">
                        </i>
                        <i class="fa fa-star-o gray">
                        </i>
                      </p>
                      
                      <h5 class="reviewer">
                        ${user.customerName }
                      </h5>
                      <p class="review-date">
                        <jsp:useBean id="now" class="java.util.Date" />
						<fmt:formatDate var="year" value="${now}" pattern="yyyy-MM-dd" />
						${year}
                      </p>
                      <p style="position: relative;" data-product="${product.productId}">
                        <textarea id="txt-comment" rows="5" cols="88" placeholder="Comments ..."></textarea>
                        <button data-account= "${user.id}" class="btn btn-primary add-comment" type="button" style=" border : none;background-color: #da9898;position: absolute;right: 10px;top: 68px;">Submit</button>
                      </p>
                    </div>          
						</sec:authorize>
								            
					</sec:authorize>
                    
                  </div>
                  <div class="tab-content" >
                    
                    
                  </div>
            
                </div>
              </div>
              <div class="clearfix">
              </div>
            </div>
            <div class="col-md-3">
              <div class="special-deal leftbar" style="font-family: cursive;">
                <h4 class="title">
                  Special 
                  <strong>
                    Deals
                  </strong>
                </h4>
                <!-- start -->
                <c:forEach items="${special }" var="s">
                <div class="special-item">
                  <div class="product-image">
                    <a href="<c:url  value ="/ViewDetail/${s.productId }"/>">
                      <img style="height: 58px;" src="<c:url value="/resources/images/products/all/${s.getOneImage()}"/>" alt="${s.productName }">
                    </a>
                  </div>
                  <div class="product-info">
                    <p style="">
                      ${s.productName}
                    </p>
                    <h5 class="price">
                     	
                     	<fmt:formatNumber type="number" pattern="###,###" value="${s.getNewPrice()}" /><sup style="font-size: 12px;"> đ</sup>
                    </h5>
                  </div>
                </div>
                </c:forEach>
                <!-- end -->
             
              </div>
              <div class="clearfix">
              </div>
              <div class="fbl-box leftbar">
                <h3 class="title">
                   <span>
                      <img src="<c:url value="/resources/images/fbicon.png"/>" alt="">
                    </span> Facebook
                </h3>
               	<div id="fb-root"></div>
		<script async defer crossorigin="anonymous" src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v5.0"></script>
		<div class="fb-like" data-href="https://www.facebook.com/BemineStoreHCM/" data-width="200px" data-layout="standard" 
		data-action="like" data-size="small" data-show-faces="true" data-share="true"></div>
            
              </div>
              <div class="clearfix">
              </div>
            </div>
          </div>

          
        </div>
      </div>