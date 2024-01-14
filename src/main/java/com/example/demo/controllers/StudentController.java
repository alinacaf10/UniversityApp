package com.example.demo.controllers;

import com.example.demo.model.Student;
import com.example.demo.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.addStudent(student), HttpStatus.CREATED);
    }
}
