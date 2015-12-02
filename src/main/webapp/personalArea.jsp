<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><fmt:message key="welcome"/></title>
</head>
<body>
Close page<br/>
<br/>

<jsp:useBean id="user" scope="session" type="DAO.Users.UserBean" />

${user.getName()}

<a href="/myBets"> My bet list</a> <br/>

</body>
</html>
