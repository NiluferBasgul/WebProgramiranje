package wp.lab.repository.impl;

import wp.lab.bootstrap.DataHolder;
import wp.lab.model.Course;
import wp.lab.model.Student;
import org.springframework.stereotype.Repository;
import wp.lab.repository.CourseRepository;
import wp.lab.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    @Override
    public List<Student> findAllStudents() {
        return DataHolder.studentList;
    }

    @Override
    public Optional<Student> findAllByNameOrSurname(String text) {
        return DataHolder.studentList.stream().filter(r -> r.getName().equals(text) && r.getSurname().equals(text)).findFirst();
    }

    @Override
    public Optional<Student> findAllStudentsByCourse(Long courseId) {
        return DataHolder.studentList.stream().filter(r -> r.getCourse().equals(courseId)).findFirst();
    }

    @Override
    public Optional<Student> addStudentToCourse(Student student, Course course) {
        DataHolder.studentList.removeIf(i -> false);
        Student students = new Student(student.username, student.password, student.name, student.surname);
        DataHolder.studentList.add(student);
        return Optional.of(students);
    }

    @Override
    public Student save(Student student) {
        DataHolder.studentList.removeIf(r->r.getUsername().equals(student.getUsername()));
        DataHolder.studentList.add(student);
        return student;
    }


}
