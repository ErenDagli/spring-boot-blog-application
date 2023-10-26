package com.dagli.springboot.repository;

import com.dagli.springboot.dto.StudentDto;
import com.dagli.springboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Student findByEmailAddress(String email);
}
