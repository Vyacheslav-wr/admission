package by.salei.admission.mapper;

import by.salei.admission.dto.EnrolleeSubjectCreateDto;
import by.salei.admission.dto.EnrolleeSubjectGetDto;
import by.salei.admission.dto.EnrolleeSubjectUpdateDto;
import by.salei.admission.entity.EnrolleeSubject;
import org.mapstruct.Mapper;

@Mapper
public interface EnrolleeSubjectMapper {

    EnrolleeSubject EnrolleeSubjectCreateDtoToEnrolleeSubject(EnrolleeSubjectCreateDto enrolleeSubjectCreateDto);

    EnrolleeSubjectGetDto EnrolleeSubjectToEnrolleeSubjectGetDto(EnrolleeSubject enrolleeSubject);

    EnrolleeSubject EnrolleeSubjectUpdateDtoToEnrolleeSubject(EnrolleeSubjectUpdateDto enrolleeSubjectUpdateDto);
}
