package by.salei.admission.service;

import by.salei.admission.comparator.EnrolleeScoreComparator;
import by.salei.admission.dao.EnrolleeDao;
import by.salei.admission.dao.FacultyDao;
import by.salei.admission.entity.Enrollee;
import by.salei.admission.entity.EnrolleeStatus;
import by.salei.admission.entity.Faculty;
import by.salei.admission.service.api.AdmissionService;
import by.salei.admission.service.api.EnrolleeSubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdmissionServiceImpl implements AdmissionService {

    private final EnrolleeSubjectService enrolleeSubjectService;

    private final FacultyDao facultyDao;

    private final EnrolleeDao enrolleeDao;

    @Autowired
    public AdmissionServiceImpl(EnrolleeSubjectService enrolleeSubjectService,
                                FacultyDao facultyDao, EnrolleeDao enrolleeDao) {
        this.enrolleeSubjectService = enrolleeSubjectService;
        this.facultyDao = facultyDao;
        this.enrolleeDao = enrolleeDao;
    }

    @Override
    public void startAdmission() {

        List<Faculty> faculties = new ArrayList<>();

        facultyDao.findAll().forEach(faculties::add);

        for (Faculty faculty : faculties) {

            List<Enrollee> enrolleeList = faculty.getEnrollees()
                    .stream()
                    .filter(entity -> entity.getStatus().equals(EnrolleeStatus.APPROVED))
                    .collect(Collectors.toList());

            enrolleeList.forEach(entity -> entity
                    .setScore(enrolleeSubjectService.calculateGeneralEnrolleeScore(entity.getId())));

            enrolleeList.sort(new EnrolleeScoreComparator());

            finishAdmission(enrolleeList, faculty.getStudentsSpots());
        }

    }

    public void finishAdmission(List<Enrollee> enrolleeList, Integer spots) {

        String status = "ENROLLED";

        for (Enrollee enrollee : enrolleeList) {

            if(spots == 0){
                status = "NOT_ENROLLED";
            }

            enrollee.setStatus(EnrolleeStatus.valueOf(status));

            enrolleeDao.save(enrollee);

            spots--;
        }
    }
}
