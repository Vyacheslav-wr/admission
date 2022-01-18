package by.salei.admission.dao;

import by.salei.admission.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

    User findUserByUsername(String name);
}
