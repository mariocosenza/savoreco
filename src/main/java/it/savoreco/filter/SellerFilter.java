package it.savoreco.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;



@WebFilter(value = "/seller")
public class SellerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)  {

    }
}
