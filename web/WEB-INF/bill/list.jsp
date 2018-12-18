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
                Клиент
            </th>
            <th>
                Автомобиль
            </th>
            <th>
                Дата заказа
            </th>
            <th>
                Стоимость
            </th>
            <th>
                Повреждения
            </th>
            <th>
                Оплата
            </th>
            <c:if test="${currentUser.role == 'ADMIN'}">
                <th>
                Управление
                </th>
            </c:if>
        </tr>
        <c:forEach items="${bills}" var="bill">
            <tr>
                <td>
                    Имя: ${bill.order.user.firstname}<br>
                    Фамилия: ${bill.order.user.lastname}<br>
                    Паспорт: ${bill.order.user.passport}<br>
                </td>
                <td>
                    Модель: ${bill.order.car.model}<br>
                    Цвет: ${bill.order.car.color}<br>
                    Госномер: ${bill.order.car.licencePlate}<br>
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
                        ${bill.paid ? 'Оплачен':'Не оплачен'}
                </td>

                <c:if test="${currentUser.role == 'ADMIN'}">
                    <td>
                        <a href="/bill/delete.html?id=${bill.id}">Удалить</a><br>
                        <a href="/bill/edit.html?id=${bill.id}">Редактировать</a><br>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>
<%@include file="../common/footer.jsp" %>