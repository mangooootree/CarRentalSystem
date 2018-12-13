<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="common/header.jsp" %>
<div align="center">
    <h2>Login</h2>
    <form action="/login.html" method="post">
        <div align="left">
            <p><label>Login:</label>
                <input type="text" name="login"></p>
            <p><label>Password:</label>
                <input type="password" name="password"></p>
        </div>
        <button type="submit">Log in</button>
    </form>
    <br>
    ${message}
</div>
<%@include file="common/footer.jsp" %>