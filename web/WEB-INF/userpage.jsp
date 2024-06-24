<%@page contentType="text/html" pageEncoding="UTF-8" import="model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Page</title>
</head>
<body>
    <h1>Welcome, ${user.first_name} ${user.last_name}!</h1>
    <p>Email: ${user.email}</p>

    <h2>Timeline</h2>
    <c:forEach items="${posts}" var="post">
        <p>${post.body}</p>
    </c:forEach>

    <form action="userpageServlet" method="post">
        <label for="body">Body:</label>
        <textarea id="body" name="body"></textarea><br><br>
        <input type="submit" value="Post">
    </form>

    <form action="logout" method="post">
        <input type="submit" value="Logout"/>
    </form>
</body>
</html>