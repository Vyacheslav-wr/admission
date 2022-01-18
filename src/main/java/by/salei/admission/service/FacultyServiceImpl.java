package by.salei.admission.service;

import by.salei.admission.dao.FacultyDao;
import by.salei.admission.dao.SubjectDao;
import by.salei.admission.dto.FacultyCreateDto;
import by.salei.admission.dto.FacultyGetDto;
import by.salei.admission.dto.FacultyUpdateDto;
import by.salei.admission.entity.Faculty;
import by.salei.admission.mapper.FacultyMapper;
import by.salei.admission.service.api.FacultyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyDao facultyDao;

    private final FacultyMapper facultyMapper;

    @Autowired
    public FacultyServiceImpl(FacultyDao facultyDao, SubjectDao subjectDao, FacultyMapper facultyMapper) {
        this.facultyDao = facultyDao;
        this.facultyMapper = facultyMapper;
    }

    @Override
    public FacultyGetDto save(FacultyCreateDto entity) {

        log.info("Executing method save()");
        log.debug("Executing method save() for faculty: {}", entity.toString());

        Faculty faculty = facultyDao.save(facultyMapper.facultyCreateDtoToFaculty(entity));

        return facultyMapper.facultyToFacultyGetDto(faculty);
    }

    @Override
    public FacultyUpdateDto update(FacultyUpdateDto newEntity) {

        log.info("Executing method update()");
        log.debug("Executing method update() for faculty: {}", newEntity.toString());

        facultyDao.save(facultyMapper.facultyUpdateDtoToFaculty(newEntity));

        return newEntity;
    }

    @Override
    public void delete(Long id) {

        log.info("Executing method delete()");
        log.debug("Executing method delete() for faculty with id = {}", id);

        facultyDao.deleteById(id);
    }

    @Override
    public FacultyGetDto getById(Long id) throws EntityNotFoundException {

        log.info("Executing method getById()");
        log.debug("Executing method getById() for faculty with id = {}", id);

        Faculty faculty = facultyDao.findById(id).orElse(null);

        if (faculty == null) {

            log.warn("Faculty with id = {} does not exist", id);
            throw new EntityNotFoundException();
        }

        return facultyMapper.facultyToFacultyGetDto(faculty);
    }

    @Override
    public List<FacultyGetDto> getAll() {

        log.info("Executing method getByAll()");

        List<Faculty> faculties = new ArrayList<>();

        facultyDao.findAll().forEach(faculties::add);

        return faculties
                .stream()
                .map(facultyMapper::facultyToFacultyGetDto)
                .collect(Collectors.toList());
    }
}
