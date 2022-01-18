package by.salei.admission.dto;


import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrolleeUpdateDto extends AbstractUpdateDto {

    private Long id;
    private String FirstName;
    private String lastName;
    private Integer score;
    private String email;
    private String status;
    private UserGetDto user;
    private FacultyGetDto faculty;
    private List<EnrolleeSubjectGetDto> subjects;

}
