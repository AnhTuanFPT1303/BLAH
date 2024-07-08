<%-- 
    Document   : friendRequest
    Created on : Jul 7, 2024, 1:13:02 AM
    Author     : HELLO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Post, dao.postDAO, model.User" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Pending Friend Requests</h2>
        <c:forEach var="friendRequest" items="${pendingList}">
            
        </c:forEach>
        <div>
            <p>Friend Request from User ID: <%= userRequest %></p>
            <form action="acceptFriend" method="post" style="display:inline;">
                <input type="hidden" name="userRequest" value="<%= userRequest %>">
                <input type="hidden" name="userAccept" value="<%= userId %>">
                <button type="submit">Accept</button>
            </form>
            <form action="rejectFriend" method="post" style="display:inline;">
                <input type="hidden" name="userRequest" value="<%= userRequest %>">
                <input type="hidden" name="userAccept" value="<%= userId %>">
                <button type="submit">Reject</button>
            </form>
        </div>
    </body>
</html>
