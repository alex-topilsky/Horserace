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

<form action="/logaut" method="post">
    <input type="submit" name="logaut" value="out" />
</form>
<br/>
<fmt:bundle basename="locale"/>
<fmt:message key="welcome"/>
<p>
<a href="/login.jsp"> You personal area</a> <br/>

<a href="/horseList"> You can see horses list</a> <br/>
<a href="/allHorserace"> You can see horserace list</a> <br/>
</p>

</body>
</html>
