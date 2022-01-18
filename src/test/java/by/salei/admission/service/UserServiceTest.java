package by.salei.admission.service;

import by.salei.admission.dao.UserDao;
import by.salei.admission.dto.UserGetDto;
import by.salei.admission.entity.User;
import by.salei.admission.service.api.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @MockBean
    UserDao userDao;

    @Autowired
    UserService userService;

    @Test
    public void getByUsernameTest(){

        String username = "Andrei";

        User user = new User();
        user.setUsername(username);

        Mockito.when(userDao.findUserByUsername(username)).thenReturn(user);

        UserGetDto userGetDto = userService.getByUsername(username);

        Assertions.assertEquals(username, userGetDto.getUsername());
    }
}
