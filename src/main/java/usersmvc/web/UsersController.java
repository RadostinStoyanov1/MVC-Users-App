package usersmvc.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import usersmvc.model.dto.AddUserDTO;
import usersmvc.model.dto.InputFormFieldDTO;
import usersmvc.model.dto.UserDTO;
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

    @GetMapping("/{id}")
    public String userDetails(@PathVariable Long id, Model model) {
        UserDTO userDTO = userEntityService.getUserById(id);
        model.addAttribute("userDetails", userDTO);

        return "details";
    }

    @GetMapping("/add")
    public String newUser(Model model) {

        if (!model.containsAttribute("addUserDTO")) {
            model.addAttribute("addUserDTO", new AddUserDTO());
        }

        return "user-add";
    }

    @PostMapping("/add")
    public String addUser(@Valid AddUserDTO addUserDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addUserDTO", addUserDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addUserDTO", bindingResult);

            return "redirect:user-add";
        }

        userEntityService.register(addUserDTO);

        return "redirect:/users/all";

    }
}
