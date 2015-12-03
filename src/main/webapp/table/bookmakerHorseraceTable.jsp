<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Horserace List</title>
</head>
<body>
<jsp:useBean id="racesList" scope="session" type="java.util.ArrayList" />

<table align="center" cellpadding="4" cellspacing="1">
  <thead>
  <tr>
    <th>Number</th>
    <th>rate</th>
    <th>date</th>
    <th>name</th>
    <th>show info</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="races" items="${racesList}">
    <tr>
      <td>${races.getIdRaces()}</td>
      <td> ${races.getWinRate()}<br/>
          <form action="/bookmakerHorseraceEditor" method="post">
          <input name="winRate" type="text" value="" title="Win rate edit"/><br/>
          <button type="submit" name="winRateEdit" value="${races.getIdRaces()}"> Win rate edit </button>
          </form>
      </td>
      <td>
          ${races.getDateRace()}
      </td>
      <td>
          ${races.getNameRaces()}
      </td>
      <td><form action="/raceinfo" method="post">
        <button type="submit" name="NumberRace" value="${races.getIdRaces()}"> Show </button>
      </form></td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
