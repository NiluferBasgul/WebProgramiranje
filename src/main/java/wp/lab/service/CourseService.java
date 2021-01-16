package wp.lab.service;

import wp.lab.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Optional<Course> listStudentsByCourse(Long courseId);
    public Optional<Course> save(Long courseId, String name, String description);
    public void deleteById(Long courseId);
    List<Course> listAll();
}
