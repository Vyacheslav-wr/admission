package by.salei.admission.service;

import by.salei.admission.dao.EnrolleeSubjectDao;
import by.salei.admission.dto.EnrolleeSubjectGetDto;
import by.salei.admission.entity.EnrolleeSubject;
import by.salei.admission.mapper.EnrolleeSubjectMapper;
import by.salei.admission.service.api.EnrolleeSubjectService;
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
public class EnrolleeSubjectServiceTest {

    @Autowired
    EnrolleeSubjectService enrolleeSubjectService;

    @MockBean
    EnrolleeSubjectDao enrolleeSubjectDao;

    @MockBean
    EnrolleeSubjectMapper enrolleeSubjectMapper;

    @Test(expected = EntityNotFoundException.class)
    public void getEnrolleeSubjectByIdTest(){

        Long id = 1L;

        EnrolleeSubject enrollee = new EnrolleeSubject();
        enrollee.setId(id);

        EnrolleeSubjectGetDto enrolleeGetDto = enrolleeSubjectService.getById(1L);

        Mockito.when(enrolleeSubjectDao.findById(id)).thenReturn(Optional.of(enrollee));
    }



}
