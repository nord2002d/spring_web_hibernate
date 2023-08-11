package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.exeptions.UserNotFoundException;
import web.model.User;
import web.service.UserService;

@Controller
public class UserController {

    private static final String REDIRECT = "redirect:/users";
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public String showUsers(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        return "users";
    }

    @GetMapping( "/formAdd")
    public String getFormAddUser(ModelMap model) {
        model.addAttribute("add", new User());
        return "add";
    }

    @PostMapping()
    public String addUser(@ModelAttribute User user) {
        userService.add(user);
        return REDIRECT;
    }

    @DeleteMapping()
    public String removeUser(@RequestParam("id") long id) throws UserNotFoundException {
        userService.removeUser(id);
        return REDIRECT;
    }

    @GetMapping("/formUpdate")
    public String getFormUpdate(ModelMap model, @RequestParam("id") long id) throws UserNotFoundException {
        model.addAttribute("update", userService.getUser(id));
        return "update";
    }

    @PatchMapping()
    public String updateUser(@ModelAttribute User user) throws UserNotFoundException {
        User updateUser = userService.getUser(user.getId());
        updateUser.setName(user.getName());
        updateUser.setSurName(user.getSurName());
        updateUser.setAge(user.getAge());
        userService.add(updateUser);
        return REDIRECT;
    }


}