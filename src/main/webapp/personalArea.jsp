<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><fmt:message key="welcome"/></title>
</head>
<body>
Close page<br/>
<br/>

<jsp:useBean id="user" scope="session" type="DAO.Users.UserBean" />
Youre name:  ${user.getName()}<br/>
You balance:   ${user.getBalance()}<br/>
<br/>
<a href="/myBets"> My bet list</a> <br/>
<br/>
<form action="/personalArea" method="post"><br/>
    <input name="replenishTheBalance" type="text" value="" title="balance"/><br/>
    <input type="submit"/>
</form>
<br/>

<br/>
<form action="/logaut" method="post">
    <input type="submit" name="logaut" value="out" />
</form>
<br/>

<a href="/index.jsp"> You can go to main page</a> <br/>
<a href="/horseList"> You can see horses list</a> <br/>
<a href="/allHorserace"> You can see horserace list</a> <br/>

</body>
</html>
