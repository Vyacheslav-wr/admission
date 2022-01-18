package by.salei.admission.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class FacultyGetDto extends AbstractGetDto {

    private Long id;
    private String name;
    private Integer studentsSpots;
    private String image;
    private List<SubjectGetDto> subjects;
}
