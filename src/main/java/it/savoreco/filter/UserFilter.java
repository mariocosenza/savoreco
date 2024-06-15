package it.savoreco.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebFilter("/user/*")
public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {

        if (request instanceof HttpServletRequest httpRequest && response instanceof HttpServletResponse httpResponse) {
            var session = httpRequest.getSession(false);
            try {
                if (session != null) {
                    if (session.getAttribute("logged") != null && session.getAttribute("logged").equals("user")) {
                        chain.doFilter(request, response);
                    }
                    else {
                        httpResponse.sendRedirect("/home");
                    }
                } else {
                    httpResponse.sendRedirect("/home");
                }
            } catch (IOException | ServletException e) {
                httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }

}

