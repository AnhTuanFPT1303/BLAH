<%-- 
    Document   : welcome
    Created on : May 30, 2024, 3:10:47 PM
    Author     : HELLO
--%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <form method="post" action="/blahproject/loginServlet">
            <label for="email">Email:</label><br>
            <input type="text" id="email" name="userEmail"><br>
            <label for="pass">Password:</label><br>
            <input type="password" id="pass" name="passWord"><br>
            <input type="submit" value="login"/>
        </form>
    </body>
</html>
