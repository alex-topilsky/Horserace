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
<table align="center">
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
        <form action="/doBet" method="post">
          <input name="ValueBet" type="text" value="" title="do Bet"/><br/>
          <button type="submit" name="doBet" value="${race.getIdRace()}"> do Bet </button>
        </form>
      </c:if>
      </td>      
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