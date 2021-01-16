package wp.lab.service.impl;

import wp.lab.model.Teacher;
import wp.lab.repository.TeacherRepository;
import wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        return this.teacherRepository.findById(id);
    }

    @Override
    public List<Teacher> findAll() {
        return this.teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> save(Long id, String name, String surname) {
        return Optional.of(this.teacherRepository.save(new Teacher(id, name, surname)));
    }

    @Override
    public void deleteById(Long id) {
        this.teacherRepository.deleteById(id);
    }
}
