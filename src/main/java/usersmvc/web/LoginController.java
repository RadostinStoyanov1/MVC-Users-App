package usersmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import usersmvc.model.dto.MasterUserLoginDTO;

@Controller
public class LoginController {

    @GetMapping("/masters/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("auth-login");

        modelAndView.addObject("loginData", new MasterUserLoginDTO());
        return modelAndView;
    }

    @GetMapping("masters/login-error")
    public ModelAndView viewLoginError() {
        ModelAndView modelAndView = new ModelAndView("auth-login");

        modelAndView.addObject("showErrorMessage", true);
        modelAndView.addObject("loginData", new MasterUserLoginDTO());

        return modelAndView;
    }
}
