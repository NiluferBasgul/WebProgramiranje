package wp.lab.repository;

import wp.lab.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    public List<Teacher> findAll();

    public Optional<Teacher> findById(Long id);

    public Optional<Teacher> save(String name, String surname);

    public void deleteById(Long id);


}
