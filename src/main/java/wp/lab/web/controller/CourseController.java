package wp.lab.web.controller;

import wp.lab.model.Course;
import wp.lab.model.Student;
import wp.lab.model.Teacher;
import wp.lab.service.CourseService;
import wp.lab.service.StudentService;
import wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wp.lab.service.impl.CourseServiceImpl;
import wp.lab.service.impl.StudentServiceImpl;
import wp.lab.service.impl.TeacherServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
    CourseServiceImpl courseService;
    TeacherServiceImpl teacherService;
    StudentServiceImpl studentService;

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Course> courses = this.courseService.findAll();
        model.addAttribute("courses", courses);
        model.addAttribute("bodyContent", "courses");
        return "listCourses";
    }

    @PostMapping("/courses/add")
    public String saveCourse(@RequestParam(required = false) Long courseId,
                             @RequestParam String name,
                             @RequestParam String description){
        if (courseId!=null){
            this.courseService.save(courseId,name,description);
        }
        return "listCourses";
    }

    @DeleteMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable Long id){
        this.courseService.deleteById(id);
        return "listCourses";
    }

    @GetMapping("/courses/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model){
        if (this.courseService.listStudentsByCourse(id).isPresent()){
            Course course = this.courseService.listStudentsByCourse(id).get();
            List<Teacher> teachers = this.teacherService.findAll();
            List<Student> students = this.studentService.listAll();
            model.addAttribute("teachers", teachers);
            model.addAttribute("students", students);
            model.addAttribute("course",course);
            model.addAttribute("bodyContent", "add-course");
            return "add-course";
        }
        return "redirect:/courses?error=CourseNotFound";
    }

    @PostMapping("/courses/add-form")
    public String getAddCoursePage(Model model){
        List<Teacher> teachers = this.teacherService.findAll();
        List<Course> courses = this.courseService.findAll();
        model.addAttribute("teachers", teachers);
        model.addAttribute("courses",courses);
        model.addAttribute("bodyContent","add-course");
        return "add-course";
    }
}
