package by.salei.admission.dto;

import by.salei.admission.entity.SubjectType;
import lombok.Data;

@Data
public class SubjectUpdateDto extends AbstractUpdateDto {

    private Long id;
    private SubjectType type;
}
