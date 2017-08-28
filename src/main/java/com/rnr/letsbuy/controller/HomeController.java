package com.rnr.letsbuy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());  
	
	@RequestMapping(path="/", method={RequestMethod.GET})
	public String welcome(Model model){
		
		log.info("Accesing HomeController");
		model.addAttribute("pageTitle", "## LetsBuy - Get crazy buying lots of products ## :-)");
		
		return "views/index";
	}
	
}
