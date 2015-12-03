<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Race </title>
</head>
<body>
<jsp:useBean id="raceList" scope="session" type="java.util.ArrayList" />
<jsp:useBean id="raceListRaces" scope="session" type="DAO.Races.RacesBean" />
<jsp:useBean id="raceListHorses" scope="session" type="java.util.ArrayList" />


Information about this race:  <br/>
<b>Name: </b>${raceListRaces.nameRaces} <br/>
<b>Date: </b>${raceListRaces.dateRace} <br/>
<b>Rate:</b> ${raceListRaces.winRate} <br/>

<c:forEach var="race" items="${raceList}">
    <c:if test="${race.getWinner()==null}">
        <c:set var="win" scope="request" value="true"/>
    </c:if>
</c:forEach>
<table align="center" cellpadding="4" cellspacing="1">
    <thead>
    <tr>
        <th>horse</th>
        <th>Win</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="race" items="${raceList}">
        <tr>
            <td> <c:forEach var="horse" items="${raceListHorses}">
                <c:if test="${horse.getIdHorse()==race.getIdHorse()}">
                    ${horse.getName()}
                </c:if>
                </c:forEach></td>
            <td>${race.getWinner()}</td>
            <td><c:if test="${raceListRaces.done!='done'}">
                <form action="/adminRaceInfo" method="post">
                    <button type="submit" name="doWin" value="${race.getIdRace()}"> Win </button>
                </form>
            </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

Added horse to race:
<form action="/adminRaceInfo">
    <select name = "HorseAdd">
        <c:forEach var="horse" items="${raceListHorses}">
            <option value="${horse.getIdHorse()}">${horse.getName()}</option>
        </c:forEach>
    </select>
    <button type="submit" name="NumberRace" value="${raceListRaces.getIdRaces()}"> Add </button>
</form>
<p>
    <a href="/login.jsp"> You personal area</a> <br/>

    <a href="/horseList"> You can see horses list</a> <br/>
    <a href="/allHorserace"> You can see horserace list</a> <br/>
</p>
</body>
</html>