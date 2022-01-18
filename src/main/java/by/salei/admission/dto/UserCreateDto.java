package by.salei.admission.dto;

import by.salei.admission.entity.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserCreateDto extends AbstractCreateDto {

    private String username;
    private String password;
    private UserRole role;
}
