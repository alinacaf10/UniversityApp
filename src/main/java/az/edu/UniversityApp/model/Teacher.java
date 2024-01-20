package az.edu.UniversityApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Entity
@Table(name = "teacher")
@Data
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int experience;

    private String department;
    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<Student> students = new ArrayList<>();

    public Teacher(int id, String name, int experience,String department) {
        this.id = id;
        this.name = name;
        this.experience = experience;
        this.department=department;
    }

    public void addStudent(Student student) {
        students.add(student);
        student.setTeacher(this);
    }
}
