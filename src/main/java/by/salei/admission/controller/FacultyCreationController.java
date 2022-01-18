package by.salei.admission.controller;

import by.salei.admission.dto.FacultyCreateDto;
import by.salei.admission.dto.SubjectGetDto;
import by.salei.admission.dto.UserGetDto;
import by.salei.admission.entity.UserRole;
import by.salei.admission.service.api.FacultyService;
import by.salei.admission.service.api.FileCreatePath;
import by.salei.admission.service.api.SubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class FacultyCreationController {

    private final SubjectService subjectService;

    private final FacultyService facultyService;

    private final FileCreatePath fileCreatePath;

    @Value("${icons.path}")
    private String uploadPath;

    @Autowired
    public FacultyCreationController(SubjectService subjectService, FacultyService facultyService,
                                     FileCreatePath fileCreatePath) {
        this.subjectService = subjectService;
        this.facultyService = facultyService;
        this.fileCreatePath = fileCreatePath;
    }

    @GetMapping(value = "/faculty/new")
    public ModelAndView openFacultyCreation(HttpServletRequest request) {

        UserGetDto user = (UserGetDto) request.getSession().getAttribute("user");

        if (user != null && user.getRole().equals(UserRole.ADMIN)) {

            ModelAndView modelAndView = new ModelAndView();

            modelAndView.addObject("subjects", subjectService.getAll());
            modelAndView.setViewName("faculty_creation");
            modelAndView.addObject("user", user);

            return modelAndView;
        }

        return new ModelAndView("error");
    }

    @PostMapping(value = "/faculty/create")
    public ModelAndView createNewFaculty(@ModelAttribute FacultyCreateDto facultyCreateDto,
                                         HttpServletRequest request,
                                         @RequestParam(name = "subject1") String subject1,
                                         @RequestParam(name = "subject2") String subject2,
                                         @RequestParam(name = "subject3") String subject3,
                                         @RequestParam(name = "faculty_image") MultipartFile multipartFile) {

        UserGetDto user = (UserGetDto) request.getSession().getAttribute("user");

        SubjectGetDto subjectGetDto1;

        SubjectGetDto subjectGetDto2;

        SubjectGetDto subjectGetDto3;

        try {
            subjectGetDto1 = subjectService.findSubjectByType(subject1);

            subjectGetDto2 = subjectService.findSubjectByType(subject2);

            subjectGetDto3 = subjectService.findSubjectByType(subject3);

        } catch (EntityNotFoundException ex) {

            log.error("Failed to create Subjects in createNewFaculty()");
            return new ModelAndView("error");
        }

        List<SubjectGetDto> subjects = new ArrayList<>();
        subjects.add(subjectGetDto1);
        subjects.add(subjectGetDto2);
        subjects.add(subjectGetDto3);

        if (user != null && user.getRole().equals(UserRole.ADMIN)) {

            String path = fileCreatePath.createFilePath(multipartFile);

            if (path != null) {
                try {
                    multipartFile.transferTo(new File(uploadPath + "/" + path));
                } catch (IOException e) {
                    log.error("Unable to save file: {}", path);
                }

                facultyCreateDto.setImage(path);
            }

            ModelAndView modelAndView = new ModelAndView("redirect:/faculty");

            facultyCreateDto.setSubjects(subjects);

            facultyService.save(facultyCreateDto);

            return modelAndView;
        }

        return new ModelAndView("error");
    }
}
