package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import dao.userDAO;
import dao.postDAO;
import model.User;
import model.Post;
import java.util.List;

public class settingServlet extends HttpServlet {
    private userDAO userDao;
    private postDAO postDao;

    public void init() {
        userDao = new userDAO();
        postDao = new postDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        try {
            if ("changeName".equals(action)) {
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                user.setFirst_name(firstName);
                user.setLast_name(lastName);
                userDao.updateUser(user);
                request.getRequestDispatcher("settings.jsp?action=changeName").forward(request, response);
            } else if ("changePassword".equals(action)) {
                String oldPassword = request.getParameter("oldPassword");
                String newPassword = request.getParameter("newPassword");
                String confirmNewPassword = request.getParameter("confirmNewPassword");
                if (user.getPassword().equals(oldPassword) && newPassword.equals(confirmNewPassword)) {
                    user.setPassword(newPassword);
                    userDao.updateUser(user);
                    request.getRequestDispatcher("settings.jsp?action=changePassword").forward(request, response);
                }
            } else if ("deletePost".equals(action)) {
                int postId = Integer.parseInt(request.getParameter("postId"));
                postDao.deletePost(postId);
                request.getRequestDispatcher("settings.jsp?action=deletePost").forward(request, response);
            } else if ("confirmDelete".equals(action)) {
                int postId = Integer.parseInt(request.getParameter("postId"));
                postDao.deletePost(postId);
                request.getRequestDispatcher("settings.jsp?action=deletePost").forward(request, response);
            }
        } catch (Exception e) {
            // log the error and exception
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        try {
            if ("deletePost".equals(action)) {
                List<Post> posts = postDao.getMyPosts(user.getUser_id());
                request.setAttribute("posts", posts);
                request.getRequestDispatcher("settings.jsp?action=deletePost").forward(request, response);
            } else {
                request.getRequestDispatcher("settings.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // log the error and exception
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}