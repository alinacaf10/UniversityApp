package com.example.demo.services;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public String addStudent(Student student) {
        studentRepository.save(student);
        return "Student successfully added";
    }

    public String deleteStudent(int id) {
        if (studentRepository.findById(id).isEmpty()) {
            return "Student doesn't find with id:" + id;
        }
        studentRepository.deleteById(id);
        return "Student with id:" + id + " successfully deleted";
    }


}
