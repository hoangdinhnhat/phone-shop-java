<%-- 
    Document   : HeaderAdmin
    Created on : Mar 13, 2023, 2:42:30 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import var="SearchBar" url="Layout/Components/AccountSearchBar.jsp"/>
<c:import var="Actions" url="Layout/Components/AdminActions.jsp"/>
<c:import var="Logo" url="Layout/Components/Logo.jsp"/>
<!DOCTYPE html>
<html>
    <body>
        <div id="header">
            ${Logo}
            ${SearchBar}
            ${Actions}
        </div>
    </body>
</html>
