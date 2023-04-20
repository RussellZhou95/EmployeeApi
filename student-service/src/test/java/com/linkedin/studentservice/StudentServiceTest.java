package com.linkedin.studentservice;


import com.linkedin.studentservice.exception.StudentNotFoundException;
import com.linkedin.studentservice.model.Student;
import com.linkedin.studentservice.repo.StudentRepository;
import com.linkedin.studentservice.service.StudentService;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void getStudentById_forSavedStudent_isReturned(){
        //given
        Student savedStudent = studentRepository.save(new Student(null, "Mark"));
        //when
        Student student=studentService.getStudentById(savedStudent.getId());
        //then
        then(student.getId()).isNotNull();
        then(student.getName()).isEqualTo("Mark");

    }

    @Test
    void getStudentById_whenMissingStudent_notFoundExceptionThrown(){
        //given
//        Student savedStudent=studentRepository.save(new Student(123l,"Mark"));
        Long id=123l;
        //when
        Throwable throwable = catchThrowable(() -> studentService.getStudentById(id));
        //then
        BDDAssertions.then(throwable).isInstanceOf(StudentNotFoundException.class);
    }
}
