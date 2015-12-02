<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="error"/></title>
</head>
<body>
   Error<br/>
   try again<br/>
   <%@ include file="/login.jsp"%>
   or:<br/>
   <a href="/index.jsp"> Try return to Main page</a><br/>
   <a href="/registration.jsp"> Go to registration page</a><br/>
</body>
</html>
