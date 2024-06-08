package com.example.pbl4.filter;

import com.example.pbl4.config.Rol;
import com.example.pbl4.user.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/type/*", "/type/*/*", "/preProcessedMaterial/*", "/preProcessedMaterial/*/*", "/processedMaterial/*", "/processedMaterial/*/*",
"/task/*", "/task/*/*", "/sale/*", "/sale/*/*", "/purchase/*", "/purchase/*/*", "/section/*", "/section/*/*", "/supplier/*", "/supplier/*/*"})
public class RoleBasedCRUDFilter implements Filter {

    public RoleBasedCRUDFilter() {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(true);

        User user = (User) session.getAttribute("userLogin");
        String path = request.getServletPath();

        if (user == null) {
            // No user logged in
            redirectToErrorPage(request, response, HttpServletResponse.SC_UNAUTHORIZED, "You need to login to access this resource.");
            return;
        }

        if (user.getRol() == Rol.CUSTOMER) {
            // Customers should not have access to any employee endpoints
            redirectToErrorPage(request, response, HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this resource.");
            return;
        }

        // Admin can access all endpoints, and Employee can view/update
        chain.doFilter(req, res);
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

    private void redirectToErrorPage(HttpServletRequest request, HttpServletResponse response, int statusCode, String errorMessage)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("error", errorMessage);
        session.setAttribute("errorCode", statusCode);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/templates/error");
        response.setStatus(statusCode);
        dispatcher.forward(request, response);
    }
}
