package by.salei.admission.mapper;

import by.salei.admission.dto.UserCreateDto;
import by.salei.admission.dto.UserGetDto;
import by.salei.admission.dto.UserUpdateDto;
import by.salei.admission.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User userCreateDtoToUser(UserCreateDto userCreateDto);

    UserGetDto userToUserGetDto(User user);

    User userUpdateDtoToUser(UserUpdateDto userUpdateDto);
}
