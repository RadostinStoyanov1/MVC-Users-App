package usersmvc.web;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import usersmvc.model.dto.AddUserDTO;
import usersmvc.model.dto.InputFormFieldDTO;
import usersmvc.model.dto.UpdateUserDTO;
import usersmvc.model.dto.UserDTO;
import usersmvc.service.UserEntityService;
import usersmvc.service.exception.ExistingEmailOrPhoneException;
import usersmvc.service.exception.UserNotFoundException;

import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserEntityService userEntityService;
    private final ModelMapper modelMapper;

    public UsersController(UserEntityService userEntityService, ModelMapper modelMapper) {
        this.userEntityService = userEntityService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("allUsers", userEntityService.getAllUsersSummary());
        InputFormFieldDTO inputFormFieldDTO = new InputFormFieldDTO();
        inputFormFieldDTO.setPattern("");

        model.addAttribute("searchPattern", inputFormFieldDTO);
        return "users";
    }

    @PostMapping("/all")
    public String getUsersWithSearch(@ModelAttribute InputFormFieldDTO searchPattern, Model model) {
        model.addAttribute("allUsers", userEntityService.getAllSearchedUsersSummary(searchPattern.getPattern()));
        model.addAttribute("searchPattern", searchPattern);

        return "users";
    }

    @GetMapping("/{id}")
    public String userDetails(@PathVariable String id, Model model) {
        UserDTO userDTO = userEntityService.getUserByUuid(UUID.fromString(id));
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

            return "redirect:/users/add";
        }

        userEntityService.addUser(addUserDTO);

        return "redirect:/users/all";

    }

    @GetMapping("/update/{id}")
    public String updatingUser(@PathVariable UUID id, Model model) {
        UserDTO userDTO = userEntityService.getUserByUuid(id);
        UpdateUserDTO updatedUserDTO = modelMapper.map(userDTO, UpdateUserDTO.class);

        if (!model.containsAttribute("updateUserDTO")) {
            model.addAttribute("updateUserDTO", updatedUserDTO);
        }

        return "user-edit";
    }

    @PutMapping("/update/{id}")
    public String updateUser(@Valid UpdateUserDTO updateUserDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("updateUserDTO", updateUserDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateUserDTO", bindingResult);

            return "redirect:/users/update/{id}";
        }

        userEntityService.updateUser(updateUserDTO);

        return "redirect:/users/all";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") String id) {
        userEntityService.deleteUser(UUID.fromString(id));

        return "redirect:/users/all";
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView handleUserNotFound(UserNotFoundException unfe) {
        ModelAndView modelAndView = new ModelAndView("user-not-found");

        modelAndView.addObject("message", unfe.getMessage());

        return modelAndView;
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(ExistingEmailOrPhoneException.class)
    public ModelAndView handleExistingEmailOrPhone(ExistingEmailOrPhoneException ex) {
        ModelAndView modelAndView = new ModelAndView("existing-email-or-phone");

        modelAndView.addObject("message", ex.getMessage());

        return modelAndView;
    }

}
