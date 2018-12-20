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

    <h2><fmt:message key="user.editProfile"/></h2>
    <form action="/user/update.html" method="post">
        <div align="left">
            <p><label><fmt:message key="user.firstname"/></label>
                <input type="text" name="firstname" value="${user.firstname}"></p>
            <p><label><fmt:message key="user.lastname"/></label>
                <input type="text" name="lastname" value="${user.lastname}"></p>
            <p><label><fmt:message key="user.passport"/></label>
                <input type="text" name="passport" value="${user.passport}"></p>
            <br>

            <div style="background: #f9fafe">
                <br>
                <fmt:message key="user.edit.changePassword"/> (<fmt:message key="user.edit.optionalFields"/>)
                <p><label><fmt:message key="user.edit.oldPassword"/></label>
                    <input type="password" name="oldPassword"></p>
                <p><label><fmt:message key="user.edit.newPassword"/></label>
                    <input type="password" name="newPassword"></p>
                <input type="hidden" name="id" value=${user.id}></p>
                <br>
            </div>

            <br>
            <c:if test="${currentUser.role == 'ADMIN'}">
            <p><label><fmt:message key="user.role"/></label>
                <select name="role">
                    <option ${client} value="1"><fmt:message key="role.client"/></option>
                    <option ${admin} value="2"><fmt:message key="role.admin"/></option>
                </select>
                </c:if>
        </div>
        <button type="submit"><fmt:message key="user.edit.button"/></button>
    </form>
    <br>
    <c:if test="${not empty profileUpdated}">
        <fmt:message key="${profileUpdated}"/>.
    </c:if>
    <c:if test="${not empty passwordUpdated}">
        <fmt:message key="${passwordUpdated}"/>.
    </c:if>
    <c:if test="${not empty passwordIncorrect}">
        <fmt:message key="${passwordIncorrect}"/>.
    </c:if>
    <c:if test="${not empty checkForm}">
        <fmt:message key="${checkForm}"/>.
    </c:if>
</div>
<%@include file="../common/footer.jsp" %>