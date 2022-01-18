package by.salei.admission.dao;

import by.salei.admission.entity.Faculty;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FacultyDao extends CrudRepository<Faculty, Long> {

}
