<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../common/header.jsp" %>

<table align="center" border="1" cellpadding="20">

    <td valign="top" align="center">
        <h2>Редактировать автомобиль</h2>
        <form action="/car/update.html" method="post" enctype='multipart/form-data'>
            <div align="left">
                <p>Модель<br>
                    <input type="text" value="${car.model}" name="model"></p>
                <p>Цвет<br>
                    <input type="text" value="${car.color}" name="color"></p>
                <p>Госномер<br>
                    <input type="text" value="${car.licencePlate}" name="licencePlate"></p>
                <p>Тип КПП<br>
                    <select name="gearbox">
                        <option value="false">Ручная</option>
                        <option value="true">Автоматическая</option>
                    </select></p>
                <p>Цена<br>
                    <input type="text" value="${car.price}" name="price"></p>
                    <input type="hidden" name="id" value="${car.id}"></p>
                <button type="submit">Сохранить</button>
            </div>
        </form>
    </td>

<%@include file="../common/footer.jsp" %>