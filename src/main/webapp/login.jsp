<%-- 
    Document   : login
    Created on : Mar 12, 2023, 11:05:55 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import var="Body" url="Login/BodyLogin.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="General.css">
        <link rel="stylesheet" href="Login/login.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <script src="https://accounts.google.com/gsi/client" async defer></script>
    </head>
    <body>
        <c:import url="Layout/OnlyLogoLayout.jsp">
            <c:param name="Body" value="${Body}"/>
        </c:import>
        <script src="Login/login.js"></script>
    </body>
</html>
