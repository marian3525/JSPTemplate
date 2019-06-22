<%--
  Created by IntelliJ IDEA.
  User: marian
  Date: 21.06.2019
  Time: 05:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login</title>
</head>
<body>
<form name="Login form" action="JSPTemplate_war_exploded/servlet/test" method="POST">
  <div>
    <span>Username:</span>
    <input type="text" name="username" />
  </div>
  <div>
    <span>Password:</span>
    <input type="password" name="password" />
  </div>
  <input type="submit" value="Log in" />
</form>
</body>
</html>