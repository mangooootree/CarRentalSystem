<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="common/header.jsp" %>
<div align="center">
    <h2><fmt:message key="login.login"/></h2>
    <form action="/login.html" method="post">
        <div align="left">
            <p><label><fmt:message key="login.login"/></label>
                <input type="text" name="login"></p>
            <p><label><fmt:message key="login.password"/></label>
                <input type="password" name="password"></p>
        </div>
        <button type="submit"><fmt:message key="login.loginButton"/></button>
    </form>
    <br>
    ${message}
</div>
<%@include file="common/footer.jsp" %>