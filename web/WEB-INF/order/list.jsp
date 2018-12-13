<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../common/header.jsp" %>

<table align="center" border="1" cellpadding="10">
    <tr>
        <th>
            Заказчик
        </th>
        <th>
            Автомобиль
        </th>
        <th>
            Дата
        </th>
        <th>
            Дни
        </th>
        <th>
            Стоимость
        </th>
        <th>
            Оплачен
        </th>
        <th>
            Одобрен
        </th>
        <th>
            Комментарий
        </th>

        <c:if test="${currentUser.role == 'ADMIN'}">
            <th>
                Управление
            </th>
        </c:if>

    </tr>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>
                    ${order.user.firstname} ${order.user.lastname} <br> ${order.user.passport}
            </td>
            <td>
                    ${order.car.model} ${order.car.licencePlate} <br> ${order.car.color} ${order.car.automaticTransmission ? 'АКПП':'МКПП'}
            </td>
            <td><div style="font-size: small">
                Дата заказа:<br>
                <fmt:formatDate pattern="dd.MM.yyyy, HH:mm" value="${order.date}"/> <br><br>
                Истекает:<br>
                <fmt:formatDate pattern="dd.MM.yyyy, HH:mm" value="${order.date}"/>
                 </div>
            </td>
            <td>
                    ${order.amountOfDays}
            </td>
            <td>
                    ${order.totalCost}
            </td>
            <td>
                    ${order.paid ? 'Оплачен':'Не оплачен'}
            </td>
            <td>
                <c:choose>
                        <c:when test="${order.reviewed == 'true'}">
                                ${order.accepted ? 'Одобрен':'Отклонен'}
                        </c:when>
                        <c:otherwise>
                            На рассмотрении
                        </c:otherwise>
                    </c:choose>
            </td>
            <td>
                <div style="padding-left: 10px">${order.comments} <br><br></div>
                <c:choose>
                <c:when test="${currentUser.role == 'ADMIN'}">
                            <form action="/func" method="post" style="width: 200px">
                                <input type="text" value="" name="comment" placeholder="Введите комментарий">
                                <input type="hidden" name="id" value="${order.id}">
                                <button type="submit">Добавить</button>
                            </form>
                </c:when>
                    <c:otherwise>
                        ${order.comments}
                    </c:otherwise>
                </c:choose>
            </td>

            <c:if test="${currentUser.role == 'ADMIN'}">
                <td>
                    <a href="/order/accept.html?id=${order.id}">Одобрить</a><br>
                    <a href="/order/reject/html?id=${order.id}">Отклонить</a><br>
                    <a href="/order/setPaid.html?id=${order.id}">Оплачен</a><br>
                    <a href="/order/setUnpaid.html?id=${order.id}">Не оплачен</a><br>
                    <a href="/order/delete.html?id=${order.id}">Удалить</a><br>
                </td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<%@include file="../common/footer.jsp" %>