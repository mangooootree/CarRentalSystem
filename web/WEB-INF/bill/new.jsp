<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../common/header.jsp" %>

<table align="center" border="1" cellpadding="20">
    <td>
        <div valign="top" align="center">
            <h2><fmt:message key="bill.new"/></h2>

            <div align="left">
                <b><fmt:message key="bill.client"/>:</b><br>
                <fmt:message key="user.firstname"/>: ${order.user.firstname}<br>
                <fmt:message key="user.lastname"/>: ${order.user.lastname}<br>
                <fmt:message key="user.passport"/>: ${order.user.passport}<br>
                <br>
                <b><fmt:message key="bill.car"/>:</b><br>
                <fmt:message key="car.model"/>: ${order.car.model}<br>
                <fmt:message key="car.color"/>: ${order.car.color}<br>
                <fmt:message key="car.licencePlate"/>: ${order.car.licencePlate}<br>
                <br>
                <b><fmt:message key="bill.orderDate"/>: </b><fmt:formatDate pattern="dd.MM.yyyy" value="${order.date}"/><br>
            </div>


            <form action="/bill/save.html" method="post">
                <div align="left">
                    <p><fmt:message key="bill.damage"/>:<br>
                        <textarea name="damage" style="width: 100%; height: 200px" ${disabled}>${damage}</textarea></p>
                    <p><fmt:message key="bill.price"/>:<br>
                        <input type="text" name="cost" value="${cost}" ${disabled}></p>

                    <input type="hidden" name="orderId" value="${order.id}">

                    <button type="submit" ${disabled}><fmt:message key="bill.button"/></button>
                </div>
            </form>
            <c:if test="${not empty billSaved}">
                <fmt:message key="${billSaved}"/>.
            </c:if>
        </div>
    </td>
</table>
<%@include file="../common/footer.jsp" %>