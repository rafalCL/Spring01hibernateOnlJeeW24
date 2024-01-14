<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add person</title>
</head>
<body>
    <form:form method="post" modelAttribute="person">
        <div><label for="login">login<form:input id="login" path="login"/></label></div>
        <div><label for="password">password<form:password id="password" path="password"/></label></div>
        <div><label for="email">email<form:input id="email" path="email"/></label></div>
        <div><label for="hobbies">hobbies<form:checkboxes items="${hobbies}" id="hobbies" path="hobbies"/></label></div>
        <div><input type="submit"></div>
    </form:form>
</body>
</html>
