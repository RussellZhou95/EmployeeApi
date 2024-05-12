package com.example.employeeapi.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;
@Slf4j
public class UserFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Initialize User Filter.");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Custom filter is processing the request.");
        log.info("CustomFilter is processing the request.");

        // Continue the filter chain
        filterChain.doFilter(servletRequest, servletResponse);

        // Your custom filter logic after the request has been processed
        System.out.println("Custom filter has processed the response.");
        log.info("CustomFilter has processed the response.");

    }

//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
}
