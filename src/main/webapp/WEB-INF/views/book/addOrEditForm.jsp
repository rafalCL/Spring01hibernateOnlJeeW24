<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>${formHeader}</title>
</head>
<body>
    <h2>${formHeader}</h2>
    <form:form method="post" modelAttribute="book">
        <div><label for="title">title<form:input id="title" path="title"/></label><form:errors path="title"/></div>
        <div><label for="rating">rating<form:input id="rating" path="rating" type="number"/></label><form:errors path="rating"/></div>
        <div><label for="description">description<form:textarea id="description" path="description"/></label><form:errors path="description"/></div>
        <div><label for="publisher">publisher<form:select id="publisher" path="publisher.id" items="${publishers}" itemValue="id" itemLabel="name"/></label><form:errors path="publisher"/></div>
        <div><label for="authors">authors<form:select id="authors" path="authors" items="${authors}" itemValue="id" itemLabel="name" multiple="true"/></label><form:errors path="authors"/></div>
        <div><label for="pages">pages<form:input id="pages" path="pages" type="number" value="2"/></label><form:errors path="pages"/></div>
        <div><input type="submit"></div>
        <div><label for="errors"><form:errors id="errors" path="*"/></label></div>
    </form:form>
</body>
</html>
