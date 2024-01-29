package com.example.employeeapi.service;

import com.example.employeeapi.dto.EmployeeDTO;
import com.example.employeeapi.mapper.EmployeeMapper;
import com.example.employeeapi.model.AddEmployeeRq;
import com.example.employeeapi.model.AddEmployeeRs;
import com.example.employeeapi.model.AddEmployeeRsEmployee;
import com.example.employeeapi.repo.EmployeeDBOperation;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class EmployeeService {


    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeDBOperation employeeDBOperation;
    @Autowired
    private EmployeeCacheService employeeCacheService;
    @Autowired
    private EmployeeFallBackService employeeFallBackService;

//    public List<Employee> getEmployees(){
//        return employeeRepo.findAll();
//    }
//
//    public Employee getEmployeeById(long id){
//        if(!employeeRepo.findById(id).isPresent()) {
//            throw new EmployeeNotFoundException("Employee with id "+id+" was not found.");
//        }
//        return employeeRepo.findById(id).get();
//    }

    public AddEmployeeRs create(AddEmployeeRq employee){

        EmployeeDTO employeeDTO=employeeMapper.mapEmployee(employee);
        EmployeeDTO employeeDTO1;
        employeeDTO1=employeeDBOperation.createEmployee(employeeDTO);



        return employeeMapper.mapToEmployeeResponse(employeeDTO1);

    }

//    @Cacheable(value = "EmployeeList")
    public List<EmployeeDTO> listEmployee() {
        System.out.println("Calling from Employee db");
        List<EmployeeDTO> employeeDTOList;
        try{
//            employeeDTOList=employeeCacheService.getEmployee();
            employeeDTOList=employeeCacheService.listEmployeeCache();
        }catch (RedisConnectionFailureException ex){
            log.error("Error while fetching item employee");
//            employeeDTOList=employeeFallBackService.getEmployeeDTOList();
            employeeDTOList=employeeCacheService.listEmployeeFallBack();
        }
        return employeeDTOList;
    }

    @Cacheable(value = "EmployeeList")
    public List<EmployeeDTO> listEmployeeCache(){
        return employeeDBOperation.listEmployee();
    }

    private List<EmployeeDTO> listEmployeeFallback() {
        log.info("Fallback method: Fetching employee items from alternative source.");
        // Implement the logic to fetch the data from an alternative source (e.g., database)
        List<EmployeeDTO> employeeDTOList= Arrays.asList(
                new EmployeeDTO(new ObjectId("6597289ae0d1ed061eb0fbca"),"Russell",28,"Developer"),
                new EmployeeDTO(new ObjectId("659729b5ef03be205a405d43"),"Kailey",26,"Teacher")
        );
        return employeeDTOList;
    }

//    public AddEmployeeRs updateEmployee(AddEmployeeRq employee) {
//        EmployeeDTO employeeDTO=employeeMapper.mapEmployee(employee);
//        EmployeeDTO employeeDTO1;
//        employeeDTO1=employeeDBOperation.createEmployee(employeeDTO);
//        return employeeDBOperation.updateEmployee(employeeDTO1);
//    }

//    public void updateEmployee(Employee employee) {
//        employeeRepo.save(employee);
//    }
//
//    public void deleteById(int id) {
//        employeeRepo.deleteById((long) id);
//    }
}
