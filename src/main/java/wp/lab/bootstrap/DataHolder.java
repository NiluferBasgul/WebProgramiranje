package wp.lab.bootstrap;

import lombok.Getter;

import org.springframework.stereotype.Component;
import wp.lab.model.Course;
import wp.lab.model.Student;
import wp.lab.model.Teacher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static List<Course> courseList = new ArrayList<>();
    public static List<Student> studentList = new ArrayList<>();
    public static List<Teacher> teacherList = new ArrayList<>();
//
//
//    @PostConstruct
//    public void init() {
//        courseList.add(new Course(1l, "Operativni","Operativni Sistemi",studentList));
//        courseList.add(new Course(2l, "Web Programiranje","Web programiranje",studentList));
//        courseList.add(new Course(3l, "EMT","EMT",studentList));
//
//        studentList.add(new Student("nilufer.bashgul","nb","Nilufer","Bashgul"));
//        studentList.add(new Student("ela.lilla","el","Elanur","Lilla"));
//        studentList.add(new Student("alican.ali","al","Alican","Ali"));
//
//    }
}
