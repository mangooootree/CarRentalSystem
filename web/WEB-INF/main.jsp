<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="common/header.jsp"%>

<table align="center" border="1" cellpadding="20">

    <c:forEach items="${cars}" var="car">

        <c:choose>
            <c:when test="${car.automaticTransmission}">
                <c:set var="gearbox" value="Автомат"/>
            </c:when>
            <c:otherwise>
                <c:set var="gearbox" value="Ручная"/>
            </c:otherwise>
        </c:choose>
        <tr>
            <td>
                <div align="top">
                        Модель: ${car.model} <br>
                        Цвет: ${car.color} <br>
                        Гос. номер: ${car.licencePlate} <br>
                        КПП: ${gearbox} <br><br>
                    Статус:
                    <c:choose>
                        <c:when test="${car.ordered == 'true'}">
                            Занят
                        </c:when>
                            <c:when test="${car.ordered == 'false'}">
                                Свободен
                            </c:when>
                    </c:choose>
                    <br>
                    Цена за сутки: <b>${car.price}</b>
                </div>
                <br>
                <c:choose>
                    <c:when test="${car.ordered == 'true'}">

                    </c:when>
                    <c:when test="${car.ordered == 'false'}">
                        <a href="/order/new.html?id=${car.id}">Заказать сейчас!</a>
                    </c:when>
                </c:choose>
            </td>
            <td>
                <img src="../img/${car.id}.jpg" width="300" align="middle" border="1" style="border-color: #eef6fd ">
            </td>

        </tr>


    </c:forEach>
</table>
<%@include file="common/footer.jsp"%>