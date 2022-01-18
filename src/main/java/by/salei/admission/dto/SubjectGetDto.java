package by.salei.admission.dto;

import by.salei.admission.entity.SubjectType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class SubjectGetDto extends AbstractGetDto {

    private Long id;
    private SubjectType type;
}
