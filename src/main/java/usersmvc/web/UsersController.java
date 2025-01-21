package usersmvc.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import usersmvc.model.dto.InputFormFieldDTO;
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
        model.addAttribute("allUsers", userEntityService.getAllUsersSummary());
        InputFormFieldDTO inputFormFieldDTO = new InputFormFieldDTO();
        model.addAttribute("searchPattern", inputFormFieldDTO.setPattern(""));
        return "users";
    }

    @PostMapping("/all")
    public String getUsersWithSearch(@ModelAttribute InputFormFieldDTO searchPattern, Model model) {
        model.addAttribute("allUsers", userEntityService.getAllSearchedUsersSummary(searchPattern.getPattern()));
        model.addAttribute("searchPattern", searchPattern);

        return "users";
    }
}
