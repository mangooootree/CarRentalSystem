<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../common/header.jsp" %>
<div align="center">

<table border="1" cellpadding="10">
    <td style="border: 0px">
        <a href="/car/new.html">Добавить автомобиль</a>
    </td>
    <tr>
        <th>
            Модель
        </th>
        <th>
            Цвет
        </th>
        <th>
            Гос. номер
        </th>
        <th>
            Тип КПП
        </th>
        <th>
            Заказан/свободен
        </th>
        <th>
            Цена в сутки
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
                    ${car.automaticTransmission ? 'АКПП':'МКПП'}
            </td>
            <td>
                    ${car.ordered ? 'Заказан':'Свободен'}
            </td>
            <td>
                    ${car.price}
            </td>
            <td>
                <a href="/car/delete.html?id=${car.id}">Удалить</a><br>
                <a href="/car/edit.html?id=${car.id}">Редактировать</a><br>
            </td>
        </tr>
    </c:forEach>
</table>
</div>
<%@include file="../common/footer.jsp" %>