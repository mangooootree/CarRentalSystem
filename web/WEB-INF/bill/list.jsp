<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../common/header.jsp" %>
<div align="center">

    <table border="1" cellpadding="10">
        <td style="border: 0px">
        </td>
        <tr>
            <th>
                <fmt:message key="bill.client"/>
            </th>
            <th>
                <fmt:message key="bill.car"/>
            </th>
            <th>
                <fmt:message key="bill.orderDate"/>
            </th>
            <th>
                <fmt:message key="bill.price"/>
            </th>
            <th>
                <fmt:message key="bill.damage"/>
            </th>
            <th>
                <fmt:message key="bill.paid"/>
            </th>
            <c:if test="${currentUser.role == 'ADMIN'}">
                <th>
                    <fmt:message key="bill.handling"/>
                </th>
            </c:if>
        </tr>
        <c:forEach items="${bills}" var="bill">
            <tr>
                <td>
                    <fmt:message key="user.firstname"/>: ${bill.order.user.firstname}<br>
                    <fmt:message key="user.lastname"/>: ${bill.order.user.lastname}<br>
                    <fmt:message key="user.passport"/>: ${bill.order.user.passport}<br>
                </td>
                <td>
                    <fmt:message key="car.model"/>: ${bill.order.car.model}<br>
                    <fmt:message key="car.color"/>: ${bill.order.car.color}<br>
                    <fmt:message key="car.licencePlate"/>: ${bill.order.car.licencePlate}<br>
                </td>
                <td>
                    <fmt:formatDate pattern="dd.MM.yyyy" value="${bill.order.date}"/>
                </td>
                <td>
                        ${bill.price}
                </td>
                <td>
                        ${bill.damageDescription}
                </td>
                <td>
                            <c:choose>
                                <c:when test="${bill.paid == 'true'}">
                                    <fmt:message key="bill.paid"/>
                                </c:when>
                                <c:when test="${bill.paid == 'false'}">
                                    <fmt:message key="bill.unpaid"/>
                                </c:when>
                            </c:choose>
                </td>

                <c:if test="${currentUser.role == 'ADMIN'}">
                    <td>
                        <a href="/bill/delete.html?id=${bill.id}"><fmt:message key="bill.delete"/></a><br>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="../common/footer.jsp" %>