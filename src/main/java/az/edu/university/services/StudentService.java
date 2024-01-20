package az.edu.university.services;

import az.edu.university.model.Student;
import az.edu.university.model.Teacher;
import az.edu.university.repository.StudentRepository;
import az.edu.university.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public StudentService(StudentRepository studentRepository,
                          TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository=teacherRepository;
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
        studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return studentRepository.findStudentById(id);
    }

    @Transactional
    public String changeTeacher(int studentId, int teacherId) {
        Optional<Student> student = studentRepository.findById(studentId);
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);

        if (student.isPresent() && teacher.isPresent()) {
            student.get().setTeacher(teacher.get());
            studentRepository.save(student.get());
            return "Student with id:" + studentId + " change teacher";
        }

        return "Student or Teacher is not found";
    }
}
