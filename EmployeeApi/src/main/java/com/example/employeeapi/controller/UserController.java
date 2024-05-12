package com.example.employeeapi.controller;

import com.example.employeeapi.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public List<UserDTO> getAllUsers() {

        return Arrays.asList(new UserDTO("Kelly","teacher"),new UserDTO("Russell","developer"));
    }
}