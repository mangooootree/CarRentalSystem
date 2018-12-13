<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="common/header.jsp" %>
<div align="center">
    <h2>Registration</h2>
    <form action="/registration.html" method="post">
        <div align="left">
            <p><label>Имя:</label>
                <input type="text" name="firstname"></p>
            <p><label>Фамилия:</label>
                <input type="text" name="lastname"></p>
            <p><label>Паспорт:</label>
                <input type="text" name="passport"></p>
            <p><label>Логин</label>
                <input type="text" name="login"></p>
            <p><label>Пароль</label>
                <input type="password" name="password"></p>
        </div>
        <button type="submit">Отправить</button>
    </form>
    <br>
    ${message}
</div>
<%@include file="common/footer.jsp" %>