package com.openshift.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.openshift.model.User;
import com.openshift.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	// get the user profile page
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(Principal principal, Model model) {
		// get the current logged in user
		User user = userService.findByEmail(principal.getName());

		// add the user to the model
		model.addAttribute("user", user);

		// return the profile page
		return "profile";
	}

	// save the user profile changes
	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String profilePost(@ModelAttribute("user") User user, Model model, Principal principal) {
		
		
              System.out.println(user);

		// get the current logged in user
		User newUser = userService.findByEmail(principal.getName());
		newUser.setUsername(user.getUsername());
		newUser.setNombre(user.getNombre());
		newUser.setSegundoNombre(user.getSegundoNombre());
		newUser.setApellidoPaterno(user.getApellidoPaterno());
		newUser.setApellidoMaterno(user.getApellidoMaterno());
		newUser.setEmail(user.getEmail());
		newUser.setTelefono(user.getTelefono());
		newUser.setUsername(user.getUsername());
		// add the user to the model
		model.addAttribute("user", newUser);

		// save the user
		userService.save(newUser);

		// return the view
		return "profile";
	}
}


