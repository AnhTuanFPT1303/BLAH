/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.sun.jdi.connect.spi.Connection;
import dao.postDAO;
import dao.userDAO;
import model.Post;
import model.User;
import java.sql.SQLException;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author bim26
 */
public class postServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet postServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet postServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user_id") != null) {
            List<Post> posts = postDAO.getAllPosts();
            request.setAttribute("posts", posts);
            request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
        } else {
            response.sendRedirect("loginServlet");
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurse
     * @throws IOException if an I/O error occurs
     */
    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user_id") != null) {
            int user_id = (int) session.getAttribute("user_id");
            String body = request.getParameter("body");
            Post post = new Post();
            post.setUser_id(user_id);
            post.setBody(body);
            postDAO postDao = new postDAO();
            postDao.addPost(post);
            response.sendRedirect("post");
        } else {
            response.sendRedirect("loginServlet");
        }
    }
    /* trong trường hợp muốn thử nhập tay user_id, tạo thêm cái input bên trang jsp r cho nhập giá trị vào user_id là đc :D
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String userIdStr = request.getParameter("userId");
//        String body = request.getParameter("body");
//
//        if (userIdStr != null && body != null && !body.isEmpty()) {
//            int userId = Integer.parseInt(userIdStr);
//            Post post = new Post(userId, body);
//
//            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdb", "username", "password")) {
//                PostDAO postDAO = new PostDAO(connection);
//                postDAO.addPost(post);
//                response.sendRedirect("post.jsp?status=success");
//            } catch (SQLException e) {
//                e.printStackTrace();
//                response.sendRedirect("post.jsp?status=error");
//            }
//        } else {
//            response.sendRedirect("post.jsp?status=error");
//        }
//    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
