<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My bets</title>
</head>
<body>
<jsp:useBean id="betsList" scope="session" type="java.util.ArrayList" />

<table>
    <thead>
    <tr>
        <th>id bet</th>
        <th>id race</th>
        <th>id user</th>
        <th>rate</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="bet" items="${betsList}">
        <tr>
            <td>${bet.getIdBet()}</td>
            <td>${bet.getIdRace()}</td>
            <td>${bet.getIdUser()}</td>
            <td>${bet.getRate()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
