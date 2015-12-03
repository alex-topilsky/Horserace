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
      </td>
      <td>
      ${races.getDateRace()} <br/>
        <form action="/adminEditor" method="post">
        <input name="dateValue" type="text" value="" title="Date edit"/><br/>
        <button type="submit" name="dateEdit" value="${races.getIdRaces()}"> Date Edit </button>
      </form>
      </td>
      <td>
        ${races.getNameRaces()}<br/>
        <form action="/adminEditor" method="post">
          <input name="nameRace" type="text" value="" title="Name race edit"/><br/>
          <button type="submit" name="nameRaceEdit" value="${races.getIdRaces()}"> Name race edit </button>
        </form>

      </td>
      <td><form action="/raceinfo" method="post">
        <button type="submit" name="NumberRace" value="${races.getIdRaces()}"> Show </button>
      </form></td>

      <td><form action="/adminEditor" method="post">
        <button type="submit" name="DeleteHorserace" value="${races.getIdRaces()}"> Delete </button>
      </form></td>

    </tr>
  </c:forEach>
  </tbody>
</table>

Add race: <br/>
<form action="/adminEditor" method="post">
Name:  <input name="AddNameRace" type="text" value="" title="Name race "/><br/>
Date:  <input name="AddDateValue" type="text" value="" title="Date "/><br/>
  <button type="submit" name="NewRace" value="new"> Add new race </button>
</form>

<p>
  <a href="/login.jsp"> You personal area</a> <br/>

  <a href="/horseList"> You can see horses list</a> <br/>
  <a href="/allHorserace"> You can see horserace list</a> <br/>
</p>

</body>
</html>
