<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<jsp:useBean id="horseList" scope="session" type="java.util.ArrayList" />

<table align="center" cellpadding="4" cellspacing="1">
  <thead>
  <tr>
    <th>id</th>
    <th>name</th>
    <th>age</th>
    <th>breed</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="horse" items="${horseList}">
    <tr>
      <td>
      ${horse.getIdHorse()}
      </td>
      <td>
      ${horse.getName()}
        <form action="/adminHorseTable" method="post">
          <input name="nameHorse" type="text" value="" title="Name horse edit"/><br/>
          <button type="submit" name="nameHorseEdit" value="${horse.getIdHorse()}"> Name edit </button>
        </form>
      </td>
      <td>
      ${horse.getAge()}
        <form action="/adminHorseTable" method="post">
          <input name="ageHorse" type="text" value="" title="Age horse edit"/><br/>
          <button type="submit" name="ageHorseEdit" value="${horse.getIdHorse()}"> age edit </button>
        </form>
      </td>
      <td>
      ${horse.getBread()}
        <form action="/adminHorseTable" method="post">
          <input name="breadHorse" type="text" value="" title="Bread horse edit"/><br/>
          <button type="submit" name="breadHorseEdit" value="${horse.getIdHorse()}"> bread edit </button>
        </form>
      </td>
      <td><form action="/adminHorseTable" method="post">
        <button type="submit" name="DeleteHorse" value="${horse.getIdHorse()}"> Delete </button>
      </form></td>
    </tr>
  </c:forEach>
  </tbody>
</table>

Add horse: <br/>
<form action="/adminHorseTable" method="post">
  Name:  <input name="nameHorse" type="text" value="" title="name "/><br/>
  Age:  <input name="ageHorse" type="text" value="" title="age "/><br/>
  Breed: <input name="breedHorse" type="text" value="" title="breed "/><br/>
  <button type="submit" name="NewHorse" value="new"> Add new horse </button>
</form>

</body>
</html>
