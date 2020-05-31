<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login/Register</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link href="<c:url value="/resources/css/animate.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/login/custom.css"/>" rel="stylesheet">
</head>
<body id="body-login">
	<div id="body-flex-login">
		<div id="container-login">
			<div id="container-login-left">
				<div id="header-left" class="header-login">
					<span id="text-logo">Welcom</span> <br />
					<span id="hint-text-logo">Let create your style with BeMineShop !</span>
				</div>
				<div id="header-bottom">
					<p><img src="<c:url value="/resources/images/login/icon_oval.png"/>" alt="" /><span>Always updated with the latest fashion trends</span></p>
					<p><img src="<c:url value="/resources/images/login/icon_oval.png"/>" alt="" /><span>Discount 50% on items reserved for other VIP</span></p>
					<p><img src="<c:url value="/resources/images/login/icon_oval.png"/>" alt="" /><span>Enthusiastic advice to create your own style</span></p>
				</div>
				
			</div>
			<div id="container-login-right">
				<div class="header-login" id="header-top-right">
					<a href="#" id="si"><span id="sign-in" class="active">Sign In</span></a>/
					<a href="#" id="su"><span id="sign-up">Sign Up</span></a>
				
				</div>
				<div class="form-sign-in">
					<p id="alert-message" style="text-align: center; color: firebrick;">${message}</p>
					<div id="container-center-right">
						<form style="position: relative;" action="<c:url value="j_spring_security_check"/>" method="post" autocomplete="false" role="form">
							<input  name="username" type="text" class="login-input email-input" placeholder="Email" required="required"/><br />
							<input id="password"  name="password" type="password" class="login-input pass-input" placeholder="Password" required="required"/><br />
							<span id="btn-showpass" style="position: absolute;border-radius: 5px 5px;right: 1%;top: 37%;height: 32px;" class="btn btn-warning">Show</span>
							<input type="submit" value="Sign In" class="btn-login"/>
							  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						</form>
					</div>
				</div>	
				<div class="form-sign-up">
					<div id="container-center-right">
					<p id="alert-message-2" style="text-align: center; color: firebrick;">${message}</p>
						<form id="form-sign-up">
							<input name="customerName" type="text" class="login-input name-input" placeholder="Full Name"/><br />
							<input name="address" type="text" class="login-input address-input" placeholder="Address"/><br />
							<input name="phoneNumber" type="text" class="login-input phone-input" placeholder="Phone Number"/><br />
							<input name="email" type="text" class="login-input email-input" placeholder="Email"/><br />
							<input name="password" type="password" class="login-input pass-input" placeholder="Password"/><br />
							<input name="rePassword" type="password" class="login-input pass-input" placeholder="Re-Password"/><br />
							<input style="padding-top: 6px;" name="avatar" type="file" id="avatar" class="login-input file-input"/><br />
							<input id="btn-form-sign-up" type="submit" value="Sign Up" class="btn-login"/>
						</form>
					</div>
				</div>
					<div id="social">
						<img src="<c:url value="/resources/images/login/icon_facebook.png"/>" alt="" />
						<img src="<c:url value="/resources/images/login/icon_google.png"/>" alt="" />
					</div>
				
			
			</div>
		</div>
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src='<c:url value="/resources/js/login/custom.js"/>'></script>
<script type="text/javascript" src="<c:url value="/resources/js/login/bootstrap.min.js"/>"></script>

</html>