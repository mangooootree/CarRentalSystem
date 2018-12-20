<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="common/header.jsp"%>

<table align="center" border="1" cellpadding="20">

    <c:forEach items="${cars}" var="car">

        <tr>
            <td>
                <div align="top">
                    <fmt:message key="car.model"/>: ${car.model} <br>
                    <fmt:message key="car.color"/>: ${car.color} <br>
                    <fmt:message key="car.licencePlate"/>: ${car.licencePlate} <br>
                    <fmt:message key="car.gearbox"/>:

                    <c:choose>
                        <c:when test="${car.automaticTransmission == 'true'}">
                            <fmt:message key="car.gearbox.auto"/>
                        </c:when>
                        <c:when test="${car.automaticTransmission == 'false'}">
                            <fmt:message key="car.gearbox.manual"/>
                        </c:when>
                    </c:choose>

                    <br><br>
                    <fmt:message key="car.status"/>:
                    <c:choose>
                        <c:when test="${car.ordered == 'true'}">
                            <fmt:message key="car.status.ordered"/>
                        </c:when>
                            <c:when test="${car.ordered == 'false'}">
                                <fmt:message key="car.status.free"/>
                            </c:when>
                    </c:choose>
                    <br>
                    <fmt:message key="car.price"/>: <b>${car.price}</b>
                </div>
                <br>
                <c:choose>
                    <c:when test="${car.ordered == 'true'}">

                    </c:when>
                    <c:when test="${car.ordered == 'false'}">
                        <a href="/order/new.html?id=${car.id}"><fmt:message key="main.orderNow"/></a>
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