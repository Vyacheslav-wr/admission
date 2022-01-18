package by.salei.admission.dto;

import by.salei.admission.entity.EnrolleeStatus;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class EnrolleeCreateDto extends AbstractCreateDto {

    private String firstName;
    private String lastName;
    private Integer score;
    private String email;
    private EnrolleeStatus status;
}
