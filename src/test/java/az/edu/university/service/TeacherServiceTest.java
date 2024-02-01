package az.edu.university.service;

import az.edu.university.model.Teacher;
import az.edu.university.repository.TeacherRepository;
import az.edu.university.services.TeacherService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

public class TeacherServiceTest {

    @Mock
    TeacherRepository repository;
    @InjectMocks
    TeacherService teacherService;

    @BeforeClass
    public static void setUp(){
        System.out.println("before class anno");
    }

    @Before
    public void before(){
        System.out.println("before anno");

        MockitoAnnotations.openMocks(this);

        List<Teacher> list=new ArrayList<>();

        Teacher teacher=new Teacher();
        teacher.setName("Teacher");
        teacher.setExperience(11);
        teacher.setDepartment("depart");

        list.add(teacher);
    }
}
