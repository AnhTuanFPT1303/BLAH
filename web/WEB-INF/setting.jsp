<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Settings</title>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h3>Settings</h3>
        <form action="settingServlet" method="post">
            <div class="mb-3">
                <label for="firstName" class="form-label">First Name</label>
                <input type="text" class="form-control" id="firstName" name="firstName" value="${user.first_name}">
            </div>
            <div class="mb-3">
                <label for="lastName" class="form-label">Last Name</label>
                <input type="text" class="form-control" id="lastName" name="lastName" value="${user.last_name}">
            </div>
            <button type="submit" class="btn btn-primary" name="action" value="changeName">Change Name</button>
        </form>

        <form action="settingServlet" method="post">
            <div class="mb-3">
                <label for="oldPassword" class="form-label">Old Password</label>
                <input type="password" class="form-control" id="oldPassword" name="oldPassword">
            </div>
            <div class="mb-3">
                <label for="newPassword" class="form-label">New Password</label>
                <input type="password" class="form-control" id="newPassword" name="newPassword">
            </div>
            <div class="mb-3">
                <label for="confirmNewPassword" class="form-label">Confirm New Password</label>
                <input type="password" class="form-control" id="confirmNewPassword" name="confirmNewPassword">
            </div>
            <button type="submit" class="btn btn-primary" name="action" value="changePassword">Change Password</button>
        </form>
    </div>
</body>
</html>