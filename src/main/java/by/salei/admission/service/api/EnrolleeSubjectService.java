package by.salei.admission.service.api;

import by.salei.admission.dto.EnrolleeSubjectCreateDto;
import by.salei.admission.dto.EnrolleeSubjectGetDto;
import by.salei.admission.dto.EnrolleeSubjectUpdateDto;

import java.util.List;

public interface EnrolleeSubjectService extends Service<EnrolleeSubjectCreateDto,
        EnrolleeSubjectGetDto, EnrolleeSubjectUpdateDto>{

    Integer calculateGeneralEnrolleeScore(Long id);
}
