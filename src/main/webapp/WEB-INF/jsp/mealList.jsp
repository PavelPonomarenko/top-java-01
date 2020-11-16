<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>MealList</title>
</head>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>UserName</th>
            <th>Description</th>
            <th>Datetime</th>
            <th>Calories</th>
        </tr>
        </thead>
        <c:forEach var="meal" items="${allMeals}">
            <jsp:useBean id="meal" class="com.gmail.ponomarenko.model.UserMeal"/>

            <tr>
                <td>${userName}</td>
                <td><c:out value="${meal.description}"/></td>
                <td>${meal.dateTime}</td>
                <td><%= meal.getCalories()%>
                </td>
            </tr>
        </c:forEach>
    </table>
</section>
<br><br>
<h3>Create new meal for user:</h3>
<form:form action="/topjava/meals" method="post" modelAttribute="userMeal">
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <td><form:label path="description">Add description</form:label></td>
            <td><form:input path="description"/></td>
        </tr>
        <tr>
            <td><form:label path="calories">Add calories</form:label></td>
            <td><form:input path="calories"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
                <input type="reset" value="Reset"/>
            </td>
        </tr>
    </table>
</form:form>
<hr>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
