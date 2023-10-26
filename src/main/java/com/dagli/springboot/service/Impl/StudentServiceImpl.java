package com.dagli.springboot.service.Impl;

import com.dagli.springboot.dto.StudentDto;
import com.dagli.springboot.entity.Student;
import com.dagli.springboot.exception.ResourceNotFoundException;
import com.dagli.springboot.repository.StudentRepository;
import com.dagli.springboot.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private ModelMapper mapper;

    private StudentRepository studentRepository;

    public StudentServiceImpl(ModelMapper mapper, StudentRepository studentRepository) {
        this.mapper = mapper;
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("student", "id", id));
        return mapper.map(student,StudentDto.class);
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = mapper.map(studentDto, Student.class);
        Student savedStudent = studentRepository.save(student);
        return mapper.map(savedStudent,StudentDto.class);
    }
}
