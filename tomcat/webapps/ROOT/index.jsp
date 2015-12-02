<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="en_US" scope="session"/>
<html>
<head>
    <title>Hello</title>
</head>
<body>


<form action="/localeChange" method="post">
    <input type="submit" name="buttonLocale" value="RU" />
    <input type="submit" name="buttonLocale" value="EN" />
</form>


<fmt:bundle basename="locale"/>
<fmt:message key="welcome"/>

<%@ include file="/login.jsp"%>

<c:if test="${not empty language}">
    ${language}
</c:if>

</body>
</html>
