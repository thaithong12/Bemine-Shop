<%@ taglib uri="http://www.springframework.org/security/tags"  prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <div class="header">
            <div class="container">
               <div class="row">
                  <div class="col-md-2 col-sm-2">
                     <div class="logo" id="logo-background"><a href='<c:url value="/"/>' ><img src="<c:url value="/resources/images/br/logo.png"/>" style="height: 66px !important;" alt="TQSHOP"></a></div>
                  </div>
                 <%--   --%>
                  <div class="col-md-10 col-sm-10">
                     <div class="header_top">
                        <div class="row">
                           <div class="col-md-3">
                           </div>
                           <div class="col-md-6">
                             
                           </div>
                           <div class="col-md-3">
                              <ul class="usermenu">
                                 <li>
                                 	<span>
							         	<sec:authorize access="isAuthenticated()">
								            <sec:authorize access="hasRole('ROLE_ADMIN')">
								                <span><a style="color:#f58d7a; " class="" href="<c:url value="/admin/home"/>">Admin AC</a></span>
								            </sec:authorize>
								              <sec:authorize access="hasRole('ROLE_USER')">
								                <span><a style="padding-right: 6px;" class="" href="<c:url value="/user/home"/>">   ${user.customerName}
								                </a></span>
								            </sec:authorize>
								            <span><a style="padding-left: 26px;" class="log" href="<c:url value="/logout" />"> Logout</a></span>
									 	</sec:authorize>
								        <sec:authorize access="!isAuthenticated()">
								            <span><a style="padding-right: 17px;" class="log" href="<c:url value="/login" />">Login</a></span>
								            <span style="padding-left: 1px;"><a class="reg" href="<c:url value="/login" />">Register</a></span>
							    		</sec:authorize>
	            					</span>
                                </li>
                               <!--  <li><a href="checkout2.html" class="reg">Register</a></li> -->
                              </ul>
                           </div>
                        </div>
                     </div>
                     <div class="clearfix"></div>
                     <div class="header_bottom">
                        <ul class="option">
                           <li id="search" class="search">
                           		<form action='<c:url value="/SearchProducts"/>' method="get" >
	                              	<input class="search-submit btn-search"  type="submit" value="">
	                              	<input id="search-input" autocomplete="off" maxlength="50" class="search-input" placeholder="Enter your search name product..." type="text" name="search">
	                              	<div id="fs-result" class="fs-result col-sm-12">
	                              		<div class="fs-sremain" style="position: absolute; width: 100%;">
	                              			<ul id="filter-search-here"   style="overflow-y : scroll;max-height: 250px;">
	                              				
	                              			</ul>
	                              		</div>
	                              	</div>
                              	</form>
                           </li>
                           <li class="option-cart">
                              <a href="<c:url value= "/ViewCart"/>" class="cart-icon">cart <span id="cart-here" class="cart_no">00</span></a>
                              <ul  class="option-cart-item">
									<div id="filter-cart" style="overflow: auto; max-height:200px;" >
										<span>No product in your cart</span>
									</div>
                               
                            
                                 <li><span class="total">Total <strong id="total-cart" style="text-transform: lowercase;">$00.00</strong></span>
                                 <button class="checkout" onclick="window.location.href='CheckOut'">CheckOut</button></li>
                              </ul>
                           </li>
                        </ul>
                        <div class="navbar-header"><button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button></div>
                        <div class="navbar-collapse collapse">
                           <ul class="nav navbar-nav">
                              <li class="active ab">
                                 <a href="<c:url value="/home"/>" >Home</a>
                                
                              </li>
                              <li class="ab"><a href='<c:url value = "/TypeProduct/MALE/1"/>'>men</a></li>
                              <li class="ab"><a href="<c:url value = "/TypeProduct/FEMALE/1"/>">women</a></li>
                              <li class="dropdown ab">
                                 <a href="#" class="dropdown-toggle" data-toggle="dropdown">Fashion</a>
                                 <div class="dropdown-menu mega-menu">
                                    <div class="row">
                                       <div class="col-md-6 col-sm-6">
                                          <ul class="mega-menu-links">
                                          <c:forEach items="${categories}" var="cate">
                                          		 <li><a href='<c:url value="/CategoryProduct/${cate.categoryId}/1"/>'>${cate.categoryName}</a></li>
                                          </c:forEach>

                                          </ul>
                                       </div>

                                    </div>
                                 </div>
                              </li>
                              <li class="ab"><a href="productgird.html">blog</a></li>
                             
                              <li class="ab"><a href="contact.html">contact us</a></li>
                           </ul>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>