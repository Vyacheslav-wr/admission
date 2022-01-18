package by.salei.admission.service;

import by.salei.admission.dao.UserDao;
import by.salei.admission.dto.UserCreateDto;
import by.salei.admission.dto.UserGetDto;
import by.salei.admission.dto.UserUpdateDto;
import by.salei.admission.entity.User;
import by.salei.admission.mapper.UserMapper;
import by.salei.admission.service.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserDao userDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @Override
    public UserGetDto save(UserCreateDto entity) {

        log.info("Executing method save()");
        log.debug("Executing method save() for user: {}", entity.toString());

        User user = userDao.save(userMapper.userCreateDtoToUser(entity));

        return userMapper.userToUserGetDto(user);
    }

    @Override
    public UserUpdateDto update(UserUpdateDto newEntity) {

        log.info("Executing method update()");
        log.debug("Executing method update() for user: {}", newEntity.toString());

        userDao.save(userMapper.userUpdateDtoToUser(newEntity));

        return newEntity;
    }

    @Override
    public void delete(Long id) {

        log.info("Executing method delete()");
        log.debug("Executing method delete() for user with id = {}", id);

        userDao.deleteById(id);
    }

    @Override
    public UserGetDto getById(Long id) throws EntityNotFoundException {

        log.info("Executing method getById()");
        log.debug("Executing method getById() for user with id = {}", id);

        User user = userDao.findById(id).orElse(null);

        if (user == null) {

            log.warn("User with id = {} does not exist", id);
            throw new EntityNotFoundException();
        }

        return userMapper.userToUserGetDto(user);
    }

    @Override
    public List<UserGetDto> getAll() {

        log.info("Executing method getByAll()");

        List<User> users = new ArrayList<>();

        userDao.findAll().forEach(users::add);

        return users
                .stream()
                .map(userMapper::userToUserGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserGetDto getByUsername(String username) {

        log.info("Executing method getByUserName");

        User user = userDao.findUserByUsername(username);

        return userMapper.userToUserGetDto(user);
    }
}
