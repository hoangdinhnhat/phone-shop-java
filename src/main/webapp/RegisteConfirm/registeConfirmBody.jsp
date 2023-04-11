<%-- 
    Document   : BodySignup
    Created on : Mar 12, 2023, 11:43:41 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div class="panda">
            <div class="ear"></div>
            <div class="face">
                <div class="eye-shade"></div>
                <div class="eye-white">
                    <div class="eye-ball"></div>
                </div>
                <div class="eye-shade rgt"></div>
                <div class="eye-white rgt">
                    <div class="eye-ball"></div>
                </div>
                <div class="nose"></div>
                <div class="mouth"></div>
            </div>
        </div>
        <form action="MainController" method="POST">
            <div class="hand"></div>
            <div class="hand rgt"></div>
            <h1>Register Confirm</h1>
            <div class="form-group">
                <input name="registeConfirmCode" id="text" type="text" required="required" class="form-control"/>
                <label class="form-label">Register Confirm Code</label>
                <p class="error-msg">${requestScope.REGISTE_CODE_ERROR}</p>
            </div>
            <div class="form-group">
                <input class="btn" type="submit" name="action" value="Registe Confirm">
            </div>
        </form>
    </body>
</html>
