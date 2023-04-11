<%-- 
    Document   : forgetPassword
    Created on : Mar 13, 2023, 6:03:38 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import var="Body" url="ConfirmForgetCode/ConfirmCodeBody.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forget Password Page</title>
        <link rel="stylesheet" href="General.css">
        <link rel="stylesheet" href="ConfirmForgetCode/style.css">
    </head>
    <body>
        <c:if test="${sessionScope.PENDING_NEW_PASS == null}" >
            <c:redirect url="login.jsp"/>
        </c:if>
        <c:import url="Layout/OnlyLogoLayout.jsp">
            <c:param name="Body" value="${Body}"/>
        </c:import>
    </body>
</html>
