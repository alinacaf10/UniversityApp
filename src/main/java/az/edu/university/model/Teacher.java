package az.edu.university.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int experience;

    private String department;
    //    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<Student> students = new ArrayList<>();


    public void addStudent(Student student) {
        this.students.add(student);
        student.setTeacher(this);
    }
}
