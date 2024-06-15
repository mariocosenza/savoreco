package it.savoreco.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(value = "/moderator")
public class ModeratorFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)  {
        if (servletRequest instanceof HttpServletRequest httpRequest && servletResponse instanceof HttpServletResponse httpResponse) {
            var session = httpRequest.getSession(false);
            try {
                if (session != null) {
                    if (session.getAttribute("logged").equals("moderator")) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    }
                }
                httpResponse.sendRedirect("/home");
            } catch (IOException | ServletException e) {
                httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    }

}
