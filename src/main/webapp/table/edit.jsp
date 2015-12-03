<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="/adminEditor" method="post">
  <input name="dateValue" type="text" value="" title="Date edit"/><br/>
  <button type="submit" name="dateEdit"> DateEdit </button>
</form>

<form action="/adminEditor" method="post">
    <input name="winRate" type="text" value="" title="Win rate edit"/><br/>
    <button type="submit" name="winRateEdit" value=""> DateEdit </button>
</form>

<form action="/adminEditor" method="post">
    <input name="nameRace" type="text" value="" title="Name race edit"/><br/>
    <button type="submit" name="nameRaceEdit" value=""> DateEdit </button>
</form>
</body>
</html>
