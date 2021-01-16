package wp.lab.web.servlet;

import org.apache.http.auth.InvalidCredentialsException;
import wp.lab.model.Role;
import wp.lab.model.Student;
import wp.lab.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="StudentEnrollment",urlPatterns = "/servlet/course")

public class StudentEnrollmentSummary extends HttpServlet {
    private final StudentService studentService;

    public StudentEnrollmentSummary(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = studentService.listAll();
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
        writer.println("<h2>Categories</h2>");
        students.forEach(r->
                writer.format("<li>%s (%s)</li>",r.getName(),r.getSurname(),r.getPassword(),r.getUsername()));
        writer.println("</ul>");

        writer.println("<html>");
        writer.println("<head>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h3>Add New Student</h3>");
        writer.println("<form method='post' action='/servlet/listStudents'>" +
                "<label for='name'>Name:</label><input id='name' type='text' name='name'/>"+
                "<label for='surname'>Surname:</label><input id='surname' type='text' name='surname'/>"+
                "<label for='password'>Password:</label><input id='password' type='text' name='password'/>"+
                "<label for='username'>Username:</label><input id='username' type='text' name='username'/>"+
                "<input type='submit' value='Submit'/>"+
                "</form>");
        writer.println("</body>");
        writer.println("<html>");

        writer.println("</body>");
        writer.println("</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String)req.getParameter("username");
        String password = (String) req.getParameter("password");
        String name = (String) req.getParameter("name");
        String surname = (String) req.getParameter("surname");
        String role =(String) req.getParameter("role");

        try {
            studentService.save(username,password,name,surname, Role.valueOf(role));
        } catch (InvalidCredentialsException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/addStudent");
    }


}
