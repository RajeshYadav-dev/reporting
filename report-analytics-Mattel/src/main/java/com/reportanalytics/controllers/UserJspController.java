package com.reportanalytics.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.reportanalytics.services.UserCreationService;
import com.reportanalytics.services.UserService;

@Controller
@RequestMapping("/user")
public class UserJspController {

	@Autowired
	UserCreationService userCreationService;

	@Autowired
	UserService userService;

	@GetMapping(value = { "/create", "/edit" })
	public String upsertUserPage(HttpServletRequest request, Model model, @RequestParam(required = false) String userId) {
		String path = request.getRequestURI().substring(request.getContextPath().length());
		
		if(path.equalsIgnoreCase("/user/edit")) {
			if(null != userId) {
				model.addAttribute("heading", "Edit User");
				model.addAttribute("user", userService.getUserByUserId(Long.parseLong(userId)));
				model.addAttribute("type", "edit");
			}else
				return "redirect:/user";
		}else {
			model.addAttribute("heading", "Create New User");
			model.addAttribute("user", null);
			model.addAttribute("type", "create");
		}
		
		model.addAttribute("roles", userCreationService.getAllRoles());
		return "user/user-upsert";
	}

	@GetMapping("/set-password")
	public String createUserPage() {
		return "user/set-password";
	}

	@GetMapping("")
	public String userPage(Model model) {
		model.addAttribute("userList", userService.getAllUsers());
		return "user/user-display";
	}
}
