package by.salei.admission.service;

import by.salei.admission.dao.EnrolleeDao;
import by.salei.admission.dao.EnrolleeSubjectDao;
import by.salei.admission.dto.EnrolleeSubjectCreateDto;
import by.salei.admission.dto.EnrolleeSubjectGetDto;
import by.salei.admission.dto.EnrolleeSubjectUpdateDto;
import by.salei.admission.entity.Enrollee;
import by.salei.admission.entity.EnrolleeSubject;
import by.salei.admission.mapper.EnrolleeSubjectMapper;
import by.salei.admission.service.api.EnrolleeService;
import by.salei.admission.service.api.EnrolleeSubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EnrolleeSubjectServiceImpl implements EnrolleeSubjectService {

    private final EnrolleeSubjectDao enrolleeSubjectDao;

    private final EnrolleeDao enrolleeDao;

    private final EnrolleeSubjectMapper enrolleeSubjectMapper;


    @Autowired
    public EnrolleeSubjectServiceImpl(EnrolleeSubjectDao enrolleeSubjectDao,
                                      EnrolleeDao enrolleeDao, EnrolleeSubjectMapper enrolleeSubjectMapper,
                                      EnrolleeService enrolleeService) {
        this.enrolleeSubjectDao = enrolleeSubjectDao;
        this.enrolleeDao = enrolleeDao;
        this.enrolleeSubjectMapper = enrolleeSubjectMapper;

    }

    @Override
    public EnrolleeSubjectGetDto save(EnrolleeSubjectCreateDto entity) {

        log.info("Executing method save()");
        log.debug("Executing method save() for enrolleeSubject: {}", entity.toString());

        EnrolleeSubject enrolleeSubject = enrolleeSubjectDao.save(enrolleeSubjectMapper
                .EnrolleeSubjectCreateDtoToEnrolleeSubject(entity));

        return enrolleeSubjectMapper.EnrolleeSubjectToEnrolleeSubjectGetDto(enrolleeSubject);
    }

    @Override
    public EnrolleeSubjectUpdateDto update(EnrolleeSubjectUpdateDto newEntity) {

        log.info("Executing method update()");
        log.debug("Executing method update() for enrolleeSubject : {}", newEntity.toString());

        enrolleeSubjectDao.save(enrolleeSubjectMapper
                .EnrolleeSubjectUpdateDtoToEnrolleeSubject(newEntity));

        return newEntity;
    }

    @Override
    public void delete(Long id) {

        log.info("Executing method delete()");
        log.debug("Executing method delete() for enrollee with id = {}", id);

        enrolleeSubjectDao.deleteById(id);
    }

    @Override
    public EnrolleeSubjectGetDto getById(Long id) throws EntityNotFoundException {

        log.info("Executing method getById()");
        log.debug("Executing method getById() for enrolleeSubject with id = {}", id);

        EnrolleeSubject enrolleeSubject = enrolleeSubjectDao.findById(id).orElse(null);

        if(enrolleeSubject == null){

            log.warn("EnrolleeSubject with id = {} does not exist", id);
            throw new EntityNotFoundException();
        }

        return enrolleeSubjectMapper.EnrolleeSubjectToEnrolleeSubjectGetDto(enrolleeSubject);
    }

    @Override
    public List<EnrolleeSubjectGetDto> getAll() {

        log.info("Executing method getByAll()");

        List<EnrolleeSubject> enrolleeSubjects = new ArrayList<>();

        enrolleeSubjectDao.findAll().forEach(enrolleeSubjects::add);

        return enrolleeSubjects
                .stream()
                .map(enrolleeSubjectMapper::EnrolleeSubjectToEnrolleeSubjectGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public Integer calculateGeneralEnrolleeScore(Long id) throws EntityNotFoundException {

        Enrollee enrollee = enrolleeDao.findById(id).orElse(null);

        if (enrollee == null) {

            log.warn("EnrolleeSubject with id = {} does not exist", id);
            throw new EntityNotFoundException();
        }

        int score = enrollee.getEnrolleeSubjects()
                .stream()
                .mapToInt(EnrolleeSubject::getScore)
                .sum();

        return score + enrollee.getScore();
    }

}
