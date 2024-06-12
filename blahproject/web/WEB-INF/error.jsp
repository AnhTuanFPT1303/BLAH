<%-- 
    Document   : error
    Created on : Jun 10, 2024, 3:35:37 PM
    Author     : bim26
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error</title>
</head>
<body>
    <h1>Error</h1>
    <% if (request.getAttribute("msg") != null) { %>
        <p><%= request.getAttribute("msg") %></p>
    <% } else { %>
        <p>Unknown error occurred.</p>
    <% } %>
</body>
</html>

