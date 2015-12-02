<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="welcome"/></title>
</head>
<body>
<fmt:message key="authorization"/>
<form action="/personalArea.jsp" method="post"><br/>
    <input name="j_username" type="text" value="login" title="login"/><br/>
    <input name="j_password" type="password" value="" title="password"/><br/>
    <input type="submit"/>
</form>
</body>
</html>