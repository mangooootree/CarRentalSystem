<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:url var="urlCss" value="/main.css"/>
    <link href="${urlCss}" rel="stylesheet">
    <meta charset="utf-8">
    <title>Car Rental System</title>
    <div style="background-color: #e1e9f0; padding: 10px 20px">
${sessionScope.currentUser.role}
        <div style="horiz-align: right; vertical-align: bottom; display: inline; margin: 10px 10px">
            <h1 style="font-size: x-large; font-family: sans-serif; vertical-align: bottom; display: inline; margin-right: 30px">
                Car Rental Service</h1>
            <c:choose>
                <c:when test="${currentUser.role == 'GUEST'}">
                    <a style="font-family: sans-serif; font-size: larger; text-decoration: none; margin-left: 15px"
                       href="/main.html">Main</a>
                    <a style="font-family: sans-serif; font-size: larger; text-decoration: none; margin-left: 15px"
                       href="/login.html">Login</a>
                    <a style="font-family: sans-serif; font-size: larger; text-decoration: none; margin-left: 15px"
                       href="/registration.html">Register</a>
                </c:when>

                <c:when test="${currentUser.role == 'ADMIN'}">
                    <a style="font-family: sans-serif; font-size: larger; text-decoration: none; margin-left: 15px"
                       href="/main.html">Main</a>
                    <a style="font-family: sans-serif; font-size: larger; text-decoration: none; margin-left: 15px"
                       href="/car/list.html">Cars</a>
                    <a style="font-family: sans-serif; font-size: larger; text-decoration: none; margin-left: 15px"
                       href="/order/list.html">Orders</a>
                    (${newOrdersAmount})
                    <a style="font-family: sans-serif; font-size: larger; text-decoration: none; margin-left: 15px"
                       href="/user/list.html">Users</a>
                    <a style="font-family: sans-serif; font-size: larger; text-decoration: none; margin-left: 15px"
                       href="/bills">Bills</a>


                    <a style="float: right; font-size: larger; font-family: sans-serif; text-decoration: none; margin-right: 5px"
                       href="/logout.html">Log out</a>
                    <div style="float: right; font-size: larger; display: inline; font-family: sans-serif; margin-right: 20px">
                            ${currentUser.login}</div>
                    <div style="float: right; font-size: larger; display: inline; font-family: sans-serif; margin-right: 20px">
                            ${currentUser.role.name}</div>
                </c:when>

                <c:when test="${currentUser.role == 'CLIENT'}">
                    <a style="font-family: sans-serif; font-size: larger; text-decoration: none; margin-left: 10px"
                       href="/main.html">Main</a>
                    <a style="font-family: sans-serif; font-size: larger; text-decoration: none; margin-left: 15px"
                       href="/useredit">Edit profile</a>
                    <a style="font-family: sans-serif; font-size: larger; text-decoration: none; margin-left: 15px"
                       href="/orders">My orders</a>

                    <a style="float: right; font-size: larger; font-family: sans-serif; text-decoration: none; margin-right: 5px"
                       href="/logout.html">Log out</a>
                    <div style="float: right; font-size: larger; display: inline; font-family: sans-serif; margin-right: 20px">
                            ${currentUser.login}</div>
                    <div style="float: right; font-size: larger; display: inline; font-family: sans-serif; margin-right: 20px">
                            ${currentUser.role.name}</div>
                </c:when>
            </c:choose>


        </div>
    </div>
    <br><br>
</head>
<body>