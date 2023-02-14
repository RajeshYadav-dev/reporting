package com.reportanalytics.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dashboard")
public class DashboardJspController {

	@GetMapping
	public String dashboard(Model model, ModelAndView modelAndView) {
		return "dashboard/index";
	}
}
