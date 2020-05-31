<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="img/favicon.png" rel="icon">
<jsp:include page="../include/header-css-user.jsp" /> 
<title>User Home</title>
</head>
<body>
	<section id="container">
		<jsp:include page="../include/header-user.jsp" />
		<jsp:include page="../include/menu-user.jsp" />
		<jsp:include page="../include/main-user.jsp" />
		<%-- <jsp:include page="../include/footer-user.jsp" /> --%>
	</section>
<jsp:include page="../include/footer-js-user.jsp" /> 
</body>
</html>
