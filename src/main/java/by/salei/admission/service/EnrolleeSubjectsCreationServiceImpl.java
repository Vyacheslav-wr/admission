package by.salei.admission.service;

import by.salei.admission.dao.EnrolleeDao;
import by.salei.admission.dao.EnrolleeSubjectDao;
import by.salei.admission.dao.FacultyDao;
import by.salei.admission.entity.Enrollee;
import by.salei.admission.entity.EnrolleeSubject;
import by.salei.admission.entity.Faculty;
import by.salei.admission.entity.Subject;
import by.salei.admission.service.api.EnrolleeService;
import by.salei.admission.service.api.EnrolleeSubjectsCreationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EnrolleeSubjectsCreationServiceImpl implements EnrolleeSubjectsCreationService {

    private final FacultyDao facultyDao;

    private final EnrolleeService enrolleeService;

    private final EnrolleeDao enrolleeDao;

    private final EnrolleeSubjectDao enrolleeSubjectDao;

    @Autowired
    public EnrolleeSubjectsCreationServiceImpl(FacultyDao facultyDao, EnrolleeService enrolleeService,
                                               EnrolleeDao enrolleeDao, EnrolleeSubjectDao enrolleeSubjectDao) {
        this.facultyDao = facultyDao;
        this.enrolleeService = enrolleeService;
        this.enrolleeDao = enrolleeDao;
        this.enrolleeSubjectDao = enrolleeSubjectDao;
    }

    @Override
    public boolean setNewEnrolleeSubjects(Long faculty_id, Long enrollee_id, List<Integer> scores)
            throws EntityNotFoundException {

        log.info("Executing method setNewEnrolleeSubjects()");
        log.debug("Executing method setNewEnrolleeSubjects() for enrollee " +
                "with id = {} and faculty_id = {}", enrollee_id, faculty_id);

        Faculty faculty = facultyDao.findById(faculty_id).orElse(null);

        Enrollee enrollee = enrolleeDao.findById(enrollee_id).orElse(null);

        if(faculty == null || enrollee == null) {

            if(faculty == null){
                log.warn("Faculty with id = {} does not exist", faculty_id);
            }
            if(enrollee == null){
                log.warn("enrollee with id = {} does not exist", enrollee_id);
            }
            throw new EntityNotFoundException();
        }

        if (!enrolleeService.isAlreadyRegistered(enrollee_id)) {

            List<Subject> subjects = faculty.getSubjects();

            enrolleeService.updateEnrolleeFaculty(enrollee_id, faculty);

            List<EnrolleeSubject> enrolleeSubjects = new ArrayList<>();

            for (int i = 0; i < 3; i++) {

                EnrolleeSubject enrolleeSubject = new EnrolleeSubject();

                enrolleeSubject.setScore(scores.get(i));

                enrolleeSubject.setSubject_type(subjects.get(i));

                enrolleeSubject.setEnrollee(enrollee);

                enrolleeSubjectDao.save(enrolleeSubject);
            }

            enrollee.setEnrolleeSubjects(enrolleeSubjects);

            enrolleeDao.save(enrollee);

            return true;
        }

        return false;
    }
}
