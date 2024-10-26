package com.example.employeeapi.controller;

import com.example.employeeapi.annotation.RepeatSubmit;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RepeatSubmit(limitType = RepeatSubmit.TYPE.PARAM,lockTime = 6)
    @PostMapping
    public String saveInfo(@RequestBody String accountNo){

        return "test ok "+ accountNo;
    }



}
