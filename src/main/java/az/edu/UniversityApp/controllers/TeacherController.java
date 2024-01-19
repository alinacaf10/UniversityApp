package az.edu.UniversityApp.controllers;

import az.edu.UniversityApp.model.Student;
import az.edu.UniversityApp.model.Teacher;
import az.edu.UniversityApp.services.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return new ResponseEntity<>(teacherService.getAllTeacher(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTeacher(@RequestBody Teacher teacher) {
        return new ResponseEntity<>(teacherService.addTeacher(teacher), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable int id) {
        return new ResponseEntity<>(teacherService.deleteTeacher(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        return new ResponseEntity<>(teacherService.updateTeacher(id, teacher), HttpStatus.OK);
    }

    @GetMapping("/getStudents/{id}")
    public ResponseEntity<List<Student>> getStudentsOfTeacher(@PathVariable int id) {
        return new ResponseEntity<>(teacherService.getStudentsOfTeacher(id), HttpStatus.OK);
    }
}
