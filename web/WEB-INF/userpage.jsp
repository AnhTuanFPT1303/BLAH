<%@page contentType="text/html" pageEncoding="UTF-8" import="model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Page</title>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/home.css">
    <style>
        /* Add some decorative elements */
        body {
            background-image: linear-gradient(to bottom, #f8f9fa, #fff);
        }
        .custom-navbar {
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .profile-section {
            background-color: #f7f7f7;
            padding: 10px;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .post {
            background-color: #fff;
            padding: 15px;
            border-radius: 10px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .post-header {
            color: #337ab7;
        }
    </style>
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
                <div class="profile-section mb-3 text-center">
                    <a href="userpageServlet" class="text-decoration-none text-dark">
                        <img src="${sessionScope.user['profile_pic']}" class="img-fluid rounded-circle avatar">
                    </a>
    <p style="text-align: left;">Name: ${user.first_name} ${user.last_name}</p>
                </div>
            </nav>
            <main class="col-8">
                <h1 class="mt-3 text-primary home-logo">Welcome, ${user.first_name} ${user.last_name}!</h1>
                <form action="userpageServlet" method="post" class="mb-4 post-method">
                    <div class="mb-3">
                        <textarea class="form-control" id="body" name="body" rows="4" placeholder="What ya thinking" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary" style="padding: 5px 25px">Post</button>
                </form>
                <hr>
                <h2>Yours Timeline</h2>
                <c:forEach items="${posts}" var="post">
                    <div class="post mb-4" style="overflow-wrap: break-word; border: 1px solid #ddd; padding: 10px; border-radius: 10px;">
                        <div class="post-header">
                            <small>${post.first_name} ${post.last_name} -- <fmt:formatDate value="${post.post_time}" pattern="yyyy-MM-dd HH:mm:ss" /></small>
                        </div>
                        <p style="font-size: 14px;">${post.body}</p>
                    </div>
                    <hr>
                </c:forEach>
                <form action="logout" method="post">
                    
                </form>
            </main>
            <aside class="col-2 py-3 bg-light friend-list">
                <h2>Yours Friends</h2>
                <ul class="list-group">
                    <li class="list-group-item">Friend 1</li>
                    <li class="list-group-item">Friend 2</li>
                    <li class="list-group-item">Friend 3</li>
                </ul>
            </aside>
        </div>
    </div>
  <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/jquery.min.js"></script>
</body>
</html>
