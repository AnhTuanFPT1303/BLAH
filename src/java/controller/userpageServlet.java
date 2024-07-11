package controller;

import dao.postDAO;
import dao.userDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Post;
import model.User;


@MultipartConfig
public class userpageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        
        Part file = req.getPart("profile_pic");
            String profile_pic = file.getSubmittedFileName();
            String uploadPath = "D:/fpt/prj301/project/BLAH_L11/BLAH/web/assets/profile_avt/" + profile_pic;
            try {
                FileOutputStream fos = new FileOutputStream(uploadPath);
                InputStream is = file.getInputStream();

                byte[] data = new byte[is.available()];
                is.read(data);
                fos.write(data);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            if (!profile_pic.isEmpty()) {
                //user.setUser_id(user.getUser_id());
                user.setProfile_pic(profile_pic);
                
                userDAO UserDAO = new userDAO();
                try {
                    UserDAO.changeAvatar(user);
                    session.setAttribute("user", user);
                } catch (Exception e) {
                    e.printStackTrace();
                    req.setAttribute("errorMessage", "Error");
                }
            }

            
            
        int userId = user.getUser_id(); // Get the userId from the session
        String body = req.getParameter("body"); // You didn't have a title field in your form, so I removed it

        postDAO postDAO = new postDAO();
        try {
            postDAO.addPost(new Post(0, userId, body, null)); // Create a new Post object and add it
        } catch (Exception ex) {
            Logger.getLogger(userpageServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Post> posts = null;
        try {
            posts = postDAO.getMyPosts(userId); // Get posts of the current user
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("posts", posts); // Set the posts list as an attribute
        req.getRequestDispatcher("/WEB-INF/userpage.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String userIdParam = req.getParameter("userId");
        int userId = currentUser.getUser_id();

        if (userIdParam != null && !userIdParam.isEmpty()) {
            try {
                userId = Integer.parseInt(userIdParam);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        userDAO userDAO = new userDAO();
        User user = new User();
        try {
            user = userDAO.getUserById(userId); // Get user details by userId
        } catch (SQLException ex) {
            Logger.getLogger(userpageServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (user == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        postDAO postDAO = new postDAO();
        List<Post> posts = null;
        try {
            posts = postDAO.getMyPosts(user.getUser_id()); // Get posts of the specified user
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("user", user); // Set the user object as an attribute
        req.setAttribute("posts", posts); // Set the posts list as an attribute
        req.getRequestDispatcher("/WEB-INF/userpage.jsp").forward(req, resp);
    }
}
