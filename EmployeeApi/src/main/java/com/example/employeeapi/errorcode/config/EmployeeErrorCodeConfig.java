package com.example.employeeapi.errorcode.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Data
public class EmployeeErrorCodeConfig {

    @Value("#{${employee.errors}}")
    private Map<String,String> employeeErrors;
}
