package com.example.employeeapi.repo;

import com.example.employeeapi.dto.EmployeeDTO;
import com.example.employeeapi.model.AddEmployeeRs;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;


@Repository
public class EmployeeDBOperation {


    @Value("${mongodb.collection.employee}")
    private String employeeCollectionName;
    private final MongoDatabase mongoDatabase;

    public EmployeeDBOperation(@Value("${mongodb.collection.employee}") String employeeCollectionName,
                               MongoDatabase mongoDatabase) {
        this.employeeCollectionName = employeeCollectionName;
        this.mongoDatabase = mongoDatabase;
    }


    private MongoCollection<EmployeeDTO> getEmployeeCollection(){

        return mongoDatabase.getCollection(employeeCollectionName,EmployeeDTO.class);
    }


    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        var result = getEmployeeCollection().insertOne(employeeDTO);
        employeeDTO.setEmployeeId(result.getInsertedId().asObjectId().getValue());
        return employeeDTO;
    }

    public List<EmployeeDTO> listEmployee() {
        List<EmployeeDTO> employeeDTOList=new ArrayList<>();
        System.out.println(this.getEmployeeCollection().find());
        return this.getEmployeeCollection().find().into(employeeDTOList);
    }

//    public AddEmployeeRs updateEmployee(EmployeeDTO employee) {
//        var idBson=eq("id",employeeId);
//        getEmployeeCollection().updateOne(Updates.)
//    }
}
