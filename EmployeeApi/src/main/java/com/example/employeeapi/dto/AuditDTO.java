package com.example.employeeapi.dto;

import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.Map;

@Data
public class AuditDTO {

    @BsonProperty("_id")
    @BsonId
    private ObjectId auditId;
    private String endpoint;
    private String reqMethod;
    private Date reqTs;
    private Map<String,String> reqHeaders;

    private String reqBody;
    private String reqResponse;



}
