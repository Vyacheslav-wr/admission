package by.salei.admission.service;

import by.salei.admission.dao.FacultyDao;
import by.salei.admission.entity.Faculty;
import by.salei.admission.service.api.FacultyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FacultyServiceTest {

    @MockBean
    FacultyDao facultyDao;

    @Autowired
    FacultyService facultyService;

    @Test(expected = EntityNotFoundException.class)
    public void getByIdTest(){

        Long id = 1L;

        Faculty faculty = new Faculty();

        facultyService.getById(id);

        Mockito.when(facultyDao.findById(id)).thenReturn(Optional.of(faculty));
    }
}
