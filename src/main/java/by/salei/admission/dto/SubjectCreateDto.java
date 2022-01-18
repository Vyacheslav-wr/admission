package by.salei.admission.dto;

import by.salei.admission.entity.SubjectType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SubjectCreateDto extends AbstractCreateDto {

    private SubjectType type;
}
