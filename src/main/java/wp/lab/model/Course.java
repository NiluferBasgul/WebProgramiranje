package wp.lab.model;

import lombok.Data;
import wp.lab.model.enumerations.Type;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long courseId;


    public String name;

    @Column(length = 4000)
    public String description;

    @OneToMany
    public List<Student> students;

    @ManyToMany
    public List<Teacher> teachers;

    @Enumerated(EnumType.STRING)
    private Type type;

    public Course(Long courseId, String name, String description) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
    }

    public Course(Long courseId, String username) {
    }
    public Course(){}
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
