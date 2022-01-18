package by.salei.admission.service;

import by.salei.admission.dao.SubjectDao;
import by.salei.admission.dto.SubjectGetDto;
import by.salei.admission.entity.Subject;
import by.salei.admission.entity.SubjectType;
import by.salei.admission.service.api.SubjectService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SubjectServiceTest {

    @MockBean
    SubjectDao subjectDao;

    @Autowired
    SubjectService subjectService;

    @Test
    public void findSubjectByTypeTest(){

        Subject subject = new Subject();
        subject.setType(SubjectType.MATHEMATICS);

        Mockito.when(subjectDao.findSubjectByType(SubjectType.MATHEMATICS)).thenReturn(subject);

        SubjectGetDto subjectGetDto = subjectService.findSubjectByType("MATHEMATICS");

        Assertions.assertEquals(SubjectType.MATHEMATICS, subjectGetDto.getType());
    }
}
