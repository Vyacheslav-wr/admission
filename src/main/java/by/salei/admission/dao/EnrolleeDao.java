package by.salei.admission.dao;

import by.salei.admission.entity.Enrollee;
import by.salei.admission.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnrolleeDao extends CrudRepository<Enrollee, Long> {

    List<Enrollee> findAllByFirstName(String name);

    Enrollee findEnrolleeByUser(User user);
}
