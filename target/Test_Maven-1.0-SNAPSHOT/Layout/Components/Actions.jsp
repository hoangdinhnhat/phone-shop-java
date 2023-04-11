<%-- 
    Document   : Cart
    Created on : Mar 12, 2023, 5:19:41 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div class="actions">
            <a href="cart.jsp">CART</a>
            <c:if test="${sessionScope.LOGIN_USER.roleID == 'US'}">
                <a href="userProfile.jsp">PROFILE</a>
            </c:if>
            <c:if test="${sessionScope.LOGIN_USER.roleID == 'AD'}">
                <a href="MainController?action=Search Account&search=">ADMIN</a>
            </c:if>
            <c:if test="${sessionScope.LOGIN_USER != null}">
                <a href="MainController?action=Logout">LOG OUT</a>
            </c:if>
            <c:if test="${sessionScope.LOGIN_USER == null}">
                <a href="login.jsp">LOG IN</a>
            </c:if>
        </div>
    </body>
</html>
