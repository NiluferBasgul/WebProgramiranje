package wp.lab.service;

import wp.lab.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    Optional<Teacher> findById(Long id);
    List<Teacher> findAll();
    Optional<Teacher> save(Long id, String name, String username);
    void deleteById(Long id);
}
