<%-- 
    Document   : BodyProfile
    Created on : Mar 12, 2023, 10:56:00 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <div id="profile">
            <div class="img-status">
                <div class="user-image">
                    <img src="${sessionScope.LOGIN_USER.imageURL}" alt="">
                </div>
                <div class="user-status">
                    <form action="ChangeAvatarController" method="POST" target="_blank" enctype="multipart/form-data">
                        <input type="text" name="action" value="ChangeAvatar" hidden>
                        <label for="uploadFile" class="uploadBtn">UP AVATAR</label>
                        <input type="file" name="imgFile" id="uploadFile" hidden onchange="event.target.parentElement.submit()">
                    </form>
                </div>
                <div class="user-status">
                    "Thà yêu nhầm còn hơn bỏ sót"
                </div>
            </div>
            <div class="info">
                <div class="user-attribute">
                    <div class="attr-name">
                        UserID
                    </div>
                    <div class="attr-value">
                        ${sessionScope.LOGIN_USER.userID}
                    </div>
                </div>
                <div class="user-attribute">
                    <div class="attr-name">
                        FullName
                    </div>
                    <div class="attr-value">
                        <form action="MainController" class="fullname-profile">
                            <input type="text" name="fullName" value="${sessionScope.LOGIN_USER.fullName}">
                            <input hidden type="text" name="roleID" value="${sessionScope.LOGIN_USER.roleID}">
                            <input hidden type="text" name="userID" value="${sessionScope.LOGIN_USER.userID}">
                            <input hidden type="submit" name="action" value="Update">
                        </form>
                    </div>
                </div>
                <div class="user-attribute">
                    <div class="attr-name">
                        Email
                    </div>
                    <div class="attr-value">
                        ${sessionScope.LOGIN_USER.email}
                    </div>
                </div>
                <div class="user-attribute">
                    <div class="attr-name">
                        Password
                    </div>
                    <div class="attr-value">
                        *****
                    </div>
                </div>
                <div class="user-attribute">
                    <div class="attr-name">
                        RoleID
                    </div>
                    <div class="attr-value">
                        ${sessionScope.LOGIN_USER.roleID}
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
