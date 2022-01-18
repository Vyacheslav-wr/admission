package by.salei.admission.controller;

import by.salei.admission.dto.EnrolleeGetDto;
import by.salei.admission.dto.UserGetDto;
import by.salei.admission.entity.EnrolleeStatus;
import by.salei.admission.entity.UserRole;
import by.salei.admission.service.api.EnrolleeService;
import by.salei.admission.service.api.EnrolleeSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    private final EnrolleeService enrolleeService;

    private final EnrolleeSubjectService enrolleeSubjectService;

    @Autowired
    public AdminController(EnrolleeService enrolleeService,
                           EnrolleeSubjectService enrolleeSubjectService) {
        this.enrolleeService = enrolleeService;
        this.enrolleeSubjectService = enrolleeSubjectService;
    }

    @GetMapping(value = "/users")
    public ModelAndView getAllEnrollee(HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        UserGetDto user = (UserGetDto) request.getSession().getAttribute("user");

        if (user != null && user.getRole().equals(UserRole.ADMIN)) {

            List<EnrolleeGetDto> enrollees = enrolleeService.getAll();

            enrollees.forEach(entity -> entity
                    .setGeneralScore(
                            enrolleeSubjectService
                                    .calculateGeneralEnrolleeScore(entity.getId())
                    ));

            modelAndView.addObject("enrollees", enrollees);
            modelAndView.addObject("user", user);
            modelAndView.setViewName("enrollees_list");

            return modelAndView;
        }

        modelAndView.setViewName("error");

        return modelAndView;
    }

    @PostMapping(value = "/approved")
    public ModelAndView approveEnrollee(@RequestParam(name = "id") Long id, HttpServletRequest request) {

        UserGetDto user = (UserGetDto) request.getSession().getAttribute("user");

        if (user != null && user.getRole().equals(UserRole.ADMIN)) {

            enrolleeService.updateEnrolleeStatus(id, EnrolleeStatus.APPROVED);

            return new ModelAndView("redirect:/users");
        }

        return new ModelAndView("error");
    }
}
