<%-- 
    Document   : home
    Created on : Jun 10, 2024, 3:20:34 PM
    Author     : bim26
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Post, dao.postDAO, model.User" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home Page</title>
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="assets/css/home.css">
    </head>
    <body>
        <header id="header">
        <nav class="navbar custom-navbar">
            <div class="container-fluid d-flex align-items-center">
                <a class="navbar-brand text-primary" href="/blahproject/home" style="font-weight: bold">BLAH</a>
                <form class="d-flex ms-2 flex-grow-1">
                    <input class="form-control" type="search" placeholder="Finding in BLAH" aria-label="Search">
                </form>
                <a class="navbar-brand text-primary log-out" href="/blahproject" style="font-weight: bold">Log out</a>
            </div>
        </nav>
    </header>
        <div class="container-fluid">
            <div class="row all-post">
                <nav class="col-2 py-3 bg-light">
                    <div class="profile-section mb-3 d-flex align-items-center">
                        <a href="user_page" class="d-flex align-items-center text-decoration-none text-dark">
                            <img src="assets/images/cheems_test.jpg" class="img-fluid rounded-circle avatar">
                            <p class="mb-0 ms-2 ava-name">${sessionScope['first_name']} ${sessionScope['last_name']}</p>
                        </a>
                    </div>
                    <div class="chat-box mb-3">
                        <h5>Chat box</h5>
                        <!-- Chatseggs here -->
                    </div>
                </nav>

                        <main class="col-8">
                    <h1 class="mt-3 text-primary home-logo">HOME</h1>
                    <form action="/blahproject/home" method="post" class="mb-4 post-method">
                        <div class="mb-3">
                            <textarea class="form-control" id="body" name="postContent" rows="4" placeholder="What ya thinking" required></textarea>
                        </div>
                        <button type="submit" class="btn btn-primary" style="padding: 5px 25px">Post</button>
                    </form>

                    <hr>

                    <%
                        List<Post> posts = postDAO.getAllPosts();
                        request.setAttribute("posts", posts);
                    %>

                    <c:forEach var="post" items="${posts}">
                        <div class="post mb-4" style="overflow-wrap: break-word">
                            <div class="post-header">
                                <small>${post.first_name} ${post.last_name} -- <fmt:formatDate value="${post.post_time}" pattern="yyyy-MM-dd HH:mm:ss" /></small>
                            </div>
                            <p>${post.body}</p>
                        </div>
                        <hr>
                    </c:forEach>
                    <%-- k dung`
                    <div class="send-message mb-4">
                        <h2>Send Message</h2>
                        <form action="sendMessage" method="post">
                            <div class="mb-3">
                                <label for="recipient" class="form-label">Recipient:</label>
                                <input type="text" class="form-control" id="recipient" name="recipient" required>
                            </div>
                            <div class="mb-3">
                                <label for="message" class="form-label">Message:</label>
                                <textarea class="form-control" id="message" name="message" rows="4" required></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary">Send Message</button>
                        </form>
                    </div>

                    <div class="add-friend mb-4">
                        <h2>Add Friend</h2>
                        <a href="addFriend?userId=123" class="btn btn-secondary">Add Friend</a>
                    </div>
                    --%>
                </main>
                <aside class="col-2 py-3 bg-light friend-list">
                    <h2>List Friends</h2>
                    <ul class="list-group">
                        <li class="list-group-item">Friend 1</li>
                        <li class="list-group-item">Friend 2</li>
                        <li class="list-group-item">Friend 3</li>
                    </ul>
                </aside>
            </div>

            <c:if test="${empty sessionScope.user}">
                <div class="alert alert-warning mt-4">
                    <p>Please <a href="login.jsp">log in</a> to access this page.</p>
                </div>
            </c:if>
        </div>

        <script src="assets/js/bootstrap.min.js"></script>
    </body>
</html>
