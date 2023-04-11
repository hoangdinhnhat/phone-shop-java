<%-- 
    Document   : index
    Created on : Mar 11, 2023, 5:12:28 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import var="Body" url="ConfirmOrder/BodyConfirmOrder.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
        <link rel="stylesheet" href="General.css">
        <link rel="stylesheet" href="ConfirmOrder/confirmOrder.css">
    </head>
    <body>
        <c:import url="Layout/MainLayout.jsp">
            <c:param name="Body" value="${Body}"/>
        </c:import>
        <script>
            setTimeout(() => {
                window.location.href = "MainController?action=Search Product&search=";
            }, 10000);
        </script>
    </body>
</html>
