<%-- 
    Document   : BodyLogin
    Created on : Mar 12, 2023, 11:08:07 PM
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
            <h1>Login</h1>
            <div class="form-group">
                <input name="userID" required="required" class="form-control"/>
                <label class="form-label">Username    </label>
            </div>
            <div class="form-group">
                <input name="password" id="password" type="password" required="required" class="form-control"/>
                <label class="form-label">Password</label>
            </div>
            <div class="form-group" style="display: flex; align-items: center; justify-content: center;">
                <div hidden id="g_id_onload"
                     data-client_id="96615940146-a6npdnvt227aiaou542u02q3q38v788t.apps.googleusercontent.com"
                     data-context="signin"
                     data-ux_mode="popup"
                     data-login_uri="PRJ301_Assignment/MainController?action=LoginWithGoogle"
                     data-auto_prompt="false">
                </div>

                <div class="g_id_signin"
                     data-type="standard"
                     data-shape="pill"
                     data-theme="filled_blue"
                     data-text="signin_with"
                     data-size="large"
                     data-logo_alignment="left">
                </div>
            </div>
            <div class="form-group">
                <input class="btn" type="submit" name="action" value="Login">
                <p class="error-msg">${requestScope.ERROR}</p>
            </div>
            <div class="form-group">
                <a href="signup.jsp">Sign Up</a>
                <a href="FindAccount.jsp">Forget Password</a>
            </div>
        </form>
    </body>
</html>
