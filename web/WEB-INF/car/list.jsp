<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../common/header.jsp" %>
<div align="center">

<table border="1" cellpadding="10">
    <td style="border: 0px">
        <a href="/car/new.html"><fmt:message key="car.new"/></a>
    </td>
    <tr>
        <th>
            <fmt:message key="car.model"/>
        </th>
        <th>
            <fmt:message key="car.color"/>
        </th>
        <th>
            <fmt:message key="car.licencePlate"/>
        </th>
        <th>
            <fmt:message key="car.gearbox"/>
        </th>
        <th>
            <fmt:message key="car.status.ordered"/>/<fmt:message key="car.status.free"/>
        </th>
        <th>
            <fmt:message key="car.price"/>
        </th>
        <th>

        </th>
    </tr>
    <c:forEach items="${cars}" var="car">
        <tr>
            <td>
                    ${car.model}
            </td>
            <td>
                    ${car.color}
            </td>
            <td>
                    ${car.licencePlate}
            </td>
            <td>
                <c:choose>
                    <c:when test="${car.automaticTransmission == 'true'}">
                        <fmt:message key="car.gearbox.auto"/>
                    </c:when>
                    <c:when test="${car.automaticTransmission == 'false'}">
                        <fmt:message key="car.gearbox.manual"/>
                    </c:when>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${car.ordered == 'true'}">
                        <fmt:message key="car.status.ordered"/>
                    </c:when>
                    <c:when test="${car.ordered == 'false'}">
                        <fmt:message key="car.status.free"/>
                    </c:when>
                </c:choose>
            </td>
            <td>
                    ${car.price}
            </td>
            <td>
                <a href="/car/delete.html?id=${car.id}"><fmt:message key="car.delete"/></a><br>
                <a href="/car/edit.html?id=${car.id}"><fmt:message key="car.edit"/></a><br>
            </td>
        </tr>
    </c:forEach>
</table>
</div>
<%@include file="../common/footer.jsp" %>