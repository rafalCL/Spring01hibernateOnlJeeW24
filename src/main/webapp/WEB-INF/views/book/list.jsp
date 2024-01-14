<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books:</title>
</head>
<body>
    <h2>${listHeader}</h2>
    <div>
        <c:forEach items="${books}" var="book">
            <div>${book} <a href="<c:url value="/book/editForm?id=${book.id}"/>">edit</a> </div>
        </c:forEach>
    </div>
</body>
</html>
