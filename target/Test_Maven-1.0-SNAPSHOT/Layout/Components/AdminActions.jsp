<%-- 
    Document   : AdminActions
    Created on : Mar 14, 2023, 3:08:42 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div class="actions">
            <a href="cart.jsp">CART</a>
            <a href="userProfile.jsp">PROFILE</a>
            <c:if test="${sessionScope.LOGIN_USER != null}">
                <a href="MainController?action=Logout">LOG OUT</a>
            </c:if>
            <c:if test="${sessionScope.LOGIN_USER == null}">
                <a href="login.jsp">LOG IN</a>
            </c:if>
        </div>
    </body>
</html>
