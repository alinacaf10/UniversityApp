package com.example.demo.services;

import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }

    public String addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return "Teacher successfully added";
    }

    public String deleteTeacher(int id) {
        Optional<Teacher> existsTeacher = teacherRepository.findById(id);
        if (existsTeacher.isPresent()) {
            teacherRepository.deleteById(id);
            return "Teacher with id:" + id + " successfully deleted";
        } else {
            return "Teacher with id:" + id + " does not exists";
        }
    }

    public String updateTeacher(int id, Teacher teacher) {
        Optional<Teacher> existsTeacher = teacherRepository.findById(id);
        if (existsTeacher.isPresent()) {
            Teacher updatedTeacher = existsTeacher.get();
            updatedTeacher.setName(teacher.getName());
            updatedTeacher.setExperience(teacher.getExperience());

            teacherRepository.save(updatedTeacher);
            return "Teacher with id:" + id + " successfully updated";
        } else {
            return "Teacher with id:" + id + " is not find";
        }
    }

    public List<Student> getStudentsOfTeacher(int id) {
//        Optional<Teacher> existsTeacher = teacherRepository.findById(id);
        return teacherRepository.getStudentsOfTeacher(id);
    }
}
