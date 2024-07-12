package controller;

import dao.userDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import model.User;

/**
 *
 * @author HELLO
 */
public class loginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet loginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("userEmail");
        String passWord = request.getParameter("passWord");
        boolean status = true;
        request.removeAttribute("msg");

        if (email.trim().isEmpty() || passWord.trim().isEmpty()) {
            status = false;
        }

        if (!status) {
            request.setAttribute("msg", "Re-enter Email & password.");
            request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);
        } else {
            userDAO userDao = new userDAO();
            boolean verify = userDao.login(email, passWord);

            if (verify) {
                try {
                    User user = userDao.getUserByEmail(email);
                    HttpSession session = request.getSession(true);
                    session.setMaxInactiveInterval(1800);
                    session.setAttribute("user", user);
                    session.setAttribute("user_id", user.getUser_id());
                    session.setAttribute("last_name", user.getLast_name());
                    session.setAttribute("first_name", user.getFirst_name());
                    response.sendRedirect("home");
                } catch (SQLException e) {
                    request.setAttribute("msg", "Login Failed.");
                    request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("msg", "Wrong username or password.");
                request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
