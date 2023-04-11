<%-- 
    Document   : BodyLogin
    Created on : Mar 12, 2023, 11:08:07 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div id="forget-password">
            <form action="MainController" class="wrapper" method="POST">
                <div class="title">
                    Repair Your Account
                </div>
                <div class="body-form">
                    <p class="des">We have confirmed you as the account holder. Please enter your password in the box below to reset a new password!</p>
                    <input type="text" name="password" placeholder="New password">
                    <input hidden type="text" name="userID" value="${requestScope.PENDING_USER.userID}">
                    <h3>${requestScope.ERROR}</h3>
                </div>
                <div class="submit-btn">
                    <input id="changePassword" hidden type="submit" name="action" value="ChangePassword">
                    <label for="changePassword">Change Password</label>
                </div>
            </form>
        </div>
    </body>
</html>
