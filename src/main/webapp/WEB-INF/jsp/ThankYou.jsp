<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
		<div class="page-index" style="background-color: #bdad7c;">
			<div class="container">
				<div class="col-md-12 mb-0">
					<a style="color: aliceblue;" href='<c:url value="/"/>'>Home</a> <span
						class="mx-2 mb-0">/</span> <a style="color: aliceblue;"
						href='<c:url value="/CheckOut"/>'>Cart</a> <span class="mx-2 mb-0">/</span>
						<a style="color: aliceblue;"
						href='<c:url value="/CheckOut"/>'>Check Out</a> <span class="mx-2 mb-0">/</span>
					<strong class="text-black">Thank You</strong>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>

		<div class="row" style="background-color: white;">
			<div class="container">
				<div class="row" style="text-align: center;">
					<div class="col-sm-12 text-center" style="margin-top: 7%;">
						<img src="<c:url value="/resources/images/ico/check.png"/>" alt="" />
						<h2 style="font-size: 4.5rem;font-weight: 300;line-height: 1.2;color: #000;font-family: inherit;">Thank you!</h2>
						<p style="font-size: 1.25rem;margin-bottom: 2rem!important;font-weight: 300;">${msg}</p>
						<p style="font-size: 1.25rem;margin-bottom: 3rem!important;font-weight: 300;">Email have sent for you.</p>
						<p style="margin-bottom:3rem!important; ">
							<a href="<c:url value="/"/>" class="btn btn-primary" style="background-color: #2196F3;border-color: none;">BACK TO SHOP</a>
						</p>
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