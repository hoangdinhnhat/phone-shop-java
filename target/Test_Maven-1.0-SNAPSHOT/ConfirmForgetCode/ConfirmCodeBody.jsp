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
                    <p class="des">We have sent a 6-digit confirmation code to your email. Please check your email and fill in the box below!</p>
                    <input style="text-align: center;" type="number" name="forget-confirm-code" placeholder="Confirm Code" min="100000" max="999999">
                    <h3>${requestScope.ERROR}</h3>
                </div>
                <div class="submit-btn">
                    <input type="submit" name="action" value="CheckForgetCode">
                </div>
            </form>
        </div>
    </body>
</html>
