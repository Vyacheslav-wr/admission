package by.salei.admission.controller;

import by.salei.admission.dto.FacultyGetDto;
import by.salei.admission.dto.SubjectGetDto;
import by.salei.admission.dto.UserGetDto;
import by.salei.admission.entity.UserRole;
import by.salei.admission.service.api.FacultyService;
import by.salei.admission.service.api.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.action.internal.EntityActionVetoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    private final SubjectService subjectService;

    @Autowired
    public FacultyController(FacultyService facultyService, SubjectService subjectService) {
        this.facultyService = facultyService;
        this.subjectService = subjectService;
    }

    @GetMapping
    public ModelAndView getAllFaculties(ModelAndView modelAndView, HttpServletRequest request) {

        modelAndView.addObject("faculties", facultyService.getAll());
        modelAndView.addObject("user", request.getSession().getAttribute("user"));
        modelAndView.setViewName("faculty_list");
        return modelAndView;
    }

    @GetMapping(value = "/{id}/subjects")
    public ModelAndView getFacultySubjects(@PathVariable(name = "id") Long id,
                                           ModelAndView modelAndView,
                                           HttpServletRequest request) {

        UserGetDto user = (UserGetDto) request.getSession().getAttribute("user");

        if (user != null && user.getRole().equals(UserRole.USER)) {

            FacultyGetDto faculty;

            try {

                faculty = facultyService.getById(id);
            } catch (EntityActionVetoException ex) {

                log.error("Failed to find Faculty in getFacultySubjects()");
                return new ModelAndView("error");
            }

            List<SubjectGetDto> subjects = faculty.getSubjects();

            modelAndView.addObject("facultyId", id);
            modelAndView.addObject("subjects", subjects);
            modelAndView.addObject("user", request.getSession().getAttribute("user"));
            modelAndView.addObject("registered",
                    request.getSession().getAttribute("registered"));
            modelAndView.addObject("numbers", new ArrayList<Integer>(Arrays.asList(0, 1, 2)));
            modelAndView.setViewName("faculty_info");
            return modelAndView;
        }

        return new ModelAndView("error");

    }

    @PostMapping(value = "/remove")
    public ModelAndView removeFaculty(@RequestParam(name = "faculty_id") Long id,
                                      HttpServletRequest request) {

        UserGetDto user = (UserGetDto) request.getSession().getAttribute("user");

        if (user != null && user.getRole().equals(UserRole.ADMIN)) {

            facultyService.delete(id);

            return new ModelAndView("redirect:/faculty");
        }

        return new ModelAndView("error");
    }

}
