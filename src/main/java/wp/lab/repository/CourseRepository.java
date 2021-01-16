package wp.lab.repository;

import wp.lab.model.Course;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository {
    List<Course> findAllCourses();
    Optional<Course> findById(Long courseId);
    public Course addStudentInCourse(String username, Long courseId);
    public Course save(Course course);
    public void deleteById(Long id);

}
