package by.salei.admission.controller;

import by.salei.admission.dto.UserGetDto;
import by.salei.admission.entity.UserRole;
import by.salei.admission.service.api.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdmissionController {

    private final AdmissionService admissionService;

    @Autowired
    public AdmissionController(AdmissionService admissionService) {

        this.admissionService = admissionService;
    }

    @GetMapping("/admission")
    public ModelAndView startAdmission(HttpServletRequest request) {

        UserGetDto user = (UserGetDto) request.getSession().getAttribute("user");

        if (user != null && user.getRole().equals(UserRole.ADMIN)) {

            admissionService.startAdmission();

            return new ModelAndView("redirect:/users");
        }

        return new ModelAndView("error");
    }
}
