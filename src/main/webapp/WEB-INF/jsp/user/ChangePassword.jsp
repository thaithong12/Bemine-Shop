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
							Account</h1>
						<div class="row mt">
							<div class="col-md-12">
								<div class="content-panel">
									<div class="form-sign-up">
										<div id="container-center-right">
											<h2>Information</h2>
											
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