package by.salei.admission.service.api;

import by.salei.admission.dto.SubjectCreateDto;
import by.salei.admission.dto.SubjectGetDto;
import by.salei.admission.dto.SubjectUpdateDto;

public interface SubjectService extends Service<SubjectCreateDto, SubjectGetDto, SubjectUpdateDto> {


    SubjectGetDto findSubjectByType(String type);
}
