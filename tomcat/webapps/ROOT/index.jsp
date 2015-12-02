<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="ru_RU" scope="session"/>

<html>
<head>
    <title>Hello</title>
</head>
<body>
<p>Добро пожаловать!</p>

<form action="/localeChange" method="post">
    <input type="submit" name="buttonLocale" value="RU" />
    <input type="submit" name="buttonLocale" value="EN" />
</form>


<fmt:bundle basename="locale"/>
<fmt:message key="welcome"/>



<c:if test="${not empty language}">
    ${language}
</c:if>

</body>
</html>
