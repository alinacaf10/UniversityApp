package az.edu.university.service;

import az.edu.university.model.Student;
import az.edu.university.model.Teacher;
import az.edu.university.repository.StudentRepository;
import az.edu.university.services.StudentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

public class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentService studentService;

    @BeforeClass
    public static void setUp(){
        System.out.println("Setup Test");
    }

    @Before
    public void before(){
        System.out.println("before test");
        MockitoAnnotations.openMocks(this);
        List<Student> list=new ArrayList<>();

        Student student=new Student();
        student.setName("Ali");
        student.setAge(23);
        student.setTeacher(new Teacher(1));

        list.add(student);
        Mockito.when(studentRepository.findAll()).thenReturn(list);
        Mockito.when(studentRepository.findById(3)).thenReturn(Optional.of(student));
    }

    @Test
    public void testGetAllStudent(){
        List<Student> list=studentService.getAllStudents();
        Assert.assertFalse("user size must be greater than 0", list.isEmpty());
        Mockito.verify(studentRepository,Mockito.atLeastOnce())
                .findAll();
    }

    @Test
    public void testGetById(){
        Student student=studentService.getById(3);
        Assert.assertNull("student must not be null",student);
        Mockito.verify(studentRepository,Mockito.atLeastOnce())
                .findById(3);
    }

}
