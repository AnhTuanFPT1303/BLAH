<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Settings</title>
</head>
<body>
    <h1>Settings</h1>
    <%
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>
    <div style="float: left; width: 20%">
        <ul>
            <li><a href="#" onclick="showChangeNameForm()">Change Name</a></li>
            <li><a href="#" onclick="showChangePasswordForm()">Change Password</a></li>
            <li><a href="#" onclick="showDeleteAccountForm()">Delete Account</a></li>
        </ul>
    </div>
    <div style="float: right; width: 80%">
        <h2>My Posts</h2>
        <%
            postDAO postDAO = new postDAO();
            List<Post> myPosts = postDAO.getMyPosts(user.getUser_id());
            for (Post post : myPosts) {
        %>
        <div>
            <p><%= post.getBody() %></p>
            <p><%= post.getPost_time() %></p>
            <a href="#" onclick="deletePost(<%= post.getPost_id() %>)">Delete</a>
        </div>
        <%
            }
        %>
    </div>

    <div id="changeNameForm" style="display: none;">
        <form action="settingServlet" method="post">
            <input type="hidden" name="action" value="changeName">
            <label for="newFirstName">New First Name:</label>
            <input type="text" id="newFirstName" name="newFirstName"><br><br>
            <label for="newLastName">New Last Name:</label>
            <input type="text" id="newLastName" name="newLastName"><br><br>
            <input type="submit" value="Change Name">
        </form>
    </div>

    <div id="changePasswordForm" style="display: none;">
        <form action="settingServlet" method="post">  
    <div id="changePasswordForm" style="display: none;">
        <form action="settingServlet" method="post">
            <input type="hidden" name="action" value="changePassword">
            <label for="oldPassword">Old Password:</label>
            <input type="password" id="oldPassword" name="oldPassword"><br><br>
            <label for="newPassword">New Password:</label>
            <input type="password" id="newPassword" name="newPassword"><br><br>
            <input type="submit" value="Change Password">
        </form>
    </div>

    <div id="deleteAccountForm" style="display: none;">
        <form action="settingServlet" method="post">
            <input type="hidden" name="action" value="deleteAccount">
            <p>Are you sure you want to delete your account?</p>
            <input type="submit" value="Delete Account">
        </form>
    </div>

    <script>
        function showChangeNameForm() {
            document.getElementById("changeNameForm").style.display = "block";
        }

        function showChangePasswordForm() {
            document.getElementById("changePasswordForm").style.display = "block";
        }

        function showDeleteAccountForm() {
            document.getElementById("deleteAccountForm").style.display = "block";
        }

        function deletePost(postId) {
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "settingServlet", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.send("action=deletePost&postId=" + postId);
        }
    </script>
</body>
</html>