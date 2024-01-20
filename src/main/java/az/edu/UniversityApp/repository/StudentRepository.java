package az.edu.UniversityApp.repository;

import az.edu.UniversityApp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllByTeacherId(int id);

    Student findStudentById(int id);
}
