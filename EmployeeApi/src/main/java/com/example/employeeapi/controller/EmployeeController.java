package com.example.employeeapi.controller;


import com.example.employeeapi.dto.EmployeeDTO;
import com.example.employeeapi.errorcode.config.EmployeeErrorCodeConfig;
import com.example.employeeapi.exception.EmployeeException;
import com.example.employeeapi.model.AddEmployeeRq;
import com.example.employeeapi.model.AddEmployeeRs;
import com.example.employeeapi.service.EmployeeCacheService;
import com.example.employeeapi.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/employeeApi")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeErrorCodeConfig employeeErrorCodeConfig;
    private final EmployeeCacheService employeeCacheService;

    //    @Value("${message}")
//    private String message;
    @Value("${info.foo}")
    private String foo;
    @Value("${bank.name}")
    private String bankName;

//    @Value("${spring.cloud.config.uri}")
//    private String uri;
//    @Value("${spring.cloud.config.profile}")
//    private String profile;
//    @Value("${mongodb.collection.product}")
//    private String productCollectionName;


//    @GetMapping("/employee")
//    public List<Employee> get() {
//        log.info("Get all the employees");
//        return employeeService.getEmployees();
//    }

    /**
     * Get all employee
     * @return
     */
    @GetMapping
    public List<EmployeeDTO> getEmployeeList() {
        log.info("Get one specific employee");
//        System.out.println(message);
        System.out.println(foo);
        System.out.println(bankName);
//        System.out.println(productCollectionName);
        return employeeService.listEmployee();
    }


//    @GetMapping("/{id}")
//    public Employee getById(@PathVariable long id){
//        log.info("Get one specific employee");
//        return employeeService.getEmployeeById(id);
//    }

    @PostMapping
    public AddEmployeeRs create(@RequestBody AddEmployeeRq employee) {

        validateEmployee(employee);
        String uri = "https://gitlab.com/russellproject/EmployeeApi";
        String label = "develop";
        String profile = "dev";

        RestTemplate restTemplate = new RestTemplate();
        Resource forObject = restTemplate.getForObject(UriComponentsBuilder.fromUriString(uri).build().toUri(), Resource.class);

        log.info("Employee was created");
        if (employee.getAge() <= 20) {
            throw new EmployeeException(HttpStatus.BAD_REQUEST, employeeErrorCodeConfig.getEmployeeErrors().get("10200"), "Employee is too young to be hired", "Error");
        }
        AddEmployeeRs rs = employeeService.create(employee);

        return rs;
    }




    private void validateEmployee(AddEmployeeRq employee) {

        if(employee.getName()!=null && employee.getName().length()>10){
            throw new EmployeeException(HttpStatus.BAD_REQUEST,"10201",employeeErrorCodeConfig.getEmployeeErrors().get("10201"),"Error");
        }
        if(employee.getTitle()!=null && employee.getTitle().startsWith("A")){
            throw new EmployeeException(HttpStatus.BAD_REQUEST,"10202",employeeErrorCodeConfig.getEmployeeErrors().get("10202"),"Error");

        }
    }

//    @PutMapping
//    public void update(@RequestBody AddEmployeeRq employee){
//        log.info("Employee was updated");
//        employeeService.updateEmployee(employee);
//    }

//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable int id){
//        employeeService.deleteById(id);
//    }

}
