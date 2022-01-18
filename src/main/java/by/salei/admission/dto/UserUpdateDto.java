package by.salei.admission.dto;

import by.salei.admission.entity.UserRole;
import lombok.Data;

@Data
public class UserUpdateDto extends AbstractUpdateDto {

    private Long id;
    private String username;
    private String password;
    private UserRole role;
    private EnrolleeGetDto enrollee;
}
