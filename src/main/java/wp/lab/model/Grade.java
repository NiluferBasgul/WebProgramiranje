package wp.lab.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Character grade;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;
}
