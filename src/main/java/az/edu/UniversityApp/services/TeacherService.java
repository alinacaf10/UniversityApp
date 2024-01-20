package az.edu.UniversityApp.services;

import az.edu.UniversityApp.model.Student;
import az.edu.UniversityApp.model.Teacher;
import az.edu.UniversityApp.repository.TeacherRepository;
import az.edu.UniversityApp.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    public TeacherService(StudentRepository studentRepository,TeacherRepository teacherRepository) {
            this.studentRepository = studentRepository;
            this.teacherRepository=teacherRepository;
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
        }
            return "Teacher with id:" + id + " does not exists";
    }
    @Transactional
    public String updateTeacher(int id, Teacher teacher) {
        Optional<Teacher> existsTeacher = teacherRepository.findById(id);
        if (existsTeacher.isPresent()) {
            Teacher updatedTeacher = existsTeacher.get();
            updatedTeacher.setName(teacher.getName());
            updatedTeacher.setExperience(teacher.getExperience());

            teacherRepository.save(updatedTeacher);
            return "Teacher with id:" + id + " successfully updated";
        }
            return "Teacher with id:" + id + " is not find";
    }

    public List<Student> getStudentsOfTeacher(int id) {
        return studentRepository.findAllByTeacherId(id);
    }

    public List<Teacher> getTeachersByDepartment(String department) {
        return teacherRepository.findAllByDepartment(department);
    }

    public String addStudentToTeacher(int teacherId, Student student) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("Teacher not found with id: " + teacherId));
        teacher.addStudent(student);
        teacherRepository.save(teacher);

        return "Student with id:"+student.getId()+ " successfully added";
    }

    public Teacher getTeacherById(int id) {
        return teacherRepository.getTeacherById(id);
    }
}
