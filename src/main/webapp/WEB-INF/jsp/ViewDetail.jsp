<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>TQ Shop</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<jsp:include page="include/head-css.jsp"/>
</head>
<body id="detail">
	<div class="wrapper">
		<jsp:include page="include/header.jsp"/>
		   <div class="clearfix">
        </div>
        <div class="page-index" style="background-color: #bdad7c;">
          <div class="container" >
        	<div class="col-md-12 mb-0" >
        		<a href='<c:url value="/"/>'>Home</a>
        		<span class="mx-2 mb-0">/</span>
        		<strong class="text-black">Products Details</strong>
        	</div>
          </div>
        </div>
		<div class="clearfix"></div>
		<jsp:include page="include/body-detail.jsp"/>
		<div class="clearfix"></div>
		<jsp:include page="include/footer.jsp"/>
	</div>
	<jsp:include page="include/footer-script.jsp"/>
    <script type="text/javascript">
        new WOW().init();
    </script>
</body>
</html>