package com.example.employeeapi.mapper;

import com.example.employeeapi.dto.EmployeeDTO;
import com.example.employeeapi.model.AddEmployeeRq;
import com.example.employeeapi.model.AddEmployeeRs;
import com.example.employeeapi.model.AddEmployeeRsEmployee;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EmployeeMapper {


    public EmployeeDTO mapEmployee(AddEmployeeRq employee) {

        EmployeeDTO employeeDTO=new EmployeeDTO();
        if(employee.getName()!=null){
            employeeDTO.setName(employee.getName());
        }
        if(employee.getAge()!=null){
            employeeDTO.setAge(employee.getAge());
        }
        if(employee.getTitle()!=null){
            employeeDTO.setTitle(employee.getTitle());
        }
        return employeeDTO;
    }

    public AddEmployeeRs mapToEmployeeResponse(EmployeeDTO employeeDTO1) {
        AddEmployeeRs rs=new AddEmployeeRs();
        AddEmployeeRsEmployee rsEmployee=new AddEmployeeRsEmployee();
        rsEmployee.setEmployeeId(employeeDTO1.getEmployeeId().toString());
        rs.setEmployee(rsEmployee);
        return rs;
    }
}
