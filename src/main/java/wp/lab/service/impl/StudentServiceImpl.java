package wp.lab.service.impl;

import org.apache.http.auth.InvalidCredentialsException;
import wp.lab.bootstrap.DataHolder;
import wp.lab.model.Role;
import wp.lab.model.Student;
import wp.lab.model.exceptions.InvalidUsernameOrPasswordException;
import wp.lab.model.exceptions.PasswordsDoNotMatchException;
import wp.lab.model.exceptions.StudentNotFoundException;
import wp.lab.model.exceptions.UsernameAlreadyExistsException;
import wp.lab.repository.StudentRepository;
import wp.lab.repository.impl.StudentRepositoryImpl;
import wp.lab.service.StudentService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepositoryImpl studentRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentServiceImpl(StudentRepositoryImpl studentRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAllStudents();
    }

    @Override
    public Student searchByNameOrSurname(String text) throws StudentNotFoundException {
        return studentRepository.findAllByNameOrSurname(text).orElseThrow(()->new StudentNotFoundException(text));
    }

    @Override
    public Student register(String username, String password, String repeatPassword, String name, String surname, Role role) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.studentRepository.findAllByNameOrSurname(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        Student student = new Student(username,passwordEncoder.encode(password),name,surname,role);
        return studentRepository.save(student);
    }


    @Override
    public Student save(String username, String password, String name, String surname, Role role) throws InvalidCredentialsException {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) throw new InvalidCredentialsException();
        if (this.studentRepository.findAllByNameOrSurname(username).isPresent()) throw new UsernameAlreadyExistsException(username);
        Student student = new Student(username,passwordEncoder.encode(password),name,surname,role);
        return studentRepository.save(student);
    }
}