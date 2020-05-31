<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../include/header-css-user.jsp" />
<title>Management Order</title>
</head>
<body>
	<section id="container">
		<jsp:include page="../include/header-user.jsp" />
		<jsp:include page="../include/menu-user.jsp" />
		<%-- <jsp:include page="../include/main-user.jsp" /> --%>
		<section id="main-content"
			style="padding-bottom: 0; margin-bottom: 0px;">
			<section class="wrapper site-min-height" id="main-user-2"
				style="background: linear-gradient(rgba(0, 0, 0, 0.45), rgba(0, 0, 0, 0.45)), url(../../resources/images/br/br1.jpg) no-repeat center center;">
				<!-- <h3><i class="fa fa-angle-right"></i> Blank Page</h3> -->
				<div class="row mt">
					<div class="col-lg-12" style="text-align: center;">
						<div class="row mt">
							<div class="col-md-12">
								<c:forEach items="${favourites}" var="f">
									<div class="col-lg-4 col-md-4 col-sm-4 mb">
										<div class="product-panel-2 pn"
											style="height: auto; background: aliceblue;">

											<img
												src="<c:url value="/resources/images/products/all/${f.productEntities.getOneImage() }"/>"
												style="width: 85%; overflow: hidden;" width="200" alt="">
											<h5 class="mt">${f.productEntities.productName }</h5>

											<c:if test="${f.productEntities.CheckExistPromotion() >= 0}">
												<h6 class="price" style="color: red;"
													data-price="${f.productEntities.getNewPrice()}">
													<fmt:formatNumber type="number" pattern="###,###"
														value="${f.productEntities.getNewPrice()}" />
													đ
													
														<sup><del style="color: black;">
													<fmt:formatNumber type="number" pattern="###,###"
														value=" ${f.productEntities.price}" />
													đ</sup>
												</del>
												
												</h6>
											<div class="product-sale" style="position: absolute;top: 10px;font-size: 12px;line-height: 1;
											padding: 7px 17px;font-weight: bold;z-index: 9;color: #000000;background: #5cd4e6;left: 43px;">
													<span> -${f.productEntities.namePromotion}%</span>
												</div>
												
											</c:if>
											<c:if test="${f.productEntities.CheckExistPromotion() < 0}">
												<h6 class="price" style="color: red;"
													data-price="${f.productEntities.getNewPrice()}">
													<fmt:formatNumber type="number" pattern="###,###"
														value="${f.productEntities.getNewPrice()}" />
													đ
												</h6>
											</c:if>
											<a href="<c:url  value= "/ViewDetail/${f.productEntities.productId }"/>" class="btn btn-small btn-theme03 "
												style="margin-bottom: 2%; float: left; margin-left: 13%;" >View
												more</a>
											<a href="<c:url value= "/user/DeleteFavourite/${f.favouriteId} " />" class="btn btn-small btn-theme04"
												style="margin-bottom: 2%;">Delete</a>
										</div>
									</div>
								</c:forEach>
							</div>
							<!-- /col-md-12 -->
						</div>
					</div>
				</div>
			</section>
			<!-- /wrapper -->
		</section>

		<%-- <jsp:include page="../include/footer-user.jsp" /> --%>
	</section>
	<jsp:include page="../include/footer-js-user.jsp" />
</body>
</html>