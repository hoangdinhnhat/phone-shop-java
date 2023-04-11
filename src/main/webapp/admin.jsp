<%-- 
    Document   : admin
    Created on : Mar 13, 2023, 5:31:04 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import var="Body" url="Admin/BodyAdmin.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="General.css">
        <link rel="stylesheet" href="Admin/admin.css">
    </head>
    <body>
        <c:import url="Layout/AdminLayout.jsp">
            <c:param name="Body" value="${Body}"/>
        </c:import>
    </body>
</html>
