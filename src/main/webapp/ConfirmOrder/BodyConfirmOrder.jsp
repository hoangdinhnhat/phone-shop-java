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
        <div id="wrapper">
            <div class="notify">
                You have placed an order successfully. Below is the order information
                you have just placed.
            </div>
            <div class="user-order">
                <table>
                    <tr>
                        <th>Order ID</th>
                        <th>Order Date</th>
                        <th>Expected Delivery Date</th>
                        <th>Payment Method</th>
                        <th>Shipping Unit</th>
                        <th>Phones</th>
                    </tr>
                </table>
                <table>
                    <tr>
                        <th>${requestScope.ORDER_DETAILS.orderID}</th>
                        <th>${requestScope.ORDER_DETAILS.orderDate}</th>
                        <th>${requestScope.ORDER_DETAILS.expectedDeliveryDate}</th>
                        <th>${requestScope.ORDER_DETAILS.paymentMethod}</th>
                        <th>${requestScope.ORDER_DETAILS.shippingUnit}</th>
                        <th>
                            <select>
                                <option>List Phone</option>
                                <c:forEach var="phone" items="${requestScope.ORDER_DETAILS.phones}">
                                    <option>${phone.phoneName}</option>
                                </c:forEach>
                            </select>
                        </th>
                    </tr>
                </table>
            </div>
            <div class="notify-redirect">
                You will be redirect to the shopping page in 10 seconds. Click the below button if you want to immediately.
            </div>
            <a href="MainController?action=Search Product&search=" class="redirect">
                Shopping Page
            </a>
        </div>
    </body>
</html>
