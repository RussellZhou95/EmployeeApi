package com.example.employeeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO implements Serializable {

    @BsonProperty("_id")
    @BsonId
    private ObjectId employeeId;

    private String name;
    private int age;
    private String title;

    public EmployeeDTO(String name, int age, String title) {
        this.name = name;
        this.age = age;
        this.title = title;
    }
}
