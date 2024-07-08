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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userDao = new userDAO();
        postDao = new postDAO();
        String action = request.getParameter("action");

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        try {
            if (null != action) {
                switch (action) {
                    case "changeInformation" -> {
                        request.getRequestDispatcher("/WEB-INF/setting.jsp").forward(request, response);
                    }
                    case "changeName" -> {
                        String firstName = request.getParameter("firstName");
                        String lastName = request.getParameter("lastName");
                        user.setFirst_name(firstName);
                        user.setLast_name(lastName);
                        userDao.updateUser(user);
                        request.getRequestDispatcher("/WEB-INF/setting.jsp").forward(request, response);
                    }
                    case "changePassword" -> {
                        String oldPassword = request.getParameter("oldPassword");
                        String newPassword = request.getParameter("newPassword");
                        String confirmNewPassword = request.getParameter("confirmNewPassword");
                        if (user.getPassword().equals(oldPassword) && newPassword.equals(confirmNewPassword)) {
                            user.setPassword(newPassword);
                            userDao.updateUser(user);
                            request.getRequestDispatcher("/WEB-INF/setting.jsp").forward(request, response);
                        }
                    }
                    case "deletePost" -> {
                        int postId = Integer.parseInt(request.getParameter("postId"));
                        postDao.deletePost(postId);
                        request.getRequestDispatcher("setting.jsp").forward(request, response);
                    }
                    case "confirmDelete" -> {
                        int postId = Integer.parseInt(request.getParameter("postId"));
                        postDao.deletePost(postId);
                        request.getRequestDispatcher("setting.jsp").forward(request, response);
                    }
                    default -> {
                    }
                }
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
    }
}
