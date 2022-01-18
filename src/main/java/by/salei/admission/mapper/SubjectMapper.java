package by.salei.admission.mapper;

import by.salei.admission.dto.SubjectCreateDto;
import by.salei.admission.dto.SubjectGetDto;
import by.salei.admission.dto.SubjectUpdateDto;
import by.salei.admission.entity.Subject;
import org.mapstruct.Mapper;

@Mapper
public interface SubjectMapper {

    Subject subjectCreateDtoToSubject(SubjectCreateDto subjectCreateDto);

    SubjectGetDto subjectToSubjectGetDto(Subject subject);

    Subject subjectUpdateDtoToSubject(SubjectUpdateDto subjectUpdateDto);
}
