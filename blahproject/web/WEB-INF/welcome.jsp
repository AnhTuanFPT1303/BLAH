<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="assets/css/login.css">
    <title>Login Page</title>
</head>
<body>
    <div class="left-section col-md-6">
        <div class="logo">
            <h1>BLAH</h1>
        </div>
        <div class="welcome-text">
            <h2>Keep yourself up-to-date</h2>
            <h2>What's going on now</h2>
            <p>Welcome back! Please login to your account.</p>
        </div>
        <form class= "input-form" method="post" action="/blahproject/loginServlet">
            <div class="input-group">
                <label for="email">Email Address</label>
                <input type="text" id="email" name="userEmail">
            </div>
            <div class="input-group">
                <label for="pass">Password</label>
                <input type="password" id="pass" name="passWord">
            </div>
            <div class="actions">
                <div class="checkbox">
                    <input type="checkbox" id="rememberMe" name="rememberMe">
                    <label for="rememberMe">Remember Me</label>
                </div>
                <a href="#" class="forgot-password">Forgot Password?</a>
            </div>
            <div class="buttons">
                <button type="submit" name="action" value="login">Login</button>
                <button type="submit" name="action" value="redirectSignup" class="signup-button">Sign Up</button>
            </div>
            <div class="social-login">
                <p>Or login with</p>
                <a href="#" class="social-login-button">Facebook</a>
                <a href="#" class="social-login-button">LinkedIn</a>
                <a href="#" class="social-login-button">Google</a>
            </div>
        </form>
    </div>
    <div class="right-section col-md-6">
        <nav class="nav">
            <a href="#" class="active">Home</a>
            <a href="#">About us</a>
            <a href="#">Blog</a>
            <a href="#">Pricing</a>
        </nav>
        <div class="image">
            <img src="assets/images/banner.png" alt="A man is riding a bicycle">
        </div>
    </div>
    <script src="assets/js/bootstrap.min.js"></script>
</body>
</html>