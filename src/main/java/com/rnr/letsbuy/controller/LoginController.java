package com.rnr.letsbuy.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller
public class LoginController {
	
	@GetMapping(path="/login")
	public String login(Model model){
		
		return "auth/login";
	}
}
