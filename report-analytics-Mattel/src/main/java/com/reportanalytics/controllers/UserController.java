package com.reportanalytics.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reportanalytics.entities.Response;
import com.reportanalytics.services.UserCreationService;
import com.reportanalytics.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserCreationService userCreationService;

	@Autowired
	UserService userService;

	@PutMapping("/upsert-user")
	public Response createNewUser(@RequestBody Map<String, Object> userData) {
		return userCreationService.upsertUser(userData);
	}

	@PutMapping(value = { "/set-password", "/update-password" })
	public Response updateUserPassword(@RequestParam String password, @RequestParam String confirmPassword,
			@CookieValue("token") String token) {
		return userCreationService.updateUserPassword(password, confirmPassword, token);
	}

	@DeleteMapping()
	public Response deleteUser(@RequestParam(required = true) String userId) {
		return userService.deleteUserByUserId(userId);
	}
}
