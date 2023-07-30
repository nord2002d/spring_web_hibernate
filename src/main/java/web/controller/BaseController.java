package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.repository.UserService;

@Controller
public class BaseController {

	private UserService userService;

	public BaseController(UserService userService) {
		this.userService = userService;
	}


	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		model.addAttribute("users", userService.listUsers());
		model.addAttribute("add",new User());
		model.addAttribute("delete",new User());
		model.addAttribute("update",new User());
		return "users";
	}

	@PostMapping()
	public String addUser(@ModelAttribute User user){
		userService.add(user);
		return "redirect:/";
	}

    @DeleteMapping()
	public String removeUser(@ModelAttribute User user) {
		userService.removeUser(user.getId());
		return "redirect:/";
	}
	@PatchMapping()
	public String updateUser(@ModelAttribute User user) {
		User updateUser = userService.getUser(user.getId());
		updateUser.setName(user.getName());
		updateUser.setSurName(user.getSurName());
		updateUser.setAge(user.getAge());
		userService.add(updateUser);
		return "redirect:/";
	}



}