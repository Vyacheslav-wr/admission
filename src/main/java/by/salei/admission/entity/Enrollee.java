package by.salei.admission.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "enrollee")
@Data
public class Enrollee extends AbstractEntity {

    private String firstName;
    private String lastName;
    private Integer score;
    private String email;

    @Enumerated(EnumType.STRING)
    private EnrolleeStatus status;

    @OneToMany(mappedBy = "enrollee")
    private List<EnrolleeSubject> enrolleeSubjects;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
