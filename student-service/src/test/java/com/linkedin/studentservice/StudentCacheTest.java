package com.linkedin.studentservice;

import com.linkedin.studentservice.model.Student;
import com.linkedin.studentservice.repo.StudentRepository;
import com.linkedin.studentservice.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.NONE)
public class StudentCacheTest {

    @MockBean
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;

    @Test
    void getStudentById_forMultipleRequests_isRetrievedFromCache(){
        //given
        Long id=123l;
        given(studentRepository.findById(id)).willReturn(Optional.of(new Student(null,"Mark")));

        //when
        studentService.getStudentById(id);
        studentService.getStudentById(id);
        studentService.getStudentById(id);

        //then
        then(studentRepository).should(times(1)).findById(id);
    }
}
