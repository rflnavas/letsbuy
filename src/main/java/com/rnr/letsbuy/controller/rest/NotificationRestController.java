package com.rnr.letsbuy.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rnr.letsbuy.document.Notification;
import com.rnr.letsbuy.service.NotificationService;

@RestController
@RequestMapping("/api")
public class NotificationRestController {
	
	@Autowired
	NotificationService notificationService;
	
	@GetMapping(path="/notifications", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Notification> showAll(){
		return notificationService.findAll();
	}
}
