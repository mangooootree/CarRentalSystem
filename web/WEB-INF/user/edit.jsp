<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../common/header.jsp" %>
<div align="center">

    <c:choose>
        <c:when test="${user.role =='CLIENT'}">
            <c:set var="client" value="selected"/>
        </c:when>
        <c:when test="${user.role =='ADMIN'}">
            <c:set var="admin" value="selected"/>
        </c:when>
    </c:choose>

    <h2>Изменение профиля</h2>
    <form action="/user/update.html" method="post">
        <div align="left">
            <p><label>Имя:</label>
                <input type="text" name="firstname" value="${user.firstname}"></p>
            <p><label>Фамилия:</label>
                <input type="text" name="lastname" value="${user.lastname}"></p>
            <p><label>Паспорт:</label>
                <input type="text" name="passport" value="${user.passport}"></p>
            <br>

            <div style="background: #f9fafe">
                <br>
                Изменить пароль: (необязательные поля)
                <p><label>Старый пароль:</label>
                    <input type="password" name="oldPassword"></p>
                <p><label>Новый пароль:</label>
                    <input type="password" name="newPassword"></p>
                <input type="hidden" name="id" value=${user.id}></p>
                <br>
            </div>

            <br>
            <c:if test="${currentUser.role == 'ADMIN'}">
            <p><label>Роль</label>
                <select name="role">
                    <option ${client} value="1">Клиент</option>
                    <option ${admin} value="2">Администратор</option>
                </select>
                </c:if>
        </div>
        <button type="submit">Сохранить</button>
    </form>
    <br>
    ${message}
</div>
<%@include file="../common/footer.jsp" %>