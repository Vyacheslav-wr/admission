package by.salei.admission.dao;

import by.salei.admission.entity.Subject;
import by.salei.admission.entity.SubjectType;
import org.springframework.data.repository.CrudRepository;

public interface SubjectDao extends CrudRepository<Subject, Long> {

    Subject findSubjectByType(SubjectType subjectType);
}
