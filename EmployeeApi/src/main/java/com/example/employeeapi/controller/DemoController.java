package com.example.employeeapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/Demo")
@RestController
public class DemoController {

    private static String template="Hello,%s";

    @GetMapping
    public String getRequest(@RequestParam(value = "name",defaultValue = "world")String name){

        return String.format(template,name);
    }
}
