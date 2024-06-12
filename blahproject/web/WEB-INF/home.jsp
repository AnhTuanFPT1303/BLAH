<%-- 
    Document   : home
    Created on : Jun 10, 2024, 3:20:34 PM
    Author     : bim26
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Post, dao.postDAO" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page import="java.sql.*" %>

<html>
    <head>
        <title>Home Page</title>
    </head>
    <body>
        <h1>Welcome to the Home Page</h1>
        <c:if test="${not empty sessionScope.user_id}">
            <h1>Create a Post</h1>
            <form action="post" method="post">
                <label for="body">Body:</label><br>
                <textarea id="body" name="body" rows="4" cols="50" required></textarea><br><br>
                <input type="submit" value="Post">
            </form>
            <hr>
            <%
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
        </c:if>
    </body>
</html>








