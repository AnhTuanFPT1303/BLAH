package controller;

import dao.PostDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.post;
import model.User;

public class userpageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("login.jsp"); 
            return;
        }
        int userId = user.getUser_id(); // Get the userId from the session
        String title = req.getParameter("title");
        String body = req.getParameter("body"); 

        PostDAO postDAO = new PostDAO();
        try {
            postDAO.createPost(title, body, userId);
        } catch (Exception ex) {
            Logger.getLogger(userpageServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<post> posts = null;
        try {
            posts = postDAO.getPostsByUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("posts", posts); // Set the posts list as an attribute
        req.getRequestDispatcher("/WEB-INF/userpage.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect("login.jsp"); 
            return;
        }
        int userId = (int) req.getSession().getAttribute("user_id"); // Get the userId from the session

        PostDAO postDAO = new PostDAO();
        List<post> posts = null;
        try {
            posts = postDAO.getPostsByUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("user", user); // Set the user object as an attribute
        req.setAttribute("posts", posts); // Set the posts list as an attribute
        req.getRequestDispatcher("/WEB-INF/userpage.jsp").forward(req, resp);
    }
}