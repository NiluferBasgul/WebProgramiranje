package wp.lab.web.servlet;

import wp.lab.model.Course;
import wp.lab.service.impl.CourseServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="course_list",urlPatterns = "/servlet/course")
public class CoursesListSevlet extends HttpServlet {
    private final CourseServiceImpl courseService;

    public CoursesListSevlet(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> courseList = courseService.listAll();
        String ipAddress = req.getRemoteAddr();
        String clientAgent = req.getHeader("User-Agent");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h2>Info about our request</h2>");
        writer.format("IP Address:%s, Browser: %s",ipAddress,clientAgent);
        writer.println("<h2>Courses</h2>");
        writer.println("<ul>");
        courseList.forEach(r->
                writer.format("<li>%s (%s)</li>",r.getName(),r.getDescription()));
        writer.println("</ul>");

        writer.println("<html>");
        writer.println("<head>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h3>Add New Course</h3>");
        writer.println("<form method='post' action='/servlet/category'>" +
                "<label for='name'>Name:</label><input id='name' type='text' name='name'/>"+
                "<label for='desc'>Description:</label><input id='desc' type='text' name='description'/>"+
                "<input type='submit' value='Submit'/>"+
                "</form>");
        writer.println("</body>");
        writer.println("<html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long courseId = (Long)req.getContentLengthLong();
        String name = (String)req.getParameter("name");
        String description = (String) req.getParameter("description");
        courseService.save(courseId,name,description);
        resp.sendRedirect("/servlet/course");
    }

}
