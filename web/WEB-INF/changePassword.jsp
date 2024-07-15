<%-- 
    Document   : verify
    Created on : Jun 22, 2024, 9:14:23 PM
    Author     : HELLO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="assets/css/verify.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <h2>Enter new password</h2
            <form method="post" action="/blahproject/verify">
                <input type="text" id="code" name="otp-code" placeholder="BLAH-">
                <div class="button-group">
                    <button id="change" name="action" values="changepass">Continue</button>
                </div>
            </form>
        </div>
    </body>
</html>