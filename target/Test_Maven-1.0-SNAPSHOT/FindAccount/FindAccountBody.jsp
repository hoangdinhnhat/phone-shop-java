<%-- 
    Document   : BodyLogin
    Created on : Mar 12, 2023, 11:08:07 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div id="forget-password">
            <form action="MainController" class="wrapper" method="POST">
                <div class="title">
                    Find Your Account
                </div>
                <div class="body-form">
                    <p class="des">Please enter your email address to search for your account.</p>
                    <input type="text" name="email-forget-pass" placeholder="Email address">
                    <h3>${requestScope.ERROR}</h3>
                </div>
                <div class="submit-btn">
                    <input type="submit" name="action" value="FindAccount">
                </div>
            </form>
        </div>
    </body>
</html>
