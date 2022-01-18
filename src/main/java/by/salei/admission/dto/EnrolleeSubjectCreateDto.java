package by.salei.admission.dto;

import by.salei.admission.entity.Enrollee;
import by.salei.admission.entity.SubjectType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EnrolleeSubjectCreateDto extends AbstractCreateDto {

    private SubjectGetDto subject_type;
    private Integer score;
    private EnrolleeGetDto enrollee;
}
