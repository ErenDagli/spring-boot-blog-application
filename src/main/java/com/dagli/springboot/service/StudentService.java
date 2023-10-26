package com.dagli.springboot.service;

import com.dagli.springboot.dto.StudentDto;
import com.dagli.springboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentService {

    StudentDto getStudentById(Long id);
    StudentDto createStudent(StudentDto studentDto);
}
