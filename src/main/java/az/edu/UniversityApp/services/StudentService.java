package az.edu.UniversityApp.services;

import az.edu.UniversityApp.model.Student;
import az.edu.UniversityApp.model.Teacher;
import az.edu.UniversityApp.repository.StudentRepository;
import az.edu.UniversityApp.repository.TeacherRepository;
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
    public String changeTeacher(int id, Teacher teacher) {
        Optional<Student> student=studentRepository.findById(id);
        if (student.isPresent()){
            student.get().setTeacher(teacher);
            teacherRepository.save(teacher);
            studentRepository.save(student.get());
            return "Student with id:"+id+" change teacher";
        }
        return "Student with id:"+id+" is not found";
    }
}
