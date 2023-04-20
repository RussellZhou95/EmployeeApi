package com.linkedin.studentservice.service;

import com.linkedin.studentservice.exception.StudentNotFoundException;
import com.linkedin.studentservice.model.Student;
import com.linkedin.studentservice.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Cacheable("students")
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(()->new StudentNotFoundException());
    }
}
