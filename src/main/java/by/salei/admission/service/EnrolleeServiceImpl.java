package by.salei.admission.service;

import by.salei.admission.dao.EnrolleeDao;
import by.salei.admission.dao.FacultyDao;
import by.salei.admission.dao.UserDao;
import by.salei.admission.dto.EnrolleeCreateDto;
import by.salei.admission.dto.EnrolleeGetDto;
import by.salei.admission.dto.EnrolleeUpdateDto;
import by.salei.admission.entity.Enrollee;
import by.salei.admission.entity.EnrolleeStatus;
import by.salei.admission.entity.Faculty;
import by.salei.admission.entity.User;
import by.salei.admission.mapper.EnrolleeMapper;
import by.salei.admission.service.api.EnrolleeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EnrolleeServiceImpl implements EnrolleeService {

    private final EnrolleeDao enrolleeDao;

    private final UserDao userDao;

    private final EnrolleeMapper enrolleeMapper;

    @Autowired
    public EnrolleeServiceImpl(EnrolleeDao enrolleeDao, UserDao userDao,
                               FacultyDao facultyDao, EnrolleeMapper enrolleeMapper) {
        this.enrolleeDao = enrolleeDao;
        this.userDao = userDao;
        this.enrolleeMapper = enrolleeMapper;
    }

    @Override
    public EnrolleeGetDto save(EnrolleeCreateDto entity) {

        log.info("Executing method save()");
        log.debug("Executing method save() for enrollee: {}", entity.toString());

        Enrollee enrollee = enrolleeDao.save(enrolleeMapper.enrolleeCreateDtoToEnrollee(entity));

        return enrolleeMapper.enrolleeToEnrolleeGetDto(enrollee);
    }

    @Override
    public EnrolleeUpdateDto update(EnrolleeUpdateDto newEntity) {

        log.info("Executing method update()");
        log.debug("Executing method update() for enrollee: {}", newEntity.toString());

        enrolleeDao.save(enrolleeMapper.enrolleeUpdateDtoToEnrollee(newEntity));

        return newEntity;
    }

    @Override
    public void delete(Long id) {

        log.info("Executing method delete()");
        log.debug("Executing method delete() for enrollee with id = {}", id);

        enrolleeDao.deleteById(id);
    }

    @Override
    public EnrolleeGetDto getById(Long id) throws EntityNotFoundException {

        log.info("Executing method getById()");
        log.debug("Executing method getById() for enrollee with id = {}", id);

        Enrollee enrollee = enrolleeDao.findById(id).orElse(null);

        if(enrollee == null){
            log.warn("Enrollee with id = {} does not exist", id);
            throw new EntityNotFoundException();
        }

        return enrolleeMapper.enrolleeToEnrolleeGetDto(enrollee);
    }

    @Override
    public List<EnrolleeGetDto> getAll() throws EntityNotFoundException {

        log.info("Executing method getByAll()");

        List<Enrollee> enrollees = new ArrayList<>();

        enrolleeDao.findAll().forEach(enrollees::add);

        return enrollees
                .stream()
                .map(enrolleeMapper::enrolleeToEnrolleeGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public EnrolleeGetDto findByUserId(Long id) {

        log.info("Executing method findByUserId()");
        log.debug("Executing method findByUserId() for enrollee with user_id = {}", id);

        User user = userDao.findById(id).orElse(null);

        Enrollee enrollee = enrolleeDao.findEnrolleeByUser(user);

        return enrolleeMapper.enrolleeToEnrolleeGetDto(enrollee);
    }

    @Override
    public Boolean isAlreadyRegistered(Long id) throws EntityNotFoundException {

        log.info("Executing method isAlreadyRegistered()");
        log.debug("Executing method isAlreadyRegistered() for enrollee with id = {}", id);

        Enrollee enrollee = enrolleeDao.findById(id).orElse(null);

        if (enrollee == null) {
            log.warn("Enrollee with id = {} does not exist", id);
            throw new EntityNotFoundException();
        }

        return enrollee.getFaculty() != null;
    }

    @Override
    public void updateEnrolleeUser(Long id, Long user_id) throws EntityNotFoundException {

        log.info("Executing method updateEnrolleeUser()");
        log.debug("Executing method updateEnrolleeUser() for enrollee " +
                "with id = {} and user_id = {}", id, user_id);

        Enrollee enrollee = enrolleeDao.findById(id).orElse(null);

        User user = userDao.findById(user_id).orElse(null);

        if (enrollee == null || user == null) {
            if (enrollee == null) {
                log.warn("Enrollee with id = {} does not exist", id);
            }
            if (user == null) {
                log.warn("User with id = {} does not exist", user_id);
            }
            throw new EntityNotFoundException();
        }

        enrollee.setUser(user);

        enrolleeDao.save(enrollee);

    }

    @Override
    public void updateEnrolleeFaculty(Long id, Faculty faculty) throws EntityNotFoundException {

        log.info("Executing method updateEnrolleeFaculty()");
        log.debug("Executing method updateEnrolleeFaculty() for enrollee " +
                "with id = {} and faculty_id = {}", id, faculty.getId());

        Enrollee enrollee = enrolleeDao.findById(id).orElse(null);

        if (enrollee == null) {
            log.warn("Enrollee with id = {} does not exist", id);
            throw new EntityNotFoundException();
        }

        enrollee.setFaculty(faculty);

        enrolleeDao.save(enrollee);
    }

    @Override
    public void updateEnrolleeStatus(Long id, EnrolleeStatus enrolleeStatus) throws EntityNotFoundException {

        log.info("Executing method updateEnrolleeStatus()");
        log.debug("Executing method updateEnrolleeStatus() for enrollee " +
                "with id = {}", id);


        Enrollee enrollee = enrolleeDao.findById(id).orElse(null);

        if (enrollee == null) {
            log.warn("Enrollee with id = {} does not exist", id);
            throw new EntityNotFoundException();
        }

        enrollee.setStatus(enrolleeStatus);

        enrolleeDao.save(enrollee);
    }
}
