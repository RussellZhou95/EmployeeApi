package com.example.employeeapi.service;

import com.example.employeeapi.dto.EmployeeDTO;
import com.example.employeeapi.mapper.EmployeeMapper;
import com.example.employeeapi.model.AddEmployeeRq;
import com.example.employeeapi.model.AddEmployeeRs;
import com.example.employeeapi.repo.EmployeeDBOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeFallBackService {

    private List<EmployeeDTO> employeeDTOList=Arrays.asList(
            new EmployeeDTO("Russell",28,"Developer"),
            new EmployeeDTO("Kailey",26,"Teacher")
    );

    public EmployeeFallBackService(List<EmployeeDTO> employeeDTOList) {
        this.employeeDTOList = employeeDTOList;
        employeeDTOList.add(new EmployeeDTO("Russell",28,"Developer"));
        employeeDTOList.add(new EmployeeDTO("Kailey",26,"Teacher"));
        addEmployeeDTO();
    }

    void addEmployee(EmployeeDTO employeeDTO){
        employeeDTOList.add(employeeDTO);
    }
    List<EmployeeDTO> getEmployeeDTOList() {

        return employeeDTOList;
    }


    private void addEmployeeDTO() {
        log.info("Fallback method: Fetching employee items from alternative source.");
        // Implement the logic to fetch the data from an alternative source (e.g., database)
        employeeDTOList.add(new EmployeeDTO("Russell",28,"Developer"));
        employeeDTOList.add(new EmployeeDTO("Kailey",26,"Teacher"));

    }
}
