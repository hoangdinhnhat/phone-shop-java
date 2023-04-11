<%-- 
    Document   : BodyCart
    Created on : Mar 12, 2023, 9:50:20 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div id="cart">
            <div class="list-product">
                <h1 class="name-cart">Shopping Cart</h1>
                <div class="items">
                    <c:if test="${sessionScope.CART != null}">
                        <c:set var="total" value="${0}"/>
                        <c:forEach var="phoneID" varStatus="counter" items="${sessionScope.CART.phoneIDs}">
                            <c:set var="total" value="${pageScope.total + sessionScope.CART.cart[phoneID].price * sessionScope.CART.cart[phoneID].quantity}"/>
                            <form action="MainController" method="POST">
                                <div class="item">
                                    <div>
                                        <img src="${sessionScope.CART.cart[phoneID].imageURL}" alt="AAA">
                                    </div>
                                    <div>${sessionScope.CART.cart[phoneID].phoneName}</div>
                                    <div>
                                        <fmt:setLocale value = "vi_VN"/>
                                        <fmt:formatNumber value = "${sessionScope.CART.cart[phoneID].price}" type = "currency"/>
                                    </div>
                                    <div>
                                        <input type="number" name="quantity" value="${sessionScope.CART.cart[phoneID].quantity}" min="1" />
                                    </div>
                                    <div>
                                        <fmt:setLocale value = "vi_VN"/>
                                        <fmt:formatNumber value = "${sessionScope.CART.cart[phoneID].price * sessionScope.CART.cart[phoneID].quantity}" type = "currency"/>
                                    </div>
                                    <div>
                                        <input type="text" hidden name="productID" value="${sessionScope.CART.cart[phoneID].phoneID}" />
                                        <input type="submit" name="action" value="Remove" />
                                    </div>
                                    <div>
                                        <input type="submit" name="action" value="Edit" />
                                    </div>
                                </div>
                            </form>
                        </c:forEach>
                    </c:if>
                    <c:if test="${sessionScope.CART == null}">
                        <p style="text-align: center;font-size: 20px;">THERE NOTHING</p>
                    </c:if>
                </div>
                <c:if test="${sessionScope.CART != null}">
                    <div class="total-price">
                        <div>
                            <div>TOTAL PRICE:</div>
                            <div class="total">
                                <fmt:setLocale value = "vi_VN"/>
                                <fmt:formatNumber value = "${pageScope.total}" type = "currency"/>
                            </div>
                        </div>
                        <p>${requestScope.CHECKOUT_ERROR}</p>
                    </div>
                </c:if>
                <a href="/PRJ301_Assignment/MainController?action=Search Product&search=" style="left: 10px;">Back to shop</a>
                <a href="/PRJ301_Assignment/MainController?action=OrderPlaced" style="right: 10px;">View orders placed </a>
            </div>
            <div class="method-buy">
                <form action="MainController" method="POST">
                    <h1 class="name-cart">Delivery address</h1>
                    <input type="text" name="receiver-name" placeholder="Receiver Name" required="">
                    <input type="text" name="receiver-phone" placeholder="Receiver Phone" required="">
                    <input type="text" name="delivery-add" placeholder="Address" required="">
                    <br>
                    <select name="province">
                        <option value="hochiminh">Ho Chi Minh City</option>
                    </select>
                    <select name="district">
                        <option value="thuduc">Thu Duc</option>
                    </select>
                    <select name="ward">
                        <option value="longthanhmy">Long Thanh My</option>
                        <option value="hiepphu">Hiep Phu</option>
                        <option value="tanphu">Tan Phu</option>
                        <option value="phuhuu">Phu Huu</option>
                    </select>

                    <h1 class="name-cart">Payment Methods</h1>
                    <div class="cod-payment payments">
                        <input type="radio" name="payment-method" value="cod" required>
                        <div class="method-des">
                            <img src="images/cod.webp" alt="">
                        </div>
                    </div>
                    <div class="zalopay-payment payments">
                        <input type="radio" name="payment-method" value="zalopay">
                        <div class="method-des">
                            <img src="images/zalopay.webp" alt="">
                        </div>
                    </div>
                    <c:if test="${sessionScope.LOGIN_USER == null}">
                        <a class="checkout-btn" id="loginRedirect" href="login.jsp">LOGIN</a>
                    </c:if>
                    <c:if test="${sessionScope.LOGIN_USER != null}">
                        <input class="checkout-btn" type="submit" name="action" value="Checkout">
                    </c:if>
                </form>
            </div>
        </div>
    </body>
</html>
