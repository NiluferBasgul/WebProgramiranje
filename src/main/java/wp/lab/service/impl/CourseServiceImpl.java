package wp.lab.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import wp.lab.model.Course;
import wp.lab.model.Teacher;
import wp.lab.model.exceptions.CourseNotFoundException;
import org.springframework.stereotype.Service;
import wp.lab.repository.impl.CourseRepositoryImpl;
import wp.lab.repository.impl.StudentRepositoryImpl;
import wp.lab.repository.TeacherRepository;
import wp.lab.service.CourseService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepositoryImpl courseRepository;
    private final StudentRepositoryImpl studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final TeacherRepository teacherRepository;

    public CourseServiceImpl(CourseRepositoryImpl courseRepository, StudentRepositoryImpl studentRepository, PasswordEncoder passwordEncoder, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.teacherRepository = teacherRepository;
    }

    public List<Course> findAll(){
        return this.courseRepository.findAllCourses();
    }

    @Override
    public Optional<Course> listStudentsByCourse(Long courseId) {
        return this.courseRepository.findById(courseId);
    }

    @Override
    @Transactional
    public Optional<Course> save(Long courseId, String name, String description) {
        Course course = this.courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));
        Teacher teacher = (Teacher) this.teacherRepository.findAll();

        return Optional.of(this.courseRepository.save(new Course(courseId, name, description)));
    }

    @Override
    public void deleteById(Long courseId) {
        this.courseRepository.deleteById(courseId);
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAllCourses();
    }

}
