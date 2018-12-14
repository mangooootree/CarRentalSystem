<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="common/header.jsp"%>

<table align="center" border="1" cellpadding="20">

    <c:forEach items="${cars}" var="car">

        <c:choose>
            <c:when test="${car.automaticTransmission}">
                <c:set var="gearbox" value="Automatic"/>
            </c:when>
            <c:otherwise>
                <c:set var="gearbox" value="Manual"/>
            </c:otherwise>
        </c:choose>
        <tr>
            <td>
                <div align="top">
                        Модель: ${car.model} <br>
                        Цвет: ${car.color} <br>
                        Гос. номер: ${car.licencePlate} <br>
                        КПП: ${gearbox} <br><br>
                    Цена за сутки: <b>${car.price}</b>
                </div>
                <br>
                <a href="/order/new.html?id=${car.id}">ORDER NOW!</a>
            </td>
            <td>
                <img src="../img/${car.id}.jpg" height="200" width="300" align="middle" border="1" style="border-color: #eef6fd ">
            </td>

        </tr>


    </c:forEach>
</table>
<%@include file="common/footer.jsp"%>