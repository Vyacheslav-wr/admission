package by.salei.admission.controller;

import by.salei.admission.dto.UserGetDto;
import by.salei.admission.entity.UserRole;
import by.salei.admission.service.api.EnrolleeService;
import by.salei.admission.service.api.EnrolleeSubjectService;
import by.salei.admission.service.api.EnrolleeSubjectsCreationService;
import by.salei.admission.service.api.FacultyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class EnrolleeSubjectController {

    private final EnrolleeService enrolleeService;

    private final EnrolleeSubjectsCreationService enrolleeSubjectsCreationService;

    @Autowired
    public EnrolleeSubjectController(EnrolleeService enrolleeService,
                                     EnrolleeSubjectsCreationService enrolleeSubjectsCreationService) {
        this.enrolleeService = enrolleeService;
        this.enrolleeSubjectsCreationService = enrolleeSubjectsCreationService;
    }

    @PostMapping(value = "/{id}/subjects/new")
    public ModelAndView createUserSubject(@RequestParam(name = "score0") Integer score1,
                                          @RequestParam(name = "score1") Integer score2,
                                          @RequestParam(name = "score2") Integer score3,
                                          @PathVariable(name = "id") Long id,
                                          ModelAndView modelAndView,
                                          HttpServletRequest request) {

        UserGetDto user = (UserGetDto) request.getSession().getAttribute("user");
        Long enrollee_id = enrolleeService.findByUserId(user.getId()).getId();


        List<Integer> list = new ArrayList<>();
        list.add(score1);
        list.add(score2);
        list.add(score3);

        if(score1 > 100 || score1 < 0 || score2 > 100 || score2 < 0|| score3 > 100 || score3 < 0){
            request.getSession().setAttribute("many", "invalid score");
            return new ModelAndView("redirect:/faculty/" + id + "/subjects");
        }
        if (user != null && user.getRole().equals(UserRole.USER)) {

            boolean flag;

            try {

                flag = enrolleeSubjectsCreationService.setNewEnrolleeSubjects(id, enrollee_id, list);
            } catch (EntityNotFoundException ex) {

                log.error("Failed to set enrollee subjects in createUserSubjects()");
                return new ModelAndView("error");
            }

            if (flag) {

                modelAndView.setViewName("redirect:/faculty");
                return modelAndView;
            }

            modelAndView.setViewName("redirect:/faculty/" + id + "/subjects");
            request.getSession().setAttribute("registered", "You already registered on faculty");
            return modelAndView;
        }

        return new ModelAndView("error");
    }
}
