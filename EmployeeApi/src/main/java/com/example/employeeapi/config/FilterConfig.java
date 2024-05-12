package com.example.employeeapi.config;


import com.example.employeeapi.filter.EmployeeFilter;
import com.example.employeeapi.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;
import java.util.logging.Filter;

@Configuration
public class FilterConfig {

    private static final String[] URL_PATTERNS={"/employeeApi","/users"};

    @Bean
    public FilterRegistrationBean<EmployeeFilter> employeeFilterFilterRegistrationBean(){
        FilterRegistrationBean<EmployeeFilter> employeeFilterBean=new FilterRegistrationBean<>();
        EmployeeFilter employeeFilter=new EmployeeFilter();
        employeeFilterBean.setAsyncSupported(true);
        employeeFilterBean.setFilter(employeeFilter);
        employeeFilterBean.addUrlPatterns(URL_PATTERNS);
        employeeFilterBean.setOrder(1);
        return employeeFilterBean;
    }


    @Bean
    public FilterRegistrationBean<UserFilter> userFilterFilterRegistrationBean(){
        FilterRegistrationBean<UserFilter> userFilterBean=new FilterRegistrationBean<>();
        UserFilter userFilter=new UserFilter();
        userFilterBean.setAsyncSupported(true);
        userFilterBean.setFilter(userFilter);
        userFilterBean.addUrlPatterns(URL_PATTERNS);
        userFilterBean.setOrder(2);
        return userFilterBean;
    }


}
