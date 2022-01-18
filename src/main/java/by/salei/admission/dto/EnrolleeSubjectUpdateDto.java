package by.salei.admission.dto;

import lombok.Data;

@Data
public class EnrolleeSubjectUpdateDto extends AbstractUpdateDto {

    private Long id;
    private Integer score;
    private SubjectGetDto subject_type;
    private EnrolleeGetDto enrolleeGetDto;
}
