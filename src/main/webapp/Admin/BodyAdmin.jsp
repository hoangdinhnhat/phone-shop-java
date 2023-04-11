<%-- 
    Document   : BodyProfile
    Created on : Mar 12, 2023, 10:56:00 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div id="account-management">
            <h1>List Account</h1>
            <div class="accounts">
                <c:if test="${requestScope.LIST_USER != null}">
                    <c:if test="${not empty requestScope.LIST_USER}">
                        <c:forEach var="user" varStatus="counter" items="${requestScope.LIST_USER}">
                            <form class="account" action="MainController" method="POST">
                                <div class="account-no">${counter.count}</div>
                                <div class="account-img">
                                    <img
                                        src="${user.imageURL}"
                                        alt=""
                                        />
                                </div>
                                <div class="account-userID">${user.userID}</div>
                                <div class="account-fullName">
                                    <input type="text" name="fullName" value="${user.fullName}" />
                                </div>
                                <div class="account-email">${user.email}</div>
                                <div class="account-password">${user.password}</div>
                                <div class="account-role">
                                    <input type="text" name="roleID" value="${user.roleID}" />
                                </div>
                                <div class="account-update account-btn">
                                    <input type="hidden" name="search" value="${param.search}" />
                                    <input type="hidden" name="curPage" value="${param.curPage}" />
                                    <input type="hidden" name="userID" value="${user.userID}" />
                                    <input type="submit" name="action" value="Update" />
                                </div>
                                <div class="account-delete account-btn">
                                    <a href="MainController?action=Delete&search=${param.search}&curPage=${param.curPage}&userID=${user.userID}">Delete</a>
                                </div>
                            </form>
                        </c:forEach>
                    </c:if>
                </c:if>
            </div>
            <div class="page-sep">
                <a href="MainController?action=Search Account&search=&curPage=first" class="page-link">First</a>
                <a href="MainController?action=Search Account&search=&curPage=previous" class="page-link">Prev</a>
                <a href="MainController?action=Search Account&search=&curPage=${requestScope.curPage}" class="page-link">${requestScope.curPage}</a>
                <a href="MainController?action=Search Account&search=&curPage=next" class="page-link">Next</a>
            </div>
        </div>
    </body>
</html>
