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

<table align="center" cellpadding="4" cellspacing="1">
  <thead>
  <tr>
    <th>horse</th>
    <th>doBet</th>
    <th>Winner </th>
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
      <td>Autoriztion for do bet</td>
      <td>${race.getWinner()}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>

</body>
</html>
