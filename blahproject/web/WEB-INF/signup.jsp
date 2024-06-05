<%-- 
    Document   : signup
    Created on : Jun 5, 2024, 12:04:57 PM
    Author     : HELLO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign-up Page</title>
    </head>
    <body>
        <form method="post" action="/blahproject/signupServlet">
            <label for="firstname">First Name:</label><br>
            <input type="text" id="firstname" name="firstName"><br>
            <label for="lastname">Last Name:</label><br>
            <input type="text" id="lastname" name="lastName"><br>
            <label for="email">Email:</label><br>
            <input type="text" id="email" name="userEmail"><br>
            <label for="pass">Password:</label><br>
            <input type="password" id="pass" name="passWord"><br>
            <input type="submit" value="signup"/> <br>
        </form>
    </body>
</html>
