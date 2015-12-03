<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My bets</title>
</head>
<body>
<jsp:useBean id="betsList" scope="session" type="java.util.ArrayList" />
<jsp:useBean id="racesList" scope="session" type="java.util.ArrayList" />
<jsp:useBean id="raceList" scope="session" type="java.util.ArrayList" />
<jsp:useBean id="horseList" scope="session" type="java.util.ArrayList" />

<table>
    <thead>
    <tr>
        <th>id bet</th>
        <th>id race</th>
        <th>name races</th>
        <th>id horse</th>
        <th>name horse</th>
        <th>id user</th>
        <th>rate</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="bet" items="${betsList}">
        <tr>
            <td>${bet.getIdBet()}</td>

            <td>${bet.getIdRace()}</td>
            <td>${racesList.get(raceList.get(bet.getIdRace()-1).getIdRaces()-1).getNameRaces()}</td>

            <td>${raceList.get(bet.getIdRace()-1).getIdHorse()}</td>
            <td>${horseList.get(raceList.get(bet.getIdRace()-1).getIdHorse()-1).getName()}</td>

            <td>${bet.getIdUser()}</td>
            <td>${bet.getRate()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
