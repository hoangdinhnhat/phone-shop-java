<%-- 
    Document   : AccountSearchBar
    Created on : Mar 13, 2023, 2:43:27 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div class="search">
            <form action="MainController" method="GET">
                <input type="text" hidden name="curPage" value="${param.curPage}">
                <input hidden type="text" value="Search Account" name="action"/>
                <input placeholder="Searching..." type="text" name="search" value="${param.search}" oninput="event.target.parentElement.submit()" autofocus>
                <input type="submit" value="Search Account" name="action"/>
            </form>
        </div>
    </body>
</html>
