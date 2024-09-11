/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.userDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import jakarta.servlet.http.HttpSession;
import util.SmtpProtocol;

/**
 *
 * @author HELLO
 */
public class VerifyServlet extends HttpServlet {

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
            out.println("<title>Servlet VerifyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyServlet at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String act = request.getParameter("action");
        HttpSession session = request.getSession(false);
        Integer otp = (Integer) session.getAttribute("otpCode");
        User user = (User) session.getAttribute("user");
        if ("resend".equals(act)) {
            String email = (String) session.getAttribute("email");
            SmtpProtocol smtpProtocol = new SmtpProtocol();
            otp = smtpProtocol.sendMail(email);
            session.setAttribute("otpCode", otp);
            session.setAttribute("email", email);
            session.setAttribute("user", user);
            request.getRequestDispatcher("WEB-INF/verify.jsp").forward(request, response);
        }
        if ("forgot".equals(act)) {
            String email = request.getParameter("email");
            SmtpProtocol smtpProtocol = new SmtpProtocol();
            otp = smtpProtocol.sendMail(email);
            session.setAttribute("otpCode", otp);
            session.setAttribute("email", email);
            session.setAttribute("action", "changePass");
            request.getRequestDispatcher("WEB-INF/verify.jsp").forward(request, response);
        }

        int inputOtp = Integer.parseInt(request.getParameter("otp-code"));
        if (otp == inputOtp) {
            session.removeAttribute("otpCode");
            session.removeAttribute("email");
            userDAO userDao = new userDAO();
            String result = userDao.register(user);
            request.setAttribute("msg", result);
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);

        } else {
            request.getRequestDispatcher("WEB-INF/verify.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
