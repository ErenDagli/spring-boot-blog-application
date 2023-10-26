package com.dagli.springboot.controller;

import com.dagli.springboot.dto.StudentDto;
import com.dagli.springboot.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // http://localhost:8080/students/student
    @GetMapping("student/{id}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable("id") Long studentId) {
        StudentDto studentDto = studentService.getStudentById(studentId);
        return ResponseEntity.ok().header(studentDto.getFirstName(),studentDto.getLastName()).body(studentDto);
    }

    // http://localhost:8080/students
    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents() {
        ArrayList<StudentDto> studentDtos = new ArrayList<>();
        studentDtos.add(new StudentDto(1, "eren", "dagli","eren.dagli@gmail.com"));
        studentDtos.add(new StudentDto(2, "ahmet", "dagli","eren.dagli@gmail.com"));
        return ResponseEntity.ok(studentDtos);
    }

    //Spring Boot REST API with Path Variable
    // id URI template variable
    // http://localhost:8080/students/1/eren/dagli
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<StudentDto> studentPathVariable(@PathVariable("id") int studentId,
                                                          @PathVariable("first-name") String firstName,
                                                          @PathVariable("last-name") String lastName) {
        StudentDto studentDto = new StudentDto(studentId, firstName, lastName,"eren.dagli@gmail.com");
        return ResponseEntity.ok(studentDto);
    }


    //Spring Boot REST API with Request Param
    // id URI template variable
    // http://localhost:8080/students/query?id=1&firstName=a&lastName=b
    @GetMapping("query")
    public ResponseEntity<StudentDto> studentRequestVariable(@RequestParam int id,
                                                             @RequestParam String firstName,
                                                             @RequestParam String lastName) {
        StudentDto studentDto = new StudentDto(id, firstName, lastName,"eren.dagli@gmail.com");
        return ResponseEntity.ok(studentDto);
    }

    //Spring Boot REST API that handles HTTP POST Request
    // @PostMapping and @RequestBody
    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        StudentDto savedStudent = studentService.createStudent(studentDto);
        System.out.println(savedStudent.toString());
        return new ResponseEntity<>(savedStudent,HttpStatus.CREATED);
    }

    //Spring Boot REST API that handles HTTP PUT Request - updating existing resource
    // @PostMapping and @RequestBody
    @PutMapping("{id}/update")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto, @PathVariable("id") int studentId) {
        System.out.println(studentDto.getFirstName());
        System.out.println(studentDto.getLastName());
        return ResponseEntity.ok(studentDto);
    }

    //Spring boot REST API that handles HTTP DELETE Request - deleting the existing resource
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("Student deleted successfuly");
    }
}
