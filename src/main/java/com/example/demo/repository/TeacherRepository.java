package com.example.demo.repository;

import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    @Query(value = "SELECT s.* FROM teacher t right JOIN student s ON s.teacher_id = t.id where t.id= :id", nativeQuery = true)
    List<Student> getStudentsOfTeacher(int id);
}
