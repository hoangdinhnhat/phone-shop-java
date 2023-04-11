<%-- 
    Document   : profile
    Created on : Mar 12, 2023, 10:46:07 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import var="Body" url="UserProfile/BodyProfile.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Page</title>
        <link rel="stylesheet" href="General.css">
        <link rel="stylesheet" href="UserProfile/userProfile.css">
    </head>
    <body>
        <c:import url="Layout/MainLayout.jsp">
            <c:param name="Body" value="${Body}"/>
        </c:import>
    </body>
</html>
