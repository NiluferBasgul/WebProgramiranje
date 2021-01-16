package wp.lab;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import wp.lab.model.Course;
import wp.lab.model.Role;
import wp.lab.model.Teacher;
import wp.lab.service.CourseService;
import wp.lab.service.StudentService;
import wp.lab.service.TeacherService;

import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CourseApplicationTests {

    MockMvc mockMvc;


    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    CourseService courseService;


    private static Optional<Course> c1;
    private static Teacher t1;
    private static boolean dataInitialized = false;

    @BeforeEach
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        initData();
    }

    private void initData() {
        if (!dataInitialized) {
            c1 = courseService.save(1L, "name","description");
            courseService.save(2L, "name2","description2");

            t1 = teacherService.save(1L, "name","username").get();
            teacherService.save(2L, "name2","username2");

            String user = "user";
            String admin = "admin";

            studentService.register(user, user, user, user, user, Role.ROLE_USER);
            studentService.register(admin, admin, admin, admin, admin, Role.ROLE_ADMIN);
            dataInitialized = true;
        }
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetProducts() throws Exception {
        MockHttpServletRequestBuilder productRequest = MockMvcRequestBuilders.get("/courses");
        this.mockMvc.perform(productRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("courses"))
                .andExpect(MockMvcResultMatchers.model().attribute("bodyContent", "courses"))
                .andExpect(MockMvcResultMatchers.view().name("list-courses"));

    }

    @Test
    public void testDeleteProduct() throws Exception {
        Course course = this.courseService.save(1L, "name", "description").get();
        MockHttpServletRequestBuilder productDeleteRequest = MockMvcRequestBuilders
                .delete("/course/delete/" + course.getCourseId());

        this.mockMvc.perform(productDeleteRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/courses"));
    }

}
