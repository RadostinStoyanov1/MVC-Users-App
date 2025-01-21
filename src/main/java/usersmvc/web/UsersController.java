package usersmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import usersmvc.service.UserEntityService;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserEntityService userEntityService;

    public UsersController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("allUsers", userEntityService.getALlUsersSummary());
        return "users";
    }

}
