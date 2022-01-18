package by.salei.admission.service.api;

import by.salei.admission.dto.UserCreateDto;
import by.salei.admission.dto.UserGetDto;
import by.salei.admission.dto.UserUpdateDto;

public interface UserService extends Service<UserCreateDto, UserGetDto, UserUpdateDto>{

    UserGetDto getByUsername(String username);
}
