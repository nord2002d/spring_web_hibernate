package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.repository.UserService;

@Controller
public class UserController {

    private static final String REDIRECT = "redirect:/users";
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/users")
    public String showUsers(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        return "users";
    }

    @GetMapping(value = "/add")
    public String addPP(ModelMap model) {
        model.addAttribute("add", new User());
        return "add";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        userService.add(user);
        return REDIRECT;
    }

    @GetMapping(value = "/delete")
    public String remove(ModelMap model) {
        model.addAttribute("delete", new User());
        return "delete";
    }

    @DeleteMapping("/delete")
    public String removeUser(@ModelAttribute User user) {
        User us = userService.getUser(user.getId());
        if (us == null) {
            return "redirect:/error";
        }
        userService.removeUser(user.getId());
        return REDIRECT;
    }

    @GetMapping(value = "/update")
    public String update(ModelMap model) {
        model.addAttribute("update", new User());
        return "update";
    }

    @PatchMapping("/update")
    public String updateUser(@ModelAttribute User user) {
        User updateUser = userService.getUser(user.getId());
        if (updateUser == null) {
            return "redirect:/error";
        }
        updateUser.setName(user.getName());
        updateUser.setSurName(user.getSurName());
        updateUser.setAge(user.getAge());
        userService.add(updateUser);
        return REDIRECT;
    }

    @GetMapping(value = "/error")
    public String error() {
        return "error";
    }


}