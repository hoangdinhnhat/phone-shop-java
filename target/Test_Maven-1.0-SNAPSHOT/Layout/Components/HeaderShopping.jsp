<%-- 
    Document   : Header
    Created on : Mar 11, 2023, 5:11:49 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import var="SearchBar" url="Layout/Components/ProductSearchBar.jsp"/>
<c:import var="Actions" url="Layout/Components/Actions.jsp"/>
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
