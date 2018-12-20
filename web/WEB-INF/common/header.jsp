<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <c:url var="urlCss" value="/main.css"/>
    <link href="${urlCss}" rel="stylesheet">
    <meta charset="utf-8">
    <title>Car Rental System</title>
    <div style="background-color: #e1e9f0; padding: 10px 20px">
        <div style="horiz-align: right; vertical-align: bottom; display: inline; margin: 10px 10px">
            <h1 style="font-size: x-large; font-family: sans-serif; vertical-align: bottom; display: inline; margin-right: 30px">
                Car Rental Service</h1>
            <c:choose>
                <c:when test="${currentUser.role == 'GUEST'}">
                    <a style="font-family: sans-serif; font-size: larger; text-decoration: none; margin-left: 15px"
                       href="/main.html"><fmt:message key="header.main"/></a>
                    <a style="font-family: sans-serif; font-size: larger; text-decoration: none; margin-left: 15px"
                       href="/login.html"><fmt:message key="header.login"/></a>
                    <a style="font-family: sans-serif; font-size: larger; text-decoration: none; margin-left: 15px"
                       href="/registration.html"><fmt:message key="header.registration"/></a>
                </c:when>

                <c:when test="${currentUser.role == 'ADMIN'}">
                    <a style="font-family: sans-serif; font-size: larger; text-decoration: none; margin-left: 15px"
                       href="/main.html"><fmt:message key="header.main"/></a>
                    <a style="font-family: sans-serif; font-size: larger; text-decoration: none; margin-left: 15px"
                       href="/car/list.html"><fmt:message key="header.cars"/></a>
                    <a style="font-family: sans-serif; font-size: larger; text-decoration: none; margin-left: 15px"
                       href="/order/list.html"><fmt:message key="header.orders"/></a>
                    (${newOrdersAmount})
                    <a style="font-family: sans-serif; font-size: larger; text-decoration: none; margin-left: 15px"
                       href="/user/list.html"><fmt:message key="header.users"/></a>
                    <a style="font-family: sans-serif; font-size: larger; text-decoration: none; margin-left: 15px"
                       href="/bill/list.html"><fmt:message key="header.bills"/></a>


                    <a style="float: right; font-size: larger; font-family: sans-serif; text-decoration: none; margin-right: 5px"
                       href="/logout.html"><fmt:message key="header.logout"/></a>
                    <div style="float: right; font-size: larger; display: inline; font-family: sans-serif; margin-right: 20px">
                            ${currentUser.firstname} ${currentUser.lastname}</div>
                    <div style="float: right; font-size: larger; display: inline; font-family: sans-serif; margin-right: 20px">
                        <fmt:message key="${currentUser.role.name}"/></div>
                </c:when>

                <c:when test="${currentUser.role == 'CLIENT'}">
                    <a style="font-family: sans-serif; font-size: larger; text-decoration: none; margin-left: 10px"
                       href="/main.html"><fmt:message key="header.main"/></a>
                    <a style="font-family: sans-serif; font-size: larger; text-decoration: none; margin-left: 15px"
                       href="/user/edit.html?id=${currentUser.id}"><fmt:message key="header.editProfile"/></a>
                    <a style="font-family: sans-serif; font-size: larger; text-decoration: none; margin-left: 15px"
                       href="/order/list.html"><fmt:message key="header.myOrders"/></a>

                    <a style="float: right; font-size: larger; font-family: sans-serif; text-decoration: none; margin-right: 5px"
                       href="/logout.html"><fmt:message key="header.logout"/></a>
                    <div style="float: right; font-size: larger; display: inline; font-family: sans-serif; margin-right: 20px">
                            ${currentUser.firstname} ${currentUser.lastname}</div>
                    <div style="float: right; font-size: larger; display: inline; font-family: sans-serif; margin-right: 20px">
                        <fmt:message key="${currentUser.role.name}"/></div>
                </c:when>
            </c:choose>


        </div>
    </div>
    <br><br>
</head>
<body>