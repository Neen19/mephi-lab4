<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Список элементов</title>
</head>
<body>
    <h2>Список элементов</h2>
        <form action="/lab4" method="post">
            <label for="person">Описание:</label>
            <input type="text" id="person" name="person" placeholder="Введите описание" required>
            <input type="submit" value="Добавить элемент">
        </form>
    <ul>
        <c:forEach var="item" varStatus="status" items="${requestScope.list}">
            <li>
                ${item}
                <form action="/lab4/${item.id}" method="post" style="display:inline;">
                    <input type="hidden" name="_method" value="PUT">
                    <input type="text" name="command" value="enter command">
                    <input type="submit" value="Редактировать">
                </form
            </li>
        </c:forEach>
    </ul>
</body>
</html>
