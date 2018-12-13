<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="common/header.jsp"%>
<table align="center" border="1" cellpadding="20">


        <c:choose>
            <c:when test="{car.automaticTransmission}">
                <c:set var="gearbox" value="Automatic"/>
            </c:when>
            <c:otherwise>
                <c:set var="gearbox" value="Manual"/>
            </c:otherwise>
        </c:choose>
        <tr>
            <td valign="top" align="center">
                <h2>Новый заказ</h2>
                <form action="/order" method="post">
                    <div align="left">
                    <p>Имя<br>
                    <input type="text" value="${user.firstname}"></p>
                        <p>Фамилия<br>
                        <input type="text" value="${user.lastname}"></p>
                        <p>Паспорт<br>
                    <input  type="text" value="${user.passport}"></p>
                        <p>Количество дней<br>
                    <input id="days" type="text" name="days_amount" value="${current_days_amount}" onchange="myFunction()"></p>
                        <input type="hidden" name="car_id" value="${car.id}">
                        <input type="hidden" name="user_id" value="${user.id}">
                        <label id ="sum"></label>
                        <script>
                            function myFunction() {
                                var x = document.getElementById("sum");
                                var y = document.getElementById("days").value;
                                x.innerText = 'Стоимость заказа: ' + (y * ${car.price})/10 + ' руб.';
                            }
                        </script>

                    </div>
                    <p><button ${disabled} type="submit">Оформить заказ</button></p>
                </form>
            </td>
            <td>
                <div valign="top" align="center">${car.model} ${car.color} ${gearbox} </div>
                    <img src="../img/${car.id}.jpg" height="200" width="300" align="bottom">
            </td>
        </tr>
</table>
<div align="center" style="background: azure">
    <p>${total_price}</p>
    <p>${message}</p>
    <p>Перейти в <a href="/orders">Мои ордера</a> </p>
</div>
<%@include file="common/footer.jsp"%>