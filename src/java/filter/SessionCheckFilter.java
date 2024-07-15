/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SessionCheckFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        HttpSession session = httpRequest.getSession(false);
        
        String loginURI = "/login";
        String contextPath = httpRequest.getContextPath();
        
        boolean isLoginRequest = httpRequest.getRequestURI().endsWith(loginURI);
        boolean isResourceRequest = httpRequest.getRequestURI().startsWith(contextPath + "/assets/");
        boolean isWelcomeRequest = httpRequest.getRequestURI().endsWith("welcome.jsp");
        
        if (isLoginRequest || isResourceRequest || isWelcomeRequest || (session != null && session.getAttribute("user") != null)) {
            // Allow access to login page, resources, welcome page, or if user is logged in
            chain.doFilter(request, response);
        } else {
            // Session is null or user attribute is null, redirect to login page
            httpResponse.sendRedirect(contextPath + loginURI);
        }
    }

    @Override
    public void destroy() {
    }
}