package com.reportanalytics.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reportanalytics.entities.Response;
import com.reportanalytics.services.SessionService;

@RestController
@RequestMapping("/login")
public class SessionController {


	@Autowired
	SessionService sessionService;

	@GetMapping("/user")
	public Response userLogin(@RequestParam String username, @RequestParam String password) {
		return sessionService.getUserAuthDetails(username, password);
	}

}
