<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="header">
        <div class="container">
          <div class="row">
            <div class="col-md-2 col-sm-2">
              <div class="logo">
                <a href="<c:url value="/"/>">
                  <img src="<c:url value="/resources/images/br/logo.png"/>" style="height: 90px !important;" alt="TQSHOP">
                </a>
              </div>
            </div>
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
                        <a href="checkout.html" class="log">
                          Login
                        </a>
                      </li>
                      <li>
                        <a href="checkout2.html" class="reg">
                          Register
                        </a>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
              <div class="clearfix">
              </div>
              <div class="header_bottom">
                <ul class="option">
                  <li id="search" class="search">
                    <form>
                      <input class="search-submit" type="submit" value="">
                      <input class="search-input" placeholder="Enter your search term..." type="text" value="" name="search">
                    </form>
                  </li>
                  <li class="option-cart">
                    <a href="#" class="cart-icon">
                      cart 
                      <span class="cart_no">
                        02
                      </span>
                    </a>
                    <ul class="option-cart-item">
                      <li>
                        <div class="cart-item">
                          <div class="image">
                            <img src="<c:url value="/resources/images/products/thum/products-01.png"/>" alt="">
                          </div>
                          <div class="item-description">
                            <p class="name">
                              Lincoln chair
                            </p>
                            <p>
                              Size: 
                              <span class="light-red">
                                One size
                              </span>
                              <br>
                              Quantity: 
                              <span class="light-red">
                                01
                              </span>
                            </p>
                          </div>
                          <div class="right">
                            <p class="price">
                              $30.00
                            </p>
                            <a href="#" class="remove">
                              <img src="<c:url value="/resources/images/remove.png"/>" alt="remove">
                            </a>
                          </div>
                        </div>
                      </li>
                      <li>
                        <div class="cart-item">
                          <div class="image">
                            <img src="<c:url value="/resources/images/products/thum/products-02.png"/>" alt="">
                          </div>
                          <div class="item-description">
                            <p class="name">
                              Lincoln chair
                            </p>
                            <p>
                              Size: 
                              <span class="light-red">
                                One size
                              </span>
                              <br>
                              Quantity: 
                              <span class="light-red">
                                01
                              </span>
                            </p>
                          </div>
                          <div class="right">
                            <p class="price">
                              $30.00
                            </p>
                            <a href="#" class="remove">
                              <img src="<c:url value="/resources/images/remove.png"/>" alt="remove">
                            </a>
                          </div>
                        </div>
                      </li>
                      <li>
                        <span class="total">
                          Total 
                          <strong>
                            $60.00
                          </strong>
                        </span>
                        <button class="checkout" onClick="location.href='checkout.html'">
                          CheckOut
                        </button>
                      </li>
                    </ul>
                  </li>
                </ul>
                <div class="navbar-header">
                  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">
                      Toggle navigation
                    </span>
                    <span class="icon-bar">
                    </span>
                    <span class="icon-bar">
                    </span>
                    <span class="icon-bar">
                    </span>
                  </button>
                </div>
                <div class="navbar-collapse collapse">
                  <ul class="nav navbar-nav">
                    <li class="active dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        Home
                      </a>
                      <div class="dropdown-menu">
                        <ul class="mega-menu-links">
                          <li>
                            <a href="index.html">
                              home
                            </a>
                          </li>
                          <li>
                            <a href="home2.html">
                              home2
                            </a>
                          </li>
                          <li>
                            <a href="home3.html">
                              home3
                            </a>
                          </li>
                          <li>
                            <a href="productlitst.html">
                              Productlitst
                            </a>
                          </li>
                          <li>
                            <a href="productgird.html">
                              Productgird
                            </a>
                          </li>
                          <li>
                            <a href="details.html">
                              Details
                            </a>
                          </li>
                          <li>
                            <a href="cart.html">
                              Cart
                            </a>
                          </li>
                          <li>
                            <a href="checkout.html">
                              CheckOut
                            </a>
                          </li>
                          <li>
                            <a href="checkout2.html">
                              CheckOut2
                            </a>
                          </li>
                          <li>
                            <a href="contact.html">
                              contact
                            </a>
                          </li>
                        </ul>
                      </div>
                    </li>
                    <li>
                      <a href="productgird.html">
                        men
                      </a>
                    </li>
                    <li>
                      <a href="productlitst.html">
                        women
                      </a>
                    </li>
                    <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        Fashion
                      </a>
                      <div class="dropdown-menu mega-menu">
                        <div class="row">
                          <div class="col-md-6 col-sm-6">
                            <ul class="mega-menu-links">
                             	<c:forEach items="${categories}" var="cate">
                                    <li><a href='<c:url value="/CategoryProduct/${cate.categoryId}"/>'>${cate.categoryName}</a></li>
                                </c:forEach>
                            </ul>
                          </div>
                         
                        </div>
                      </div>
                    </li>

                    <li>
                      <a href="productgird.html">
                        blog
                      </a>
                    </li>
                    <li>
                      <a href="contact.html">
                        contact us
                      </a>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="clearfix">
        </div>
        <div class="page-index">
          <div class="container">
            <p>
              Home - Products Details
            </p>
          </div>
        </div>
      </div>