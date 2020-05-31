<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../include/header-css-user.jsp" />
<link href="<c:url value= "/resources/css/user/Info.css"/>"
	rel="stylesheet">
<title>Management Account</title>
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
							Account</h1>
						<div class="row mt">
							<div class="col-md-12">
								<div class="content-panel">
									<div class="form-sign-up">
										<div id="container-center-right">
											<h2>Information</h2>
											<p id="msg-account">${msg}</p>
											<form id="change-info">
												<label for="email">Email :</label>
												 <input value="${user.email}" type="text" readonly="readonly"
													class="form-control" id="email" name="email"> 
													<br /> <br /> 
													
													<label for="pwd">Addess :</label> 
													<input value="${user.address }" type="text" required="required" class="form-control"
													 name="address">
												<br /> <br /> 
												
												<label for="pwd">Phone Number :</label> <input
													value="${user.phoneNumber }" type="text"
													required="required" class="form-control" name="phoneNumber">
												<br /> <br />
												
												 <label for="pwd">Full Name :</label> 
												 <input value="${user.customerName}" type="text" required="required" class="form-control" name="customerName">
												 <br /> <br /> 
												 <a href="" id="change-ava">Change Avatar</a>
												<div id="focus-ava">
													<label for="pwd">Avatar :</label> <input disabled="disabled" name="avatar" id="avatar" value="${user.avatar }" type="file" class="form-control">
													
												</div>
												<br /> <br />
												<p style="display: none;"><input hidden value="${user.id}" class="form-control" name="id"/>
												</p>
												<div style="clear: both;"></div>
												<button style="margin-left: 12%;" type="submit" class="btn btn-primary">Submit</button>
												<a href="#" id="change-password">Change Password</a>
											</form>
											
											
											<form id="form-change-pass" class="form-inline">		
												<div id="change-pass">
													<br />
													<br /> 
													<label for="pwd">Password :</label> 
													<input name="oldPass" type="password" class="form-control" >
													
													<br />
													<br /> 
													<label for="pwd">New Password :</label> 
													<input name="newPass" type="password" class="form-control" >
													
													<br />
													<br /> 
													<label for="pwd">Re-New Password :</label> 
													<input name="reNewPass" type="password" class="form-control">
													
													<p style="display: none;"><input hidden value="${user.id}"
														class="form-control" name="id" id="data-account"/>
												</p>
												</div>
												
												<br /><br />
												<div style="clear: both;"></div>
												<a id="back-info" href="">Back</a> <button id="btn-submit-change-pass" class="btn btn-primary">Submit</button>
											</form>
										</div>
									</div>
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