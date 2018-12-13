<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="common/header.jsp" %>
<div align="center">
    <h2>Изменение учетной записи</h2>
    <form action="/edituser" method="post">
        <div align="left">
            <p><label>Имя:</label>
                <input type="text" name="fname" value="${user.firstname}"></p>
            <p><label>Фамилия:</label>
                <input type="text" name="fsurname" value="${user.lastname}"></p>
            <p><label>Паспорт:</label>
                <input type="text" name="fpassport" value="${user.passport}"></p>
            <p><label>Старый пароль:</label>
                <input type="password" name="foldpassword"></p>
            <p><label>Новый пароль:</label>
                <input type="password" name="fnewpassword"></p>
                <input type="hidden" name="id" value="${user.id}"></p>
            <p><label>Роль</label>
            <select name="role">
                <option value="1">Клиент</option>
                <option value="2">Администратор</option>
            </select>
        </div>
        <button type="submit">Сохранить</button>
    </form>
    <br>
    ${message}
</div>
<%@include file="common/footer.jsp" %>