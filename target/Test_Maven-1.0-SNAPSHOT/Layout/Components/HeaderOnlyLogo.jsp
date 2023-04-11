<%-- 
    Document   : HeaderOnlyLogo
    Created on : Mar 12, 2023, 11:18:02 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import var="Logo" url="Layout/Components/Logo.jsp"/>
<!DOCTYPE html>
<html>
    <body>
        <div id="header">
            ${Logo}
        </div>
    </body>
</html>
