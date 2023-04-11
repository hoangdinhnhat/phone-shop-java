<%-- 
    Document   : registeConfirmPage
    Created on : Mar 14, 2023, 4:56:30 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import var="Body" url="RegisteConfirm/registeConfirmBody.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registe Confirm Page</title>
        <link rel="stylesheet" href="General.css">
        <link rel="stylesheet" href="RegisteConfirm/registeConfirm.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    </head>
    <body>
        <c:import url="Layout/OnlyLogoLayout.jsp">
            <c:param name="Body" value="${Body}"/>
        </c:import>
        <script src="RegisteConfirm/registeConfirm.js"></script>
    </body>
</html>
