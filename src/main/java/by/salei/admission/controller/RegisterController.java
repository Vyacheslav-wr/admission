package by.salei.admission.controller;

import by.salei.admission.dto.*;
import by.salei.admission.entity.EnrolleeStatus;
import by.salei.admission.entity.UserRole;
import by.salei.admission.service.api.EnrolleeService;
import by.salei.admission.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {

    private final EnrolleeService enrolleeService;

    private final UserService userService;

    @Autowired
    public RegisterController(EnrolleeService enrolleeService, UserService userService) {
        this.enrolleeService = enrolleeService;
        this.userService = userService;
    }

    @GetMapping(value = "/register")
    public ModelAndView register(ModelAndView modelAndView, HttpServletRequest request) {
        modelAndView.setViewName("register_page");
        modelAndView.addObject("error1", request.getSession().getAttribute("error1"));
        return modelAndView;
    }

    @PostMapping(value = "/validity")
    public ModelAndView validate(@ModelAttribute UserCreateDto user,
                                 @ModelAttribute EnrolleeCreateDto enrollee,
                                 ModelAndView modelAndView,
                                 HttpServletRequest request) {

        user.setRole(UserRole.USER);
        enrollee.setStatus(EnrolleeStatus.REGISTERED);
        if(userService.getByUsername(user.getUsername()) != null){

            modelAndView.setViewName("register_page");
            modelAndView.addObject("error",
                    "User with username: " + user.getUsername()
                            + " already exist");
            return modelAndView;
        }

        if(enrollee.getScore() > 100 || enrollee.getScore() < 0){

            modelAndView.setViewName("register_page");
            modelAndView.addObject("error1",
                    "Invalid score: " + enrollee.getScore());
            return modelAndView;
        }

        UserGetDto userGetDto = userService.save(user);

        EnrolleeGetDto enrolleeGetDto = enrolleeService.save(enrollee);

        enrolleeService.updateEnrolleeUser(enrolleeGetDto.getId(), userGetDto.getId());

        request.getSession().setAttribute("user", userGetDto);
        modelAndView.setViewName("redirect:/faculty");
        return modelAndView;
    }
}
