package by.salei.admission.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, ModelAndView modelAndView) {

        request.getSession().invalidate();

        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }
}
