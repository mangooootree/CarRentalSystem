<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../common/header.jsp" %>

<table align="center" border="1" cellpadding="10">

    <tr>
        <th>
            Имя
        </th>
        <th>
            Фамилия
        </th>
        <th>
            Паспорт
        </th>
        <th>
            Логин
        </th>
        <th>
            Роль
        </th>
        <th>

        </th>
    </tr>
    <c:forEach items="${requestScope.users}" var="user">
        <tr>
            <td>
                    ${user.firstname}
            </td>
            <td>
                    ${user.lastname}
            </td>
            <td>
                    ${user.passport}
            </td>
            <td>
                    ${user.login}
            </td>
            <td>
                    ${user.role.name}
            </td>
            <td>
                    <a href="/user/edit.html?id=${user.id}">Редактировать</a><br>
                    <a href="/user/delete.html?id=${user.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>
<%@include file="../common/footer.jsp" %>