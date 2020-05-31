<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="../include/header-css-user.jsp" />
<title>Management Order</title>
</head>
<body>
	<section id="container">
		<jsp:include page="../include/header-user.jsp" />
		<jsp:include page="../include/menu-user.jsp" />
		<%-- <jsp:include page="../include/main-user.jsp" /> --%>
		 <section id="main-content">
      <section class="wrapper">
        <div class="col-lg-12 mt">
          <div class="row content-panel">
            <div class="col-lg-10 col-lg-offset-1">
              <div class="invoice-body">
                <div class="pull-left">
                  <h1>BEMINE</h1>
                  <address>
                <strong>Thu Hai, Inc.</strong><br>
                110 Phuoc Tuong 5<br>
                Da Nang<br>
                <abbr title="Phone">P:</abbr> 0354633700
              </address>
                </div>
                <!-- /pull-left -->
                <div class="pull-right">
                  <h2>invoice</h2>
                </div>
                <!-- /pull-right -->
                <div class="clearfix"></div>
                <br>
                <br>
                <br>
                <div class="row">
                  <div class="col-md-8">
                    <h4>${info.getCustomer().customerName }</h4>
                    <address>
                  <strong>${info.getCustomer().address }</strong><br>
                 	${info.getCustomer().email}<br>
                  <abbr title="Phone">P:</abbr> ${info.getCustomer().phoneNumber}
                </address>
                  </div>
                  <!-- /col-md-9 -->
                  <div class="col-md-4">
                    <br>
                    <div>
                      <div class="pull-left"> INVOICE NO : </div>
                      <div class="pull-right"> ${info.getOrderId()}</div>
                      <div class="clearfix"></div>
                    </div>
                    <div>
                      <!-- /col-md-3 -->
                      <div class="pull-left"> INVOICE DATE : </div>
                      <div class="pull-right"> ${info.getDateCreate() }</div>
                      <div class="clearfix"></div>
                    </div>
                    <!-- /row -->
                    <br>
                   <!--  <div class="well well-small green">
                      <div class="pull-left"> Total Due : </div>
                      <div class="pull-right"> 8,000 USD </div>
                      <div class="clearfix"></div>
                    </div> -->
                  </div>
                  <!-- /invoice-body -->
                </div>
                <!-- /col-lg-10 -->
                <table class="table">
                  <thead>
                    <tr>
                      <th style="width:60px" class="text-center">QTY</th>
                      <th class="text-left">PRODUCT</th>
                       <th class="text-left">SIZE</th>
                       <th class="text-left">COLOR</th>
                      <th style="width:140px" class="text-right">UNIT PRICE</th>
                      <th style="width:90px" class="text-right">TOTAL</th>
                    </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${details}" var="d">
                    <tr>
                      <td class="text-center">${d.quantity}</td>
                      <td class="text-left">${d.productEntity.productName }</td>
                      <td class="text-left">${d.size }</td>
                      <td  class="text-left">${d.color }</td>
                      <td class="text-right"><fmt:formatNumber type="number" pattern="###,###" value="${d.productEntity.price }" /> VND
                      </td>
                      <td class="text-right"><fmt:formatNumber type="number" pattern="###,###" value="${d.getTotalPrice() }" /> VND</td>
                    </tr>
                    </c:forEach>
          			
                    <tr>
                      <td colspan="4" rowspan="20">
                        <h4>Terms and Conditions</h4>
                        <p>Thank you for your business. We do expect payment within 21 days, so please process this invoice within that time. There will be a 1.5% interest charge per month on late invoices.</p>
                     </td>
                    </tr>
                    <tr>
                      <td class="text-right no-border" style="padding-bottom: 2%;">
                        <div class="well well-small green"><strong>Total</strong></div>
                      </td>
                      <td class="text-right"><strong><fmt:formatNumber type="number" pattern="###,###" value="${total}" /> VND</strong></td>
                    </tr>
                  </tbody>
                </table>
                <br>
                <br>
                <a href="<c:url value ="/user/PrintOrder"/>">Print</a>
              </div>
              <!--/col-lg-12 mt -->
      </section>
      <!-- /wrapper -->
    </section>

		<%-- <jsp:include page="../include/footer-user.jsp" /> --%>
	</section>
	<jsp:include page="../include/footer-js-user.jsp" />
</body>
</html>