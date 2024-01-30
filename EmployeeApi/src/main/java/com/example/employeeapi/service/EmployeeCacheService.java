package com.example.employeeapi.service;

import com.example.employeeapi.dto.EmployeeDTO;
import com.example.employeeapi.mapper.EmployeeMapper;
import com.example.employeeapi.repo.EmployeeDBOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeCacheService {


    @Autowired
    private EmployeeDBOperation employeeDBOperation;
    @Autowired
    private EmployeeFallBackService employeeFallBackService;

    @Cacheable(value = "EmployeeList")
    public List<EmployeeDTO> getEmployee(){
        log.info("Query from cache");
        return employeeFallBackService.getEmployeeDTOList();
    }

    @Cacheable(value = "EmployeeList")
    public List<EmployeeDTO> listEmployeeCache(){
        log.info("Query from cache");
        return employeeDBOperation.listEmployee();
    }

    public List<EmployeeDTO> listEmployeeFallBack(){
        log.info("Redis server is down, fetching from db");
        return employeeDBOperation.listEmployee();
    }


    @CachePut(value = "EmployeeList")
    public void addEmployee(EmployeeDTO employeeDTO) {
//        log.info("addItem [{}, {}, {}]", item.getId(), item.getCategory(), item.getName());
        employeeFallBackService.addEmployee(employeeDTO);
    }
}
