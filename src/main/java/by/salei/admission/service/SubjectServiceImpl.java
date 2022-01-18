package by.salei.admission.service;

import by.salei.admission.dao.SubjectDao;
import by.salei.admission.dto.SubjectCreateDto;
import by.salei.admission.dto.SubjectGetDto;
import by.salei.admission.dto.SubjectUpdateDto;
import by.salei.admission.entity.Subject;
import by.salei.admission.entity.SubjectType;
import by.salei.admission.mapper.SubjectMapper;
import by.salei.admission.service.api.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectDao subjectDao;

    private final SubjectMapper subjectMapper;

    @Autowired
    public SubjectServiceImpl(SubjectDao subjectDao, SubjectMapper subjectMapper) {
        this.subjectDao = subjectDao;
        this.subjectMapper = subjectMapper;
    }

    @Override
    public SubjectGetDto save(SubjectCreateDto entity) {

        log.info("Executing method save()");
        log.debug("Executing method save() for Subject: {}", entity.toString());

        Subject subject = subjectDao.save(subjectMapper.subjectCreateDtoToSubject(entity));

        return subjectMapper.subjectToSubjectGetDto(subject);
    }

    @Override
    public SubjectUpdateDto update(SubjectUpdateDto newEntity) {

        log.info("Executing method update()");
        log.debug("Executing method update() for Subject: {}", newEntity.toString());

        subjectDao.save(subjectMapper.subjectUpdateDtoToSubject(newEntity));

        return newEntity;
    }

    @Override
    public void delete(Long id) {

        log.info("Executing method delete()");
        log.debug("Executing method delete() for Subject with id = {}", id);

        subjectDao.deleteById(id);
    }

    @Override
    public SubjectGetDto getById(Long id) throws EntityNotFoundException {

        log.info("Executing method getById()");
        log.debug("Executing method getById() for Subject with id = {}", id);

        Subject subject = subjectDao.findById(id).orElse(null);

        if(subject == null){

            log.warn("Subject with id = {} does not exist", id);
            throw new EntityNotFoundException();
        }

        return subjectMapper.subjectToSubjectGetDto(subject);
    }

    @Override
    public List<SubjectGetDto> getAll() {

        log.info("Executing method getByAll()");

        List<Subject> subjects = new ArrayList<>();

        subjectDao.findAll().forEach(subjects::add);

        return subjects
                .stream()
                .map(subjectMapper::subjectToSubjectGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectGetDto findSubjectByType(String type) throws EntityNotFoundException {

        log.info("Executing method findSubjectByType()");

        Subject subject = subjectDao.findSubjectByType(SubjectType.valueOf(type));

        if(subject == null){
            log.warn("Subject with type = {} does not exist", type);
            throw new EntityNotFoundException();
        }

        return subjectMapper.subjectToSubjectGetDto(subject);
    }
}
