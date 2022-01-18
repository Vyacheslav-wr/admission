package by.salei.admission.mapper;


import by.salei.admission.dto.EnrolleeCreateDto;
import by.salei.admission.dto.EnrolleeGetDto;
import by.salei.admission.dto.EnrolleeUpdateDto;
import by.salei.admission.entity.Enrollee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnrolleeMapper {

    Enrollee enrolleeCreateDtoToEnrollee(EnrolleeCreateDto enrolleeCreateDto);

    EnrolleeGetDto enrolleeToEnrolleeGetDto(Enrollee enrollee);

    Enrollee enrolleeUpdateDtoToEnrollee(EnrolleeUpdateDto enrolleeUpdateDto);
}
