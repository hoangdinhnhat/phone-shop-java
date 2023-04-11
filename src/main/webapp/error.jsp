<%-- 
    Document   : error
    Created on : Mar 20, 2023, 2:23:12 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import var="Body" url="Error/errorBody.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
        <link rel="stylesheet" href="General.css">
        <link rel="stylesheet" href="Error/style.css">
    </head>
    <body>
        <c:import url="Layout/OnlyLogoLayout.jsp">
            <c:param name="Body" value="${Body}"/>
        </c:import>
    </body>
</html>
