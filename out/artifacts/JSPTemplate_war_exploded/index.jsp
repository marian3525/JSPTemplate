<%@ page import="repository.DBRepo" %>
<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  SiteUser: marian
  Date: 21.06.2019
  Time: 05:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Login</title>
  <script src="jquery-3.4.0.min.js"></script>
</head>

<body>
    <form action="${pageContext.request.contextPath}/loginService" method="post">
        <div>
          <span>Username:</span>
          <input type="text" id="username" name="username" />
        </div>

        <div>
          <span>Password:</span>
          <input type="password" name="password" />
        </div>
        <input type="submit"/>
    </form>

</body>
</html>