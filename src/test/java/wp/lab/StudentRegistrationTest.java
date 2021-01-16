package wp.lab;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import wp.lab.model.Role;
import wp.lab.model.Student;
import wp.lab.model.exceptions.InvalidUsernameOrPasswordException;
import wp.lab.model.exceptions.PasswordsDoNotMatchException;
import wp.lab.model.exceptions.UsernameAlreadyExistsException;
import wp.lab.repository.impl.StudentRepositoryImpl;
import wp.lab.service.StudentService;
import wp.lab.service.impl.StudentServiceImpl;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class StudentRegistrationTest {


    @Mock
    private StudentRepositoryImpl studentRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private StudentService studentService;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Student student = new Student("username", "password", "name", "surname", Role.ROLE_USER);
        Mockito.when(this.studentRepository.save(Mockito.any(Student.class))).thenReturn(student);
        Mockito.when(this.passwordEncoder.encode(Mockito.anyString())).thenReturn("password");


        this.studentService = Mockito.spy(new StudentServiceImpl(this.studentRepository, this.passwordEncoder));
    }

    @Test
    public void testSuccessRegister() {
        Student student = this.studentService.register("username", "password", "password", "name", "surename", Role.ROLE_USER);

        Mockito.verify(this.studentService).register("username", "password", "password", "name", "surname", Role.ROLE_USER);


        Assert.assertNotNull("Student is null", student);
        Assert.assertEquals("name do not mach", "name", student.getName());
        Assert.assertEquals("role do not mach", Role.ROLE_USER, student.getRole());
        Assert.assertEquals("surname do not mach", "surname", student.getSurname());
        Assert.assertEquals("password do not mach", "password", student.getPassword());
        Assert.assertEquals("username do not mach", "username", student.getUsername());
    }


    @Test
    public void testNullUsername() {
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.studentService.register(null, "password", "password", "name", "surname", Role.ROLE_USER));
        Mockito.verify(this.studentService).register(null, "password", "password", "name", "surname", Role.ROLE_USER);
    }

    @Test
    public void testEmptyUsername() {
        String username = "";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.studentService.register(username, "password", "password", "name", "surname", Role.ROLE_USER));
        Mockito.verify(this.studentService).register(username, "password", "password", "name", "surname", Role.ROLE_USER);
    }


    @Test
    public void testEmptyPassword() {
        String username = "username";
        String password = "";
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.studentService.register(username, password, "password", "name", "surname", Role.ROLE_USER));
        Mockito.verify(this.studentService).register(username, password, "password", "name", "surname", Role.ROLE_USER);
    }

    @Test
    public void testNullPassword() {
        String username = "username";
        String password = null;
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidUsernameOrPasswordException.class,
                () -> this.studentService.register(username, password, "password", "name", "surname", Role.ROLE_USER));
        Mockito.verify(this.studentService).register(username, password, "password", "name", "surname", Role.ROLE_USER);
    }


    @Test
    public void testPasswordMismatch() {
        String username = "username";
        String password = "password";
        String confirmPassword = "otherPassword";
        Assert.assertThrows("PasswordsDoNotMatchException expected",
                PasswordsDoNotMatchException.class,
                () -> this.studentService.register(username, password, confirmPassword, "name", "surname", Role.ROLE_USER));
        Mockito.verify(this.studentService).register(username, password, confirmPassword, "name", "surname", Role.ROLE_USER);
    }


    @Test
    public void testDuplicateUsername() {
        Student student = new Student("username", "password", "name", "surname", Role.ROLE_USER);
        Mockito.when(this.studentRepository.findAllByNameOrSurname(Mockito.anyString())).thenReturn(Optional.of(student));
        String username = "username";
        Assert.assertThrows("UsernameAlreadyExistsException expected",
                UsernameAlreadyExistsException.class,
                () -> this.studentService.register(username, "password", "password", "name", "surname", Role.ROLE_USER));
        Mockito.verify(this.studentService).register(username, "password", "password", "name", "surname", Role.ROLE_USER);
    }
}
