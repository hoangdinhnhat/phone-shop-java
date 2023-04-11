<%-- 
    Document   : BodyProfile
    Created on : Mar 12, 2023, 10:56:00 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div id="account-management">
            <h1>List Ordered</h1>
            <div class="accounts">
                <c:if test="${requestScope.LIST_ORDER != null}">
                    <c:if test="${not empty requestScope.LIST_ORDER}">
                        <form class="account" action="MainController" method="POST">
                            <div style="font-weight: bold;">STT</div>
                            <div style="font-weight: bold;">Order ID</div>
                            <div style="font-weight: bold;">Order Date</div>
                            <div style="font-weight: bold;">Expected Delivery Date</div>
                            <div style="font-weight: bold;">Payment Method</div>
                            <div style="font-weight: bold;">Shipping Unit</div>
                            <div style="font-weight: bold;">Phones</div>
                        </form>
                        <c:forEach var="order" varStatus="counter" items="${requestScope.LIST_ORDER}">
                            <form class="account" action="MainController" method="POST">
                                <div class="account-details">${counter.count}</div>
                                <div class="account-details">${order.orderID}</div>
                                <div class="account-details">${order.orderDate}</div>
                                <div class="account-details">${order.expectedDeliveryDate}</div>
                                <div class="account-details">${order.paymentMethod}</div>
                                <div class="account-details">${order.shippingUnit}</div>
                                <select name="OrderedPhones">
                                    <c:forEach var="phone" items="${order.phones}">
                                        <option value="">${phone.phoneName}</option>
                                    </c:forEach>
                                </select>
                            </form>
                        </c:forEach>
                    </c:if>
                </c:if>
            </div>
            <div class="page-sep">
                <a href="MainController?action=OrderPlaced&curPage=first" class="page-link">First</a>
                <a href="MainController?action=OrderPlaced&curPage=previous" class="page-link">Prev</a>
                <a href="MainController?action=OrderPlaced&curPage=${requestScope.curPage}" class="page-link">${requestScope.curPage}</a>
                <a href="MainController?action=OrderPlaced&curPage=next" class="page-link">Next</a>
            </div>
        </div>
    </body>
</html>
