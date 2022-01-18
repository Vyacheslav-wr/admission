package by.salei.admission.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EnrolleeSubjectGetDto extends AbstractGetDto {

    private Long id;
    private Integer score;
    private SubjectGetDto subject_type;
}
