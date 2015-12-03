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
Name: ${raceListRaces.nameRaces} <br/>
Date: ${raceListRaces.dateRace} <br/>
Rate: ${raceListRaces.winRate} <br/>

<c:forEach var="race" items="${raceList}">
  <c:if test="${race.getWinner()==null}">
    <c:set var="win" scope="request" value="true"/>
</c:if>
</c:forEach>
<table>
  <thead>
  <tr>
    <th>horse</th>
    <th>Win</th>

  </tr>
  </thead>
  <tbody>
  <c:forEach var="race" items="${raceList}">
    <tr>
      <td>${raceListHorses.get(race.getIdHorse()-1).getName()}</td>
      <td>${race.getWinner()}</td>
      <td><c:if test="${raceListRaces.done!='done'}">
        <form action="/doBet" method="post">
          <input name="ValueBet" type="text" value="" title="Date edit"/><br/>
          <button type="submit" name="doBet" value="${race.getIdRace()}"> DateEdit </button>
        </form>
      </c:if>
      </td>      
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>