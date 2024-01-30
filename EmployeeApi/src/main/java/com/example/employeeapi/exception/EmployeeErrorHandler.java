package com.example.employeeapi.exception;

import com.example.employeeapi.model.Status;
import com.example.employeeapi.model.StatusStatus;
import com.example.employeeapi.model.StatusStatusAdditionalStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class EmployeeErrorHandler {

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<Object> handleEmployeeException(EmployeeException ex){
//        var additionalSatatus=constructAdditionalStatus(ex.getHttpStatus(),Integer.parseInt(ex.getErrorCode()),ex.getDescription(),StatusStatusAdditionalStatus.s)

        StatusStatusAdditionalStatus additionalStatus=new StatusStatusAdditionalStatus();
        additionalStatus.setStatusCode(ex.getErrorCode());
        additionalStatus.setServerStatusCode(String.valueOf(ex.getHttpStatus().value()));
        additionalStatus.setSeverity(StatusStatusAdditionalStatus.SeverityEnum.ERROR);
        additionalStatus.setStatusDesc(ex.getDescription());

        StatusStatus statusStatus=new StatusStatus();
        statusStatus.setServerStatusCode(String.valueOf(ex.getHttpStatus().value()));
        statusStatus.setSeverity(StatusStatus.SeverityEnum.ERROR);
        List<StatusStatusAdditionalStatus> additionalStatusList=new ArrayList<>();
        additionalStatusList.add(additionalStatus);
        statusStatus.setAdditionalStatus(additionalStatusList);

        Status status1=new Status();
        status1.setStatus(statusStatus);

        return new ResponseEntity<>(status1,HttpStatus.BAD_REQUEST);
    }
}
