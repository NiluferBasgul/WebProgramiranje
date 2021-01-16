package wp.lab.selenium;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import wp.lab.model.Course;
import wp.lab.model.Role;
import wp.lab.model.Student;
import wp.lab.model.Teacher;
import wp.lab.service.CourseService;
import wp.lab.service.StudentService;
import wp.lab.service.TeacherService;

import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {


    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    CourseService courseService;


    private HtmlUnitDriver driver;

    private static Optional<Course> c1;
    private static Optional<Course> c2;
    private static Teacher t1;
    private static Teacher t2;
    private static Student regularUser;
    private static Student adminUser;

    private static String user = "user";
    private static String admin = "admin";

    private static boolean dataInitialized = false;


    @BeforeEach
    private void setup() {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }


    private void initData() {
        if (!dataInitialized) {
            c1 = courseService.save(1L, "name","description");
            c2 = courseService.save(2L, "name2","description2");

            t1 = teacherService.save(1L, "name","username").get();
            t2 = teacherService.save(2L, "name","username2").get();


            regularUser = studentService.register(user, user, user, user, user, Role.ROLE_USER);
            adminUser = studentService.register(admin, admin, admin, admin, admin, Role.ROLE_ADMIN);
            dataInitialized = true;
        }
    }

    @Test
    public void testScenario() throws Exception {
        CoursesPage coursesPage = CoursesPage.to(this.driver);
        coursesPage.assertElemts(0, 0, 0, 0);
        LoginPage loginPage = LoginPage.openLogin(this.driver);

        coursesPage = LoginPage.doLogin(this.driver, loginPage, adminUser.getUsername(), admin);
        coursesPage.assertElemts(0, 0, 0, 0);

        coursesPage = AddOrEditCourse.addCourse(this.driver, "name", "description", "5", t2.getName());
        coursesPage.assertElemts(1, 1, 1, 1);

        coursesPage = AddOrEditCourse.addCourse(this.driver, "test1", "description", "4", t2.getName());
        coursesPage.assertElemts(2, 2, 2, 2);

        coursesPage.getDeleteButtons().get(1).click();
        coursesPage.assertElemts(1, 1, 1, 1);

        coursesPage = AddOrEditCourse.editCourses(this.driver, coursesPage.getEditButtons().get(0), "test1", "200", "4", t2.getName());
        coursesPage.assertElemts(1, 1, 1, 1);

        loginPage = LoginPage.logout(this.driver);
        coursesPage = LoginPage.doLogin(this.driver, loginPage, regularUser.getUsername(), user);
        coursesPage.assertElemts(1, 0, 0, 1);

        Assert.assertEquals("url do not match", "http://localhost:9999/shopping-cart", this.driver.getCurrentUrl());

    }


}
