<%-- 
    Document   : Body
    Created on : Mar 11, 2023, 5:35:33 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import var="Slider" url="Shopping/Slider.jsp"/>
<!DOCTYPE html>
<html>
    <body>
        ${Slider}
        <div class="show-phone-list">
            <div class="phone-brand">
                <h1>Điện thoại Apple (iPhone)</h1>
                <p>(${fn:length(requestScope.LIST_PHONE)} products)</p>
            </div>
            <div class="phone-type">
                <h1 class="title">DÒNG SẢN PHẨM</h1>
                <div class="type-products">
                    <c:if test="${requestScope.LIST_SERIES != null}">
                        <c:if test="${not empty requestScope.LIST_SERIES}">
                            <c:forEach var="series" items="${requestScope.LIST_SERIES}">
                                <a href="MainController?search=${series.seriesName}&action=Search Product" class="phone-item">
                                    <img src="${series.imageURL}">
                                    <p class="name">${series.seriesName} Series</p>
                                    <p class="des">Chỉ từ 
                                        <fmt:setLocale value = "vi_VN"/>
                                        <fmt:formatNumber value = "${series.minPrice}" type = "currency"/>
                                    </p>
                                </a>
                            </c:forEach>
                        </c:if>
                    </c:if>
                </div>
            </div>
            <div class="list-phone">
                <c:if test="${requestScope.LIST_PHONE != null}">
                    <c:if test="${not empty requestScope.LIST_PHONE}">
                        <c:forEach var="phone" items="${requestScope.LIST_PHONE}">
                            <div href="#" class="phone-item">
                                <a href="#"><img src="${phone.imageURL}"></a>
                                <a href="#"><p class="name">${phone.phoneName}</p></a>
                                <p class="price">
                                    <fmt:setLocale value = "vi_VN"/>
                                    <fmt:formatNumber value = "${phone.price}" type = "currency"/>
                                </p>
                                <div class="thongso">
                                    <p>${phone.phoneCPU}</p>
                                    <p>${phone.screenSize}</p>
                                </div>
                                <form class="Add-Btn">
                                    <input type="number" name="quantity" min="1" value="1">
                                    <input type="text" hidden name="phone-id" value="${phone.phoneID}">
                                    <input type="text" hidden name="search" value="${param.search}">
                                    <input type="submit" name="action" value="Add">
                                </form>
                            </div>
                        </c:forEach>
                    </c:if>
                </c:if>
            </div>
        </div>
    </body>
</html>
