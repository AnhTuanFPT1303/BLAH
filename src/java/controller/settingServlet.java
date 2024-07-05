package controller;

import dao.postDAO;
import dao.userDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

import java.io.IOException;
import java.sql.SQLException;

public class settingServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            switch (action) {
                case "changeName":
                    String newFirstName = request.getParameter("newFirstName");
                    String newLastName = request.getParameter("newLastName");
                    user.setFirst_name(newFirstName);
                    user.setLast_name(newLastName);
                    userDAO userDAO = new userDAO();
                    userDAO.updateUserName(user.getUser_id(), newFirstName, newLastName);
                    session.setAttribute("user", user);
                    response.sendRedirect("setting.jsp");
                    break;
                case "changePassword":
                    String oldPassword = request.getParameter("oldPassword");
                    String newPassword = request.getParameter("newPassword");
                    if (user.getPassword().equals(oldPassword)) {
                        user.setPassword(newPassword);
                        userDAO = new userDAO();
                        userDAO.updatePassword(user.getUser_id(), newPassword);
                        session.setAttribute("user", user);
                        response.sendRedirect("login.jsp");
                    } else {
                        response.sendRedirect("setting.jsp?error=password");
                    }
                    break;
                case "deleteAccount":
                    userDAO = new userDAO();
                    userDAO.deleteUser(user.getUser_id());
                    session.invalidate();
                    response.sendRedirect("login.jsp");
                    break;
                case "deletePost":
                    int postId = Integer.parseInt(request.getParameter("postId"));
                    postDAO postDAO = new postDAO();
                    postDAO.deletePost(postId);
                    response.sendRedirect("setting.jsp");
                    break;
                default:
                    response.sendRedirect("setting.jsp");
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred: " + e.getMessage());
            response.sendRedirect("error.jsp");
        } catch (Exception e) {
            System.out.println("An unknown error occurred: " + e.getMessage());
            response.sendRedirect("error.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("setting.jsp");
    }
}