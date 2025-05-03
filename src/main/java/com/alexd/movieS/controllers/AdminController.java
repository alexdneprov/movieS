package com.alexd.movieS.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping ("/admin")
public class AdminController {
	
	@GetMapping("/dashboard")
	public String adminDashboard() {
		return "Dashboard";
	}
	
	@GetMapping("/settings")
	public String adminSettings() {
		return "settings";
	}
	
}
