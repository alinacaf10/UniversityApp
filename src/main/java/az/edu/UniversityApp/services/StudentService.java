package az.edu.UniversityApp.services;

import az.edu.UniversityApp.model.Student;
import az.edu.UniversityApp.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Transactional
    public String updateStudent(int id, Student student) {
        Optional<Student> exStudent = studentRepository.findById(id);
        if (exStudent.isPresent()) {
            Student updated = exStudent.get();
            updated.setName(student.getName());
            updated.setAge(student.getAge());

            studentRepository.save(updated);
            return "Student successfully updated";
        }
            return "Student with id:" + id + " is not find";
    }

    public Student getById(int id) {
        return studentRepository.findStudentById(id);
    }
}
