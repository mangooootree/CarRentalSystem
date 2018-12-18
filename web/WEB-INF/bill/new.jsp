<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../common/header.jsp" %>

<table align="center" border="1" cellpadding="20">
    <td>
        <div valign="top" align="center">
            <h2>Новый счет</h2>

            <div align="left">
                <b>Клиент:</b><br>
                Имя: ${order.user.firstname}<br>
                Фамилия: ${order.user.lastname}<br>
                Паспорт: ${order.user.passport}<br>
                <br>
                <b>Автомобиль:</b><br>
                Модель: ${order.car.model}<br>
                Цвет: ${order.car.color}<br>
                Госномер: ${order.car.licencePlate}<br>
                <br>
                <b>Дата заказа: </b><fmt:formatDate pattern="dd.MM.yyyy" value="${order.date}"/><br>
            </div>


            <form action="/bill/save.html" method="post">
                <div align="left">
                    <p>Описание повреждения:<br>
                        <textarea name="damage" style="width: 100%; height: 200px" ${disabled}>${damage}</textarea></p>
                    <p>Стоимость:<br>
                        <input type="text" name="cost" value="${cost}" ${disabled}></p>

                    <input type="hidden" name="orderId" value="${order.id}">

                    <button type="submit" ${disabled}>Добавить</button>
                </div>
            </form>
            ${message}
        </div>
    </td>
</table>
<%@include file="../common/footer.jsp" %>