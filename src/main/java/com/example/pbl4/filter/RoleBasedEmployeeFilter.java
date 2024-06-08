package com.example.pbl4.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.example.pbl4.user.User;
import com.example.pbl4.config.Rol;

import java.io.IOException;

/**
 * Servlet Filter implementation class RoleBasedEmployeeFilter
 */

@WebFilter(urlPatterns = {"/employee/*", "/employee/*/*", "/customer/*", "/customer/*/*"})
public class RoleBasedEmployeeFilter implements Filter {

    public RoleBasedEmployeeFilter() {
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

        if (user.getRol() == Rol.EMPLOYEE) {
            if (path.equals("/employee/create") || path.startsWith("/employee/delete") || path.startsWith("/employee/form")) {
                // Employees should not be able to create or delete employees
                redirectToErrorPage(request, response, HttpServletResponse.SC_FORBIDDEN, "You do not have permission to perform this action.");
                return;
            }
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/error.jsp");
        response.setStatus(statusCode);
        dispatcher.forward(request, response);
    }
}

