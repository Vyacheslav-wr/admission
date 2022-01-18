package by.salei.admission.service;

import by.salei.admission.dao.EnrolleeDao;
import by.salei.admission.dto.EnrolleeCreateDto;
import by.salei.admission.dto.EnrolleeGetDto;
import by.salei.admission.entity.Enrollee;
import by.salei.admission.entity.EnrolleeStatus;
import by.salei.admission.mapper.EnrolleeMapper;
import by.salei.admission.service.api.EnrolleeService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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
public class EnrolleeServiceTest {

    @MockBean
    EnrolleeDao enrolleeDao;

    @Autowired
    EnrolleeService enrolleeService;

    @MockBean
    EnrolleeMapper enrolleeMapper;

    @Test
    public void getNameTest() {

        EnrolleeCreateDto enrolleeCreateDto = EnrolleeCreateDto.builder()
                .firstName("Name")
                .build();

        Enrollee enrollee = new Enrollee();
        enrollee.setFirstName(enrolleeCreateDto.getFirstName());

        EnrolleeGetDto enrolleeGetDto = enrolleeService.save(enrolleeCreateDto);

        EnrolleeGetDto enrolleeGetDto1 = EnrolleeGetDto.builder()
                .firstName("Name")
                .build();

        Mockito.when(enrolleeDao.save(enrollee)).thenReturn(enrollee);
        Mockito.when(enrolleeMapper.enrolleeCreateDtoToEnrollee(enrolleeCreateDto))
                .thenReturn(enrollee);
        Mockito.when(enrolleeMapper.enrolleeToEnrolleeGetDto(enrollee))
                .thenReturn(enrolleeGetDto);

        Assertions.assertEquals("Name", enrolleeGetDto1.getFirstName());
    }

    @Test(expected = EntityNotFoundException.class)
    public void isAlreadyRegisteredTest() {

        Long id =1L;

        Enrollee enrollee = new Enrollee();
        enrollee.setStatus(EnrolleeStatus.REGISTERED);

        Boolean result = enrolleeService.isAlreadyRegistered(id);

        Mockito.when(enrolleeDao.findById(id)).thenReturn(Optional.of(enrollee));
    }
}
