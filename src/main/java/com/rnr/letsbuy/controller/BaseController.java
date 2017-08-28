package com.rnr.letsbuy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.rnr.letsbuy.configuration.AppConfiguration;

@Controller
public class BaseController {
	
	private AppConfiguration configuration;
	
	public Model setDateFormat(Model model){
		model.addAttribute("formatDate", configuration.getDateFormat()); //DateFormatUtils.DEFAULT_FORMAT);
		return model;
	}

	@Autowired
	public void setConfiguration(AppConfiguration configuration) {
		this.configuration = configuration;
	}
	
}
