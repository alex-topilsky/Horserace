<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="registration"/></title>
</head>
<body>

<br/>
${registrationError}
<br/>
<form action="/reg" method="post">
name:  <input name="j_name" type="text" title="name"/> <br/>
login:  <input name="j_username" type="text" title="login"/><br/>
password:  <input name="j_password" type="password" title="password"/><br/>
 <input type="submit"/>
</form>

</body>
</html>
