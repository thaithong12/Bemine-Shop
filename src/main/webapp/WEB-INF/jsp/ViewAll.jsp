<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TQ Shop</title>
<jsp:include page="include/head-css.jsp" />
</head>
<body id="home">
	<div class="wrapper">
		<jsp:include page="include/header.jsp" />
		<jsp:include page="include/menu.jsp" />
		<%-- <jsp:include page="include/menu-responsive.jsp"/> --%>
		<div class="clearfix"></div>
		<div class="container_fullwidth">
			<div class="container">
				<div class="hot-products">
					<h3 class="title">
						<strong>All</strong> Products
					</h3>

					<ul id="hot">
						<li>
							<div class="row">
								<c:forEach items="${products}" var="p">
									<div class="col-md-3 col-sm-6">
										<form action="" id="form-data" class="form-data">
											<div class="products">

												<div class="thumbnail test-zoom"
													data-image="${p.getOneImage()}">
													<a href="<c:url value="/ViewDetail/${p.productId}"/>">
														<img style="width: 260px; height: 285px;"
														src="<c:url value="/resources/images/products/all/${p.getOneImage()}"/>"
														alt="Product Name">
													</a>
												</div>
												<div class="productname">${p.productName}</div>
											
												
												 <c:if test="${p.CheckExistPromotion() >= 0}">
	                                  <h4 class="price" data-price = "${p.getNewPrice()}">
	                                  <fmt:formatNumber type="number" pattern="###,###" value="${p.getNewPrice()}" /> đ</h4>                                		                                 	
		                                 	<del style="color: black;"><fmt:formatNumber type="number" pattern="###,###" value=" ${p.price}" /> đ</del>
		                                 	<div class="product-sale">
			                                 		<span> -${p.namePromotion}%</span>
			                                </div>      	                                 
                                  </c:if>
                                 
                                
                                 <%--  <c:if test="${fn:length(p1.promotions) == 0 }"> --%>
                                  <c:if test="${p.CheckExistPromotion() < 0}">
                                  	<h4 class="price" data-price = "${p.getNewPrice()}"> 
                                  	<fmt:formatNumber type="number" pattern="###,###" value="${p.getNewPrice()}" /> đ</h4>
                                  </c:if>

												<div class="button_group">
													<button id="submit-detail" class="button add-cart"
														data-id="${p.productId}" type="submit">Add To
														Cart</button>
													<button class="button compare" type="button">
														<i class="fa fa-exchange"></i>
													</button>
													<button class="button wishlist add-favourite" data-page ="${current}" data-account="${user.id}" type="button">
														<i class="fa fa-heart-o"></i>
													</button>
												</div>
												<input type="number" value="${p.productId}" name="productId" hidden="" />	
												<input type="number" value="${p.getFisrtDetail().getColor().getColorId()}"name="colorId" hidden="" /> 
												<input type="number" value="${p.getFisrtDetail().getSize().getSizeId()}" name="sizeId" hidden="" /> 
												<input type="number" value="1" name="quantity" hidden="" />
											</div>
										</form>
									</div>
								</c:forEach>
							</div>
						</li>

					</ul>
				</div>
				<div class="row">
					<div class="col-sm-12" style="text-align: center;">
						<ul class="pagination">
							<li class="page-item">
								<c:if test="${((current -1) < 0 )||((current -1) == 0 )}">
										<a style="font-size: 16px;" class="page-link" href="<c:url value="/${action}/${current}"/>" aria-label="Previous">
								        <span aria-hidden="true">&laquo;</span>
								        <span class="sr-only">Previous</span>
								      </a>						
								</c:if>
								<c:if test="${(current -1) > 0}">
										<a style="font-size: 16px;" class="page-link" href="<c:url value="/${action}/${current-1}"/>" aria-label="Previous">
								        <span aria-hidden="true">&laquo;</span>
								        <span class="sr-only">Previous</span>
								      </a>						
								</c:if>
							      
						    </li>
							<c:forEach var="i" begin="1" end="${pages}">
								<c:choose>
									<c:when test="${i== current}">
										<li class="page-item active paging-item">
											<a style="border: none;font-size: 16px;background-color: brown;" class="page-link" href="<c:url value="/ViewAll/${i}" />">${i}</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item paging-item">
											<a style="font-size: 16px;" class="page-link" href="<c:url value="/${action}/${i}"/>">${i}</a>
										</li>
									</c:otherwise>
								</c:choose>
							
							</c:forEach>
								<li class="page-item">
									<c:if test="${(current+1) <=pages}">
										<a style="font-size: 16px;" class="page-link" href="<c:url value="/${action}/${current+1}"/>" aria-label="Next">
									        <span aria-hidden="true">&raquo;</span>
									        <span class="sr-only">Next</span>
							      		</a>
									</c:if>
      								<c:if test="${(current+1) > pages}">
										<a style="font-size: 16px;" class="page-link" href="<c:url value="/${action}/${current}"/>" aria-label="Next">
									        <span aria-hidden="true">&raquo;</span>
									        <span class="sr-only">Next</span>
							      		</a>
									</c:if>
 								</li>
						</ul>
					</div>
				</div>
				<div class="clearfix"></div>
				<div class="our-brand">
					<h3 class="title">
						<strong>Our </strong> Brands
					</h3>
					<div class="control">
						<a id="prev_brand" class="prev" href="#">&lt;</a><a
							id="next_brand" class="next" href="#">&gt;</a>
					</div>
					<ul id="braldLogo">
						<li>
							<ul class="brand_item">
								<li><a href="#">
										<div class="brand-logo">
											<img src="<c:url value="/resources/images/envato.png"/>"
												alt="">
										</div>
								</a></li>
								<li><a href="#">
										<div class="brand-logo">
											<img src="<c:url value="/resources/images/themeforest.png"/>"
												alt="">
										</div>
								</a></li>
								<li><a href="#">
										<div class="brand-logo">
											<img src="<c:url value="/resources/images/photodune.png"/>"
												alt="">
										</div>
								</a></li>
								<li><a href="#">
										<div class="brand-logo">
											<img src="<c:url value="/resources/images/activeden.png"/>"
												alt="">
										</div>
								</a></li>
								<li><a href="#">
										<div class="brand-logo">
											<img src="<c:url value="/resources/images/envato.png"/>"
												alt="">
										</div>
								</a></li>
							</ul>
						</li>
						<li>
							<ul class="brand_item">
								<li><a href="#">
										<div class="brand-logo">
											<img src="<c:url value="/resources/images/envato.png"/>"
												alt="">
										</div>
								</a></li>
								<li><a href="#">
										<div class="brand-logo">
											<img src="<c:url value="/resources/images/themeforest.png"/>"
												alt="">
										</div>
								</a></li>
								<li><a href="#">
										<div class="brand-logo">
											<img src="<c:url value="/resources/images/photodune.png"/>"
												alt="">
										</div>
								</a></li>
								<li><a href="#">
										<div class="brand-logo">
											<img src="<c:url value="/resources/images/activeden.png"/>"
												alt="">
										</div>
								</a></li>
								<li><a href="#">
										<div class="brand-logo">
											<img src="<c:url value="/resources/images/envato.png"/>"
												alt="">
										</div>
								</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
		<jsp:include page="include/footer.jsp" />
	</div>
	<jsp:include page="include/footer-script.jsp" />
	<script type="text/javascript">
		new WOW().init();
	</script>
</body>
</html>
