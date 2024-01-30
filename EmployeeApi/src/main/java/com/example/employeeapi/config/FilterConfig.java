package com.example.employeeapi.config;


import com.example.employeeapi.filter.EmployeeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;
import java.util.logging.Filter;

@Configuration
public class FilterConfig {

    private static final String[] URL_PATTERNS={"/v1/employeeApi/*"};

    @Bean
    public FilterRegistrationBean<EmployeeFilter> employeeFilterFilterRegistrationBean(){
        FilterRegistrationBean<EmployeeFilter> employeeFilterBean=new FilterRegistrationBean<>();
        EmployeeFilter employeeFilter=new EmployeeFilter();
        employeeFilterBean.setAsyncSupported(true);
        employeeFilterBean.setFilter(employeeFilter);
        employeeFilterBean.addUrlPatterns(URL_PATTERNS);
        System.out.println("Initialize the Employee Filter");
        return employeeFilterBean;
    }





}
