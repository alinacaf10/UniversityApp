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
        MockitoAnnotations.initMocks(this);
        List<Student> list=new ArrayList<>();

        Student student=new Student();
        student.setName("Ali");
        student.setAge(23);
        student.setTeacher(new Teacher(1));

        list.add(student);
        Mockito.when(studentRepository.findAll()).thenReturn(list);
    }

    @Test
    public void testGetAllStudent(){
        List<Student> list=studentService.getAllStudents();
        Assert.assertTrue("user size must be greater than 0",list.size()>0);
        Mockito.verify(studentRepository,Mockito.atLeastOnce())
                .findAll();
    }

    @Test
    public void testGetById(){
        Student student=studentService.getById(0);
        Assert.assertNull("student must be null",student);
        Mockito.verify(studentRepository,Mockito.atLeastOnce())
                .findById(0);
    }

}
