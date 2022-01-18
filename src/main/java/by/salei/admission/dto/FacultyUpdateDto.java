package by.salei.admission.dto;

import lombok.Data;

import java.util.List;

@Data
public class FacultyUpdateDto extends AbstractUpdateDto{

    private Long id;
    private Integer studentsSpots;
    private List<SubjectGetDto> subjects;
    private EnrolleeGetDto enrollee;
}
