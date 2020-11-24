<%@ page import="com.gmail.ponomarenko.model.UserMeal" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 23.11.2020
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<hr>
<h2> ------------------- meal list --------------------- </h2>
<section>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>id meal</th>
            <th>Date and time of meal</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>
        </thead>
        <c:forEach items="${userMealList}" var="meal">
            <jsp:useBean id="meal" scope="page" type="com.gmail.ponomarenko.model.UserMeal"/>
            <tr>
                <td>${meal.id}</td>
                </td>
                <td>${meal.dateTime}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                </td>
            </tr>
        </c:forEach>
    </table>
</section>
<hr>
<jsp:include page="fragments/footer.jsp"/>
<hr>
</body>
</html>
