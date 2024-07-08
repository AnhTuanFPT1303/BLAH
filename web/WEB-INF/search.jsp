<%-- 
    Document   : search
    Created on : Jul 5, 2024, 11:34:52 PM
    Author     : HELLO
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, model.User, dao.userDAO" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Search Result</title>
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="assets/css/search.css">
    </head>
    <body>
        <header id="header">
            <nav class="navbar custom-navbar">
                <div class="container-fluid d-flex align-items-center">
                    <a class="navbar-brand text-primary" href="/blahproject/login" style="font-weight: bold">BLAH</a>
                    <form class="d-flex ms-2 flex-grow-1" method="get" action="/blahproject/searchServlet">
                        <input class="form-control" name="search-name" type="search" placeholder="Finding in BLAH" aria-label="Search">
                        <input type="submit" value="Submit">
                    </form>
                    <form method="post" action="/blahproject/logout">
                        <button type="submit" class="navbar-brand text-primary log-out" style="font-weight: bold">Log out</a>
                    </form>
                </div>
            </nav>
        </header>
        <div class="container-fluid">
            <div class="row all-post">
                <nav class="col-2 py-3 bg-light">
                    <div class="profile-section mb-3 d-flex align-items-center">
                        <a href="userpageServlet" class="d-flex align-items-center text-decoration-none text-dark">
                            <img src="${sessionScope.user['profile_pic']}" class="img-fluid rounded-circle avatar">
                            <p class="mb-0 ms-2 ava-name">${sessionScope.user['first_name']} ${sessionScope.user['last_name']}</p>
                        </a>
                    </div>
                    <div class="chat-box mb-3">
                        <h5>Chat box</h5>   
                    </div>
                </nav>

                <main class="col-8">
                    <h1 class="mt-3 text-primary home-logo">HOME</h1>
                    <hr>
                    <c:forEach var="user" items="${userList}">
                        <div class="post mb-4 d-flex align-items-center" style="overflow-wrap: break-word">
                            <a href="userpageServlet?userId=${user.user_id}">
                            <img src="${user.profile_pic}" alt="avatar picture" class="img-thumbnail mr-3" style="width: 50px; height: 50px; object-fit: cover;">
                            </a>
                            <a href="userpageServlet?userId=${user.user_id}" style="margin-left: 5px">${user.first_name} ${user.last_name}</a>
                        </div>
                        <hr>
                    </c:forEach>
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
        </div>
                        <div>
                            <c:choose> 
                                <c:when test = "1==1">
                                    <h1> hello </h1>
                                </c:when>
                            </c:choose>
                        </div>
        <script src="assets/js/bootstrap.min.js"></script>
    </body>
</html>
