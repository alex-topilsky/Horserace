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

<table align="center" cellpadding="4" cellspacing="1">
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
            <td>
                <c:forEach var="races" items="${racesList}">
                <c:forEach var="race" items="${raceList}">
                    <c:if test="${race.getIdRace()==bet.getIdRace()}">
                    <c:if test="${race.getIdRaces()==races.getIdRaces()}">
                        ${races.getNameRaces()}
                    </c:if>
                    </c:if>
                </c:forEach>
            </c:forEach>
        </td>
            <c:forEach var="race" items="${raceList}">
                <c:forEach var="horse" items="${horseList}">
                <c:if test="${race.getIdRace()==bet.getIdRace()}">
                    <c:if test="${race.getIdHorse()==horse.getIdHorse()}">
                        <td>   ${horse.getIdHorse()}</td>
                        <td>   ${horse.getName()}</td>
                    </c:if>
                </c:if>
            </c:forEach>
            </c:forEach>
            </td>
            <td>${bet.getIdUser()}</td>
            <td>${bet.getRate()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p>
    <a href="/login.jsp"> You personal area</a> <br/>

    <a href="/horseList"> You can see horses list</a> <br/>
    <a href="/allHorserace"> You can see horserace list</a> <br/>
</p>
</body>
</html>
