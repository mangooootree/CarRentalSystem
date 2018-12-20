<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../common/header.jsp" %>

<table align="center" border="1" cellpadding="10">

    <tr>
        <th>
            <fmt:message key="user.firstname"/>
        </th>
        <th>
            <fmt:message key="user.lastname"/>
        </th>
        <th>
            <fmt:message key="user.password"/>
        </th>
        <th>
            <fmt:message key="user.login"/>
        </th>
        <th>
            <fmt:message key="user.role"/>
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
                <fmt:message key="${currentUser.role.name}"/>
            </td>
            <td>
                <a href="/user/edit.html?id=${user.id}"><fmt:message key="user.edit"/></a><br>
                <a href="/user/delete.html?id=${user.id}"><fmt:message key="user.delete"/></a>
            </td>
        </tr>
    </c:forEach>
</table>
<%@include file="../common/footer.jsp" %>