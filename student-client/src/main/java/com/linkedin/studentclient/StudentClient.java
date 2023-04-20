package com.linkedin.studentclient;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class StudentClient {

    @Autowired
    private final WebClient webClient;



    public Student getStudent(Long id) {
        return webClient.get().uri("/students/{id}",id).retrieve().bodyToMono(Student.class).block();
    }
}
