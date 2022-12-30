package com.lpl.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {"/page/manger/*","/page/bangumi/*","/bangumiServlet/*"})
public class Filter implements jakarta.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        jakarta.servlet.Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        Object user = request.getSession().getAttribute("user");
        if (user!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            response.sendRedirect(request.getContextPath()+"/page/user/login.html");
        }
    }

    @Override
    public void destroy() {
        jakarta.servlet.Filter.super.destroy();
    }
}
