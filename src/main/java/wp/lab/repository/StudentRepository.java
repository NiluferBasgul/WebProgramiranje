package wp.lab.repository;

import wp.lab.model.Course;
import wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository {
    List<Student> findAllStudents();

    Optional<Student> findAllByNameOrSurname(String text);

    Optional<Student> findAllStudentsByCourse(Long courseId);

    Optional<Student> addStudentToCourse(Student student, Course course);

    Student save(Student student);

}


