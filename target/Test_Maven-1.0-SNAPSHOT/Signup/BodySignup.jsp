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
            <h1>Sign Up</h1>
            <div class="form-group">
                <input name="userID" id="text" type="text" required="required" class="form-control"/>
                <label class="form-label">Username</label>
                <p class="error-msg">${requestScope.USER_ERROR.userIDError}</p>
            </div>
            <div class="form-group">
                <input name="fullName" id="text" type="text" required="required" class="form-control"/>
                <label class="form-label">Fullname</label>
                <p class="error-msg">${requestScope.USER_ERROR.fullNameError}</p>
            </div>
            <div class="form-group">
                <input style="text-align: center; font-weight: bold;" name="roleID" value="US" type="text" required="required" class="form-control" readonly/>
                <label class="form-label">RoleID</label>
            </div>
            <div class="form-group">
                <input name="password" id="password" type="password" required="required" class="form-control"/>
                <label class="form-label">Password</label>
                <p class="error-msg">${requestScope.USER_ERROR.passwordError}</p>
            </div>
            <div class="form-group">
                <input name="confirm" id="password" type="password" required="required" class="form-control"/>
                <label class="form-label">Confirm Password</label>
                <p class="error-msg">${requestScope.USER_ERROR.confirmError}</p>
            </div>
            <div class="form-group">
                <input name="email" type="email" required="required" class="form-control"/>
                <label class="form-label">Email    </label>
                <p class="error-msg">${requestScope.USER_ERROR.emailError}</p>
            </div>
            <div class="form-group">
                <div id="html_element"></div>
                <p class="error-msg">${requestScope.USER_ERROR.captchaError}</p>
            </div>
            <div class="form-group">
                <input class="btn" type="submit" name="action" value="Create">
                <p class="error-msg">${requestScope.ERROR}</p>
            </div>
        </form>
    </body>
</html>
