package wp.lab.service;

import org.apache.http.auth.InvalidCredentialsException;
import wp.lab.model.Role;
import wp.lab.model.Student;
import wp.lab.model.exceptions.StudentNotFoundException;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> listAll();
    Student searchByNameOrSurname(String text) throws StudentNotFoundException;
    Student save(String username, String password, String name, String surname,Role role) throws InvalidCredentialsException;
    Student  register(String username, String password, String repeatPassword, String name, String surname, Role role);

}
