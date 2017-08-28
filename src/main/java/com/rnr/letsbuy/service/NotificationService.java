package com.rnr.letsbuy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rnr.letsbuy.document.Notification;
import com.rnr.letsbuy.repository.NotificationRepository;

@Service
public class NotificationService {
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	public NotificationService() {
	}
	
	public List<Notification> findAll(){
		List<Notification> notifs = new ArrayList<>();
		notificationRepository.findAll().forEach(notifs::add);
		return notifs;
	}
}
