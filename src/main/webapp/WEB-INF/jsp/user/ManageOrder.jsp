<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
			<section class="wrapper site-min-height" id="main-user-2">
				<!-- <h3><i class="fa fa-angle-right"></i> Blank Page</h3> -->
				<div class="row mt">
					<div class="col-lg-12" style="text-align: center;">
						<h1 style="color: black; font-weight: 200; font-size: 47px;">Management
							Order</h1>
						<div class="row mt">
							<div class="col-md-12">
								<div class="content-panel">
									<table class="table table-striped table-advance table-hover"
										style="text-align: left;">
										<div class="col-sm-6">
											<h4 style="float: left;">
												<i class="fa fa-angle-right"></i> History Order
											</h4>
										</div>
										<div class="col-sm-6">
											<form action="<c:url value="/user/SearchOrder/1"/>" method="get" class="form-horizontal style-form">
												<div class="form-group">
													
													<div class="col-md-9">
														<div class="input-group input-large" data-date-format="yyyy/mm/dd">
															<input type="date" class="form-control dpd1" name="from">
															<span class="input-group-addon">To</span> 
															<input type="date" class="form-control dpd2" name="to">
														</div>
														
													</div>
													<div class="col-md-3">
														<input type="submit" class="btn btn-round btn-success" value="Search"/>
													</div>
												</div>
											</form>
										</div>
										<div class="col-sm-12">
											<h4>
												<i class=""></i>${msg}
											</h4>
										</div>
										<hr>
										<thead>
											<tr>
												<th><i class="fa fa-bullhorn"></i>#</th>
												<th class="hidden-phone"><i
													class="fa fa-question-circle"></i> Date Order</th>
												<th><i class="fa fa-bookmark"></i> Total Price</th>
												<th><i class=" fa fa-edit"></i> Status</th>
												<th><i class=" fa fa-edit"></i> Note</th>
												<th><i class=" fa fa-edit"></i> Details</th>
												<th><i class=" fa fa-edit"></i> Cancel</th>
											</tr>
										</thead>
										<tbody>
<%-- 											<c:forEach items="${customers}" var="u"> --%>
												<c:forEach items="${orders}" var="o" varStatus="loop">
													<tr>
														<td><a class="id-order" data-order="${o.orderId}"
															href="<c:url  value= "/user/ViewOrderDetail/${o.orderId}"/>">${loop.index +1}</a></td>
														<td class="hidden-phone">${o.dateCreate }</td>
														<td><fmt:formatNumber type="number" pattern="###,###" value="${o.totalPrice }" /> đ</td>
														<td><span class=" status label label-info label-mini">${o.status }</span></td>
														<td>${o.note}</td>														
														<td><a href="<c:url  value= "/user/ViewOrderDetail/${o.orderId}"/>"  class="btn btn-success btn-xs">
																<i class="fa ">View</i>
															</a>
														</td>
														<td><button class="btn btn-danger btn-xs cancle-order">
																<i class="fa fa-trash-o "></i>
															</button>
														</td>
													</tr>
												</c:forEach>
<%-- 											</c:forEach> --%>

										
										</tbody>
									</table>
									<nav aria-label="Page navigation example">
 										 <ul class="pagination">
   											 <li class="page-item">
					      							<c:if test="${((current -1) == 0 )}">
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
										    <c:forEach  begin="1" end="${pages}" var="i">
										    	<c:if test="${current == i }">
										    	  <li class="page-item active"><a class="page-link" href="<c:url value= "/user/ViewOrder/${i}"/>">${i}</a></li>
										    	</c:if>
										   		<c:if test="${current != i }">
										    	  <li class="page-item"><a class="page-link" href="<c:url value= "/user/ViewOrder/${i}"/>">${i}</a></li>
										    	</c:if>
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
</nav>
								</div>
								<!-- /content-panel -->
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