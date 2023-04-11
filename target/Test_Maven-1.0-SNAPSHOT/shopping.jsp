<%-- 
    Document   : index
    Created on : Mar 11, 2023, 5:12:28 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import var="Body" url="Shopping/BodyShopping.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
        <link rel="stylesheet" href="General.css">
        <link rel="stylesheet" href="Shopping/shopping.css">
        <link href="https://cdn.jsdelivr.net/gh/hung1001/font-awesome-pro-v6@44659d9/css/all.min.css" rel="stylesheet" type="text/css" />
        <script src="https://accounts.google.com/gsi/client" async defer></script>
    </head>
    <body>
        <c:import url="Layout/MainLayout.jsp">
            <c:param name="Body" value="${Body}"/>
        </c:import>
        <c:if test="${sessionScope.LOGIN_USER == null}">
            <div id="g_id_onload"
                 data-client_id="96615940146-a6npdnvt227aiaou542u02q3q38v788t.apps.googleusercontent.com"
                 data-context="signin"
                 data-login_uri="PRJ301_Assignment/MainController?action=LoginWithGoogle"
                 data-nonce=""
                 data-auto_select="true"
                 data-itp_support="true">
            </div>
        </c:if>
        <c:if test="${requestScope.MESSAGE != null}">
            <script defer>alert('${requestScope.MESSAGE}')</script>
        </c:if>
        <script src="Shopping/shopping.js"></script>
    </body>
</html>
