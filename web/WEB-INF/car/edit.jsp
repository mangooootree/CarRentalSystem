<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../common/header.jsp" %>

<table align="center" border="1" cellpadding="20">

    <td valign="top" align="center">
        <h2><fmt:message key="car.editCar"/></h2>
        <form action="/car/update.html" method="post" enctype='multipart/form-data'>
            <div align="left">
                <p><fmt:message key="car.model"/><br>
                    <input type="text" value="${car.model}" name="model"></p>
                <p><fmt:message key="car.color"/><br>
                    <input type="text" value="${car.color}" name="color"></p>
                <p><fmt:message key="car.licencePlate"/><br>
                    <input type="text" value="${car.licencePlate}" name="licencePlate"></p>
                <p><fmt:message key="car.gearbox"/><br>
                    <select name="gearbox">
                        <option value="false"><fmt:message key="car.gearbox.manual"/></option>
                        <option value="true"><fmt:message key="car.gearbox.auto"/></option>
                    </select></p>
                <p>Цена<br>
                    <input type="text" value="${car.price}" name="price"></p>
                    <input type="hidden" name="id" value="${car.id}"></p>
                <button type="submit"><fmt:message key="car.editButton"/></button>
            </div>
        </form>
    </td>

<%@include file="../common/footer.jsp" %>