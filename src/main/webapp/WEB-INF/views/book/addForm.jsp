<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add book</title>
</head>
<body>
    <h2>add book form</h2>
    <form:form method="post" modelAttribute="book">
        <div><label for="title">title<form:input id="title" path="title"/></label></div>
        <div><label for="rating">rating<form:input id="rating" path="rating" type="number"/></label></div>
        <div><label for="description">description<form:textarea id="description" path="description"/></label></div>
        <div><label for="publisher">publisher<form:select id="publisher" path="publisher" items="${publishers}" itemValue="id" itemLabel="name"/></label></div>
        <div><input type="submit"></div>
    </form:form>
</body>
</html>
