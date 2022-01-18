package by.salei.admission.service.api;

import java.util.List;

public interface EnrolleeSubjectsCreationService {

    boolean setNewEnrolleeSubjects(Long faculty_id, Long enrollee_id, List<Integer> scores);
}
