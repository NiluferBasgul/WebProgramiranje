package wp.lab.repository.impl;

import wp.lab.bootstrap.DataHolder;
import wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;
import wp.lab.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@Repository
public abstract class TeacherRepositoryImpl implements TeacherRepository {

    private List<Teacher> teachers;

    public List<Teacher> findAll() {
        return this.teachers;
    }

    public Optional<Teacher> findById(Long id){
        return DataHolder.teacherList.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public Optional<Teacher> save(String name, String surname) {
        Teacher teacher = new Teacher(1L,name,surname);
        DataHolder.teacherList.add(teacher);
        return Optional.of(teacher);
    }

    public void deleteById(Long id){
        DataHolder.teacherList.removeIf(i -> i.getId().equals(id));
    }
}

