<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="common/header.jsp" %>
<div align="center">
    <h2><fmt:message key="registration.registration"/></h2>
    <form action="/registration.html" method="post">
        <div align="left">
            <p><label><fmt:message key="user.firstname"/></label>
                <input type="text" name="firstname"></p>
            <p><label><fmt:message key="user.lastname"/></label>
                <input type="text" name="lastname"></p>
            <p><label><fmt:message key="user.passport"/></label>
                <input type="text" name="passport"></p>
            <p><label><fmt:message key="user.login"/></label>
                <input type="text" name="login"></p>
            <p><label><fmt:message key="user.password"/></label>
                <input type="password" name="password"></p>
        </div>
        <button type="submit"><fmt:message key="registration.button"/></button>
    </form>
    <br>
    ${message}
</div>
<%@include file="common/footer.jsp" %>