<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>TQ Shop</title>
        <jsp:include page="include/head-css.jsp"/>
    </head>
    <body id="home">
        <div class="wrapper">
            <jsp:include page="include/header.jsp"/>
            <jsp:include page="include/menu.jsp"/>
	        <div class="clearfix"></div>
	        <div class="container_fullwidth">
	        	
		            <div class="container">
		 			<jsp:include page="include/banner-mid.jsp"/>
		 			<div class="clearfix"></div>
		 			<jsp:include page="include/banner-bot.jsp"/>
		 			<div class="clearfix"></div>
		 			
		 			<div class="our-brand">
	                  	<h3 class="title"><strong>Our </strong> Brands</h3>
	                  	<div class="control"><a id="prev_brand" class="prev" href="#">&lt;</a><a id="next_brand" class="next" href="#">&gt;</a></div>
		                  <ul id="braldLogo">
		                     <li>
		                        <ul class="brand_item">
		                           <li>
		                              <a href="#">
		                                 <div class="brand-logo"><img src="<c:url value="/resources/images/envato.png"/>" alt=""></div>
		                              </a>
		                           </li>
		                           <li>
		                              <a href="#">
		                                 <div class="brand-logo"><img src="<c:url value="/resources/images/themeforest.png"/>" alt=""></div>
		                              </a>
		                           </li>
		                           <li>
		                              <a href="#">
		                                 <div class="brand-logo"><img src="<c:url value="/resources/images/photodune.png"/>" alt=""></div>
		                              </a>
		                           </li>
		                           <li>
		                              <a href="#">
		                                 <div class="brand-logo"><img src="<c:url value="/resources/images/activeden.png"/>" alt=""></div>
		                              </a>
		                           </li>
		                           <li>
		                              <a href="#">
		                                 <div class="brand-logo"><img src="<c:url value="/resources/images/envato.png"/>" alt=""></div>
		                              </a>
		                           </li>
		                        </ul>
		                     </li>
		                     <li>
		                        <ul class="brand_item">
		                           <li>
		                              <a href="#">
		                                 <div class="brand-logo"><img src="<c:url value="/resources/images/envato.png"/>" alt=""></div>
		                              </a>
		                           </li>
		                           <li>
		                              <a href="#">
		                                 <div class="brand-logo"><img src="<c:url value="/resources/images/themeforest.png"/>" alt=""></div>
		                              </a>
		                           </li>
		                           <li>
		                              <a href="#">
		                                 <div class="brand-logo"><img src="<c:url value="/resources/images/photodune.png"/>" alt=""></div>
		                              </a>
		                           </li>
		                           <li>
		                              <a href="#">
		                                 <div class="brand-logo"><img src="<c:url value="/resources/images/activeden.png"/>" alt=""></div>
		                              </a>
		                           </li>
		                           <li>
		                              <a href="#">
		                                 <div class="brand-logo"><img src="<c:url value="/resources/images/envato.png"/>" alt=""></div>
		                              </a>
		                           </li>
		                        </ul>
		                     </li>
		                  </ul>
	               </div>
		 		</div>
		 	</div>	
		 	<div class="clearfix"></div>
	            <jsp:include page="include/footer.jsp"/>
        </div>
        <jsp:include page="include/footer-script.jsp"/>
        <script type="text/javascript">
        	new WOW().init();
        </script>
    </body>
</html>
