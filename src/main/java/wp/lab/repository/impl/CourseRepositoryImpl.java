package wp.lab.repository.impl;

import wp.lab.bootstrap.DataHolder;
import org.springframework.stereotype.Repository;
import wp.lab.model.Course;
import wp.lab.model.Student;
import wp.lab.repository.CourseRepository;


import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepositoryImpl implements CourseRepository {

    public List<Course> findAllCourses() {
        return DataHolder.courseList;
    }

    @Override
    public Optional<Course> findById(Long courseId) {
        return DataHolder.courseList.stream().filter(i -> i.getCourseId().equals(courseId)).findFirst();
    }
    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        DataHolder.studentList.removeIf(i -> i.getUsername().equals(username));
        Course course = new Course(courseId,username);
        return course;
    }

    @Override
    public Course save(Course course) {
        DataHolder.courseList.removeIf(r->r.getCourseId().equals(course.getCourseId()));
        DataHolder.courseList.add(course);
        return course;
    }

    @Override
    public void deleteById(Long id) {
        DataHolder.courseList.removeIf(i->i.getCourseId().equals(id));
    }


}
