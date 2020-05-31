<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Billing Details</title>
<jsp:include page="include/head-css.jsp" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link href="<c:url value="/resources/css/checkout/style.css"/>"
	rel="stylesheet">
</head>
<body>
	<div class="wrapper">
		<jsp:include page="include/header.jsp" />
		<div class="clearfix"></div>
		<div class="page-index" style="background-color: #bdad7c;font-size: 12px;padding: 15px;">
			<div class="container">
				<div class="col-md-12 mb-0">
					<a class="text-black" href='<c:url value="/"/>'>Home</a> <span
						class="mx-2 mb-0">/</span> <a  class="text-black"
						href='<c:url value="/CheckOut"/>'>Cart</a> <span class="mx-2 mb-0">/</span>
					<strong style="color: white;">Checkout</strong>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>

		<div class="container-fluid"
			style="background-color: white; font-size: 19px;">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="border" role="alert">
							Returning member? <a href="<c:url value="/login"/>" style="color: #207dff;">Click
								here</a> to login
						</div>
						<div class="row">
							<div class="step-description">
								<form action='<c:url value="sending"/>' method="post">
									<div class="row">
										<div class="col-md-6 col-sm-12">
											<div class="your-details">
												<h2
													style="margin-bottom: 6%; font-weight: bold; font-size: 1.75rem;">Billing
													Details</h2>
												<div class="billing">
													<div class="form-row">
														<label class="lebel-abs"> Full Name <strong
															class="red"> * </strong>
														</label> <input type="text" class="input namefild" name="fullname" required="required" value="${user.customerName}">
													</div>

													<div class="form-row">
														<label class="lebel-abs"> Email <strong
															class="red"> * </strong>
														</label> <input type="email" name="email" value="${user.email }" style="padding: 17px 6px 15px 131px;" class="input namefild" name="" required="required">
													</div>
													<div class="form-row">
														<label class="lebel-abs"> Telephone <strong
															class="red"> * </strong>
														</label> <input value="${user.phoneNumber }" style="padding: 17px 6px 15px 131px;" type="text" class="input namefild" name="phone" required="required">
													</div>
													<div class="form-row">
														<label class="lebel-abs"> Address<strong class="red"> * </strong>
														</label> <input value="${user.address}" type="text" class="input namefild" name="address" required="required">
													</div>

													<div class="form-row">
														<label class="lebel-abs"> Notes<strong class="red"></strong>
														</label>
														<textarea cols="30" rows="5" type="text"
															class="input namefild" name="note"></textarea>
													</div>
													<input type="number" name="userId" value="${user.id}"  hidden="hidden"/>
												</div>

											</div>
										</div>
										<div class="col-md-5 col-sm-9">
											<div class="your-details" style="margin-top: 17%;">
												<h2
													style="font-size: 1.75rem; font-weight: bold; text-align: center;margin-left: 36%;">Your
													Order</h2>

												<div class="row">
													<div class="bill">
														<table class="w3-table w3-bordered">
															<thead>
																<tr>
																	<th>Product</th>
																	<th>Size</th>
																	<th>Color</th>
																	<th>Quantity</th>
																	<th>Total</th>
																</tr>
															</thead>
															<tbody>
																<c:forEach items="${listcart}" var="c">
																	<tr>
																		<td>${c.productName}</td>
																		<td>${c.sizeName }</td>
																		<td>${c.colorName }</td>
																		<td>${c.quantity }</td>
																		<td>
																		<fmt:formatNumber type="number" pattern="###,###" value="${c.getSumPrice()}" /><sup style="font-size: 12px;"> đ</sup></td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
														<div class="row">
															<div class="col-sm-4"></div>
															<div class="col-sm-8 price" style="padding-top: 3%;">Total
																Price : <fmt:formatNumber type="number" pattern="###,###" value="${total}" /><sup style="font-size: 12px;"> đ</sup></div>
														</div>
													</div>

												</div>
												<button type="submit" class="btn btn-success" style="margin-left: 19%;font-size: 16px;width: 100%;margin-top: 5%;border: none;">Confirm</button>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6"></div>
						<div class="col-md-6"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
		<jsp:include page="include/footer.jsp" />
	</div>
	<jsp:include page="include/footer-script.jsp" />
</body>
</html>