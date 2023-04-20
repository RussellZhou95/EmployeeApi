package com.linkedin.studentservice;

import com.linkedin.studentservice.repo.StudentRepository;
import com.linkedin.studentservice.model.Student;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;

import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    void testGetStudentByName_returnStudentDetails(){
        //given
        Student savedStudent = testEntityManager.persistFlushFind(new Student(null, "Mark"));

        //when
        Student student=studentRepository.getStudentByName("Mark");

        //then
        then(student.getId()).isNotNull();
        then(student.getName()).isEqualTo(savedStudent.getName());
    }

    @Test
    void testGetAverageGradeForActiveStudent_calculatesAvg(){
        Student student1=new Student(null,"Mark",true,80);
        Student student2=new Student(null,"Susan",true,100);
        Student student3=new Student(null,"Peter",false,50);

        Arrays.asList(student1,student2,student3).forEach(testEntityManager::persistFlushFind);

        Double averageGrade=studentRepository.getScoreByActive();

        then(averageGrade).isEqualTo(90);
    }
}

