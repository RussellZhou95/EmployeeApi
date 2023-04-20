package com.linkedin.studentservice;


import com.linkedin.studentservice.model.Student;
import com.linkedin.studentservice.service.StudentService;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest
public class StudentControllerBaseClass {

    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void getStudent_forSavedStudent_isReturned() throws Exception {

        RestAssuredMockMvc.mockMvc(mockMvc);
        //given
        given(studentService.getStudentById(anyLong())).willReturn(
                new Student(1l,"Mark",10)
        );


    }

}
