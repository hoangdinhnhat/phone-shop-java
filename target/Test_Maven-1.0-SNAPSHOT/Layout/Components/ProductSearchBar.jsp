<%-- 
    Document   : SearchBar
    Created on : Mar 12, 2023, 5:21:05 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div class="search">
            <form action="MainController" method="POST">
                <input placeholder="Searching..." type="text" name="search" value="${param.search}">
                <input type="submit" value="Search Product" name="action"/>
            </form>
        </div>
    </body>
</html>
