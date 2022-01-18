package by.salei.admission.service.api;

import by.salei.admission.dto.EnrolleeCreateDto;
import by.salei.admission.dto.EnrolleeGetDto;
import by.salei.admission.dto.EnrolleeUpdateDto;
import by.salei.admission.dto.UserGetDto;
import by.salei.admission.entity.EnrolleeStatus;
import by.salei.admission.entity.Faculty;

public interface EnrolleeService extends Service<EnrolleeCreateDto, EnrolleeGetDto, EnrolleeUpdateDto>{

    EnrolleeGetDto findByUserId(Long id);

    Boolean isAlreadyRegistered(Long id);

    void updateEnrolleeUser(Long id, Long user_id);

    void updateEnrolleeFaculty(Long id, Faculty faculty);

    void updateEnrolleeStatus(Long id, EnrolleeStatus enrolleeStatus);
}
