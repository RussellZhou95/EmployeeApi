package com.linkedin.studentservice.repo;

import com.linkedin.studentservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface StudentRepository extends JpaRepository<Student,Long> {
    Student getStudentByName(String name);

    @Query("SELECT AVG(grade) from Student where active is true")
    Double getScoreByActive();
}
