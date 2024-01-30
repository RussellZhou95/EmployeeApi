package com.example.employeeapi.Audit;

import com.example.employeeapi.dto.AuditDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import lombok.RequiredArgsConstructor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Aspect
@RequiredArgsConstructor
public class AuditAspect {

    @Value("${mongodb.collection.audit}")
    private String auditCollection;

    private final MongoDatabase mongoDatabase;

    private MongoCollection<AuditDTO> getAuditCollection(){

        return mongoDatabase.getCollection(auditCollection,AuditDTO.class);
    }
    @Pointcut("execution(* com.example.employeeapi.controller.EmployeeController.create(..))")
    public void addEmployee(){}

    @Pointcut("execution(* com.example.employeeapi.controller.EmployeeController.getEmployeeList(..))")
    public void getEmployeeList(){}

    @Around("addEmployee() || getEmployeeList()")
    public Object auditFunction(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request= ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();

        String endpoint=request.getRequestURI();
        String method=request.getMethod();

        Object response=null;
        response=joinPoint.proceed();

        InputStream inputStream= request.getInputStream();
        String requestBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        ObjectMapper objectMapper=new ObjectMapper();
        String reqResponseBody=objectMapper.writeValueAsString(response);

        AuditDTO auditDTO=new AuditDTO();
        auditDTO.setReqTs(new Date());
        auditDTO.setEndpoint(endpoint);
        auditDTO.setReqMethod(method);
        auditDTO.setReqBody(requestBody);
        auditDTO.setReqResponse(reqResponseBody);
        var result = getAuditCollection().insertOne(auditDTO);
        auditDTO.setAuditId(result.getInsertedId().asObjectId().getValue());
        return response;
    }
}
