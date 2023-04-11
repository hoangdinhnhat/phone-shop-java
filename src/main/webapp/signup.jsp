<%-- 
    Document   : signup
    Created on : Mar 12, 2023, 11:42:20 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import var="Body" url="Signup/BodySignup.jsp"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SignUp Page</title>
        <link rel="stylesheet" href="General.css">
        <link rel="stylesheet" href="Signup/signup.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script type="text/javascript">
            var widgetID;
            var onloadCallback = function () {
                widgetID = grecaptcha.render('html_element', {
                    'sitekey': '6LdvLckjAAAAAE_U_-OvPLPPjEPrcsWcjr317L0x'
                });
            };
        </script>
    </head>
    <body>
        <c:import url="Layout/OnlyLogoLayout.jsp">
            <c:param name="Body" value="${Body}"/>
        </c:import>
        <script src="Signup/signup.js"></script>
        <script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit"
                async defer>
        </script>
    </body>
</html>
