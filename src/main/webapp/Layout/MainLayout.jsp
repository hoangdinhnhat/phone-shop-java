<%-- 
    Document   : MainLayout
    Created on : Mar 13, 2023, 2:27:14 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import var="Header" url="Layout/Components/HeaderShopping.jsp"/>
<c:import var="Footer" url="Layout/Components/Footer.jsp"/>
<!DOCTYPE html>
<html>
    <body>
        <div class="root">
            ${Header}
            <div id="body">
                ${param.Body}
            </div>
            ${Footer}
        </div>
    </body>
</html>
