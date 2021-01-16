package wp.lab.web.servlet;

import wp.lab.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name ="/CreateStudent")
public class CreateStudentServlet extends HttpServlet {
    private final StudentService studentService;

    public CreateStudentServlet(StudentService studentService) {
        this.studentService = studentService;
    }

    class Student {
        String name;

        public String getName() {
            return this.name = name;
        }

        public Student(String name) {
            this.name = name;
        }
    }

    private List<Student> studentList = null;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html:charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h2>Info</h2>");
        writer.println("<ul>");
        //.forEach(r->writer.format("<li>%s (%s)</li>",r.getName(),r.getCourseId()));
        writer.println("</ul>");

        writer.println("<html>");
        writer.println("<head>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h3>Add</h3>");
        writer.println("<form method='post' action='/servlet/course'>" +
                "<label for='name'>Name:</label><input id='name' type='text' name='name'/>" +
                "<label for='desc'>Description:</label><input id='desc' type='text' name='description'/>" +
                "<input type='submit' value='Submit'/>" +
                "</form>");
        writer.println("</body>");
        writer.println("<html>");

        writer.println("</body>");
        writer.println("</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentName = req.getParameter("name");
        addStudent(studentName);
        resp.sendRedirect("/servlet/course");
    }

    public void addStudent(String name) {
        if (name != null && !name.isEmpty()) {
            studentList.add(new Student(name));
        }

    }
}
