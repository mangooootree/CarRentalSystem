<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../common/header.jsp" %>

<table align="center" border="1" cellpadding="10">
    <tr>
        <th>
            <fmt:message key="order.client"/>
        </th>
        <th>
            <fmt:message key="order.car"/>
        </th>
        <th>
            <fmt:message key="order.date"/>
        </th>
        <th>
            <fmt:message key="order.days"/>
        </th>
        <th>
            <fmt:message key="order.price"/>
        </th>
        <th>
            <fmt:message key="order.paid"/>
        </th>
        <th>
            <fmt:message key="order.accepted"/>
        </th>
        <th>
            <fmt:message key="order.comment"/>
        </th>

        <c:if test="${currentUser.role == 'ADMIN'}">
            <th>
                <fmt:message key="order.handling"/>
            </th>
        </c:if>

    </tr>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>
                    ${order.user.firstname} ${order.user.lastname} <br> ${order.user.passport}
            </td>
            <td>
                    ${order.car.model} ${order.car.licencePlate}
                <br> ${order.car.color}

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
                <div style="font-size: small">
                    <fmt:message key="order.date"/>:<br>
                    <fmt:formatDate pattern="dd.MM.yyyy" value="${order.date}"/> <br><br>
                    <fmt:message key="order.expired"/>:<br>
                    <fmt:formatDate pattern="dd.MM.yyyy" value="${order.expireDate}"/>
                </div>
            </td>
            <td>
                    ${order.amountOfDays}
            </td>
            <td>
                    ${order.totalCost}
            </td>
            <td>
                        <c:choose>
                            <c:when test="${order.paid == 'true'}">
                                <fmt:message key="order.paid"/>
                            </c:when>
                            <c:when test="${order.paid == 'false'}">
                                <fmt:message key="order.unpaid"/>
                            </c:when>
                        </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${order.reviewed == 'true'}">

                        <c:choose>
                            <c:when test="${order.accepted == 'true'}">
                                <fmt:message key="order.accepted"/>
                            </c:when>
                            <c:when test="${order.accepted == 'false'}">
                                <fmt:message key="order.rejected"/>
                            </c:when>
                        </c:choose>

                    </c:when>
                    <c:otherwise>
                        <fmt:message key="order.underConsideration"/>
                    </c:otherwise>
                </c:choose>
            </td>
            <td width="200px">
                <div style="padding-left: 10px; width: inherit; word-break: break-all">${order.comments} <br><br></div>
                    <c:choose>
                        <c:when test="${currentUser.role == 'ADMIN'}">
                            <form action="/order/setComment.html?id=${order.id}" method="post" style="width: 200px">
                                <input type="text" value="" name="comment" placeholder="<fmt:message key="order.putComment"/>">
                                <input type="hidden" name="id" value="${order.id}">
                                <button type="submit"><fmt:message key="order.addComment"/></button>
                            </form>
                        </c:when>
                    </c:choose>
            </td>

            <c:if test="${currentUser.role == 'ADMIN'}">
                <td>
                    <a href="/order/accept.html?id=${order.id}"><fmt:message key="order.accept"/></a><br>
                    <a href="/order/reject.html?id=${order.id}"><fmt:message key="order.reject"/></a><br>
                    <a href="/order/setPaid.html?id=${order.id}"><fmt:message key="order.paid"/></a><br>
                    <a href="/order/setUnPaid.html?id=${order.id}"><fmt:message key="order.unpaid"/></a><br>
                    <a href="/order/close.html?id=${order.id}"><fmt:message key="order.close"/></a><br>
                    <a href="/bill/new.html?id=${order.id}"><fmt:message key="order.newBill"/></a><br>
                    <a href="/order/delete.html?id=${order.id}"><fmt:message key="order.delete"/></a><br>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>

<%@include file="../common/footer.jsp" %>