package com.linkedin.studentservice;


import com.linkedin.studentservice.exception.StudentNotFoundException;
import com.linkedin.studentservice.model.Student;
import com.linkedin.studentservice.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;


    @Test
    void getStudent_forSavedStudent_isReturned() throws Exception {
        //given
        given(studentService.getStudentById(anyLong())).willReturn(
                new Student(1l,"Mark",10)
        );

        //when//then
        mockMvc.perform(MockMvcRequestBuilders.get("/students/1")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers. jsonPath("id").value(1l))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("Mark"))
                .andExpect(MockMvcResultMatchers.jsonPath("grade").value(10));
    }

    @Test
    void getStudent_forMissingStudent_status404() throws Exception {
        //given
        given(studentService.getStudentById(anyLong())).willThrow(StudentNotFoundException.class);

        //when //then
        mockMvc.perform(MockMvcRequestBuilders.get("/students/1")).andExpect(status().isNotFound());
    }

}
