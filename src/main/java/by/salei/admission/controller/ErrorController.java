package by.salei.admission.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/error")
public class ErrorController {

    @GetMapping()
    public ModelAndView findException(ModelAndView modelAndView){
        modelAndView.setViewName("error");
        return modelAndView;
    }
}