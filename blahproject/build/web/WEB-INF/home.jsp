<%-- 
    Document   : home
    Created on : Jun 10, 2024, 3:20:34 PM
    Author     : bim26
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Post, dao.postDAO, model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Home Page</title>
</head>
<body>
        <h1>Welcome ${sessionScope['user_id']}</h1>
        
        <div>
            <img width="100" height="100">
        </div>
        
        <div>
            <a href="profile.jsp">View Your Profile</a>
        </div>

        <h1>Create a Post</h1>
        <form action="/blahproject/postServlet" method="post">
            <label for="body">Body:</label><br>
            <textarea id="body" name="postContent" rows="4" cols="50" required></textarea><br><br>
            <input type="submit" value="Post">
        </form>
        <hr>

        <%
            // Fetch all posts from the database and set them as a request attribute
            List<Post> posts = postDAO.getAllPosts();
            request.setAttribute("posts", posts);
        %>
        
        <c:forEach var="post" items="${posts}">
            <div>
                <p>${post.body}</p>
                <small>Posted by ${post.first_name} ${post.last_name} on <fmt:formatDate value="${post.post_time}" pattern="yyyy-MM-dd HH:mm:ss" /></small>
            </div>
            <hr>
        </c:forEach>
        
        <h2>Send Message</h2>
        <form action="sendMessage" method="post">
            <label for="recipient">Recipient:</label>
            <input type="text" id="recipient" name="recipient" required><br><br>
            <label for="message">Message:</label><br>
            <textarea id="message" name="message" rows="4" cols="50" required></textarea><br><br>
            <input type="submit" value="Send Message">
        </form>

        <h2>Add Friend</h2>
        <a href="addFriend?userId=123">Add Friend</a> 
        <hr>
        
        <h2>Friends List</h2>
        <ul>
            <li>Friend 1</li>
            <li>Friend 2</li>
            <li>Friend 3</li>
        </ul>

    <c:if test="${empty sessionScope.user}">
        <p>Please <a href="login.jsp">log in</a> to access this page.</p>
    </c:if>
</body>
</html>
