<%@page contentType="text/html" pageEncoding="UTF-8" 
         import= "model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Settings</title>
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-4">
            <c:choose>
                <c:when test="${param.action == 'changeName'}">
                    <h3>Change Name</h3>
                    <form action="settingServlet" method="post">
                        <div class="mb-3">
                            <label for="firstName" class="form-label">First Name</label>
                            <input type="text" class="form-control" id="firstName" name="firstName" required>
                        </div>
                        <div class="mb-3">
                            <label for="lastName" class="form-label">Last Name</label>
                            <input type="text" class="form-control" id="lastName" name="lastName" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Change Name</button>
                    </form>
                </c:when>
                <c:when test="${param.action == 'changePassword'}">
                    <h3>Change Password</h3>
                    <form action="settingServlet" method="post">
                        <div class="mb-3">
                            <label for="oldPassword" class="form-label">Old Password</label>
                            <input type="password" class="form-control" id="oldPassword" name="oldPassword" required>
                        </div>
                        <div class="mb-3">
                            <label for="newPassword" class="form-label">New Password</label>
                            <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                        </div>
                        <div class="mb-3">
                            <label for="confirmNewPassword" class="form-label">Confirm New Password</label>
                            <input type="password" class="form-control" id="confirmNewPassword" name="confirmNewPassword" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Change Password</button>
                    </form>
                </c:when>
                <c:when test="${param.action == 'deletePost'}">
                    <h3>Delete Post</h3>
                    <c:forEach items="${posts}" var="post">
                        <div class="post mb-4">
                            <p>${post.body}</p>
                            <form action="settingServlet" method="post" style="display: inline;">
                                <input type="hidden" name="postId" value="${post.post_id}">
                                <button type="submit" name="action" value="confirmDelete" class="btn btn-danger">Delete</button>
                            </form>
                        </div>
                    </c:forEach>
                </c:when>
            </c:choose>
        </div>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.min.js"></script>
    </body>
</html> 