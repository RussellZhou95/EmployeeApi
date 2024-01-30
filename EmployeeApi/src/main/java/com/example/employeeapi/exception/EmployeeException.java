package com.example.employeeapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class EmployeeException extends RuntimeException{

    private final HttpStatus httpStatus;

    private final String errorCode;
    private final String description;
    private final String severity;
}
