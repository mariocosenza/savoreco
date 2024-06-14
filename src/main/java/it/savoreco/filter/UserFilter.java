package it.savoreco.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;


@WebFilter("/user")
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
           /*
            * Todo
            */
    }
}
