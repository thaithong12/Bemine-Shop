<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<aside>
	<div id="sidebar" class="nav-collapse ">
		<!-- sidebar menu start-->
		<ul class="sidebar-menu" id="nav-accordion">
			<p class="centered">
				<a href="<c:url value= "/user/ViewAccount" />">
				<c:if test="${empty user.avatar}">
					<img src="<c:url value="/resources/images/ui-sam.jpg"/>" class="img-circle" width="80">
				</c:if>
					<c:if test="${not empty user.avatar}">
					<img src="<c:url value="/resources/images/avatar/${user.avatar}"/>" class="img-circle" width="80">
				</c:if>
				</a>
			</p>
			<h5 class="centered">${user.customerName}</h5>
			<li class="mt">
				<a class="paging-menu" href="<c:url value="/user/home"/>"> 
					<i class="fa fa-dashboard"></i> 
					<span>Home</span>
				</a>
			</li>
			<li class="sub-menu">
				<a  class="paging-menu" href="<c:url value="/user/ViewAccount" />"> 
					<i class="fa fa-desktop"></i> 
					<span>Manage Account</span>
				</a>
			</li>
			<li class="sub-menu">
				<a class="paging-menu" href="<c:url value ="/user/ViewOrder/1"/>"> 
					<i class="fa fa-cogs"></i> 
					<span>Manage Order</span>
				</a>
			</li>

			<li>
				<a class="paging-menu" href="<c:url value= "/user/Favourites/${user.id}" />">
					<i class="fa fa-heart"></i> <span>Favourites</span>
				</a>
			</li>
		</ul>
		<!-- sidebar menu end-->
	</div>
</aside>