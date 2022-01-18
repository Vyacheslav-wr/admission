package by.salei.admission.mapper;

import by.salei.admission.dto.FacultyCreateDto;
import by.salei.admission.dto.FacultyGetDto;
import by.salei.admission.dto.FacultyUpdateDto;
import by.salei.admission.entity.Faculty;
import org.mapstruct.Mapper;

@Mapper
public interface FacultyMapper {

    Faculty facultyCreateDtoToFaculty(FacultyCreateDto facultyCreateDto);

    FacultyGetDto facultyToFacultyGetDto(Faculty faculty);

    Faculty facultyUpdateDtoToFaculty(FacultyUpdateDto facultyUpdateDto);
}
