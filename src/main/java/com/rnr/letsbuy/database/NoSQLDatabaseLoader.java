package com.rnr.letsbuy.database;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rnr.letsbuy.document.Notification;
import com.rnr.letsbuy.document.NotificationCategory;
import com.rnr.letsbuy.repository.NotificationCategoryRepository;
import com.rnr.letsbuy.repository.NotificationRepository;

//@Service
public class NoSQLDatabaseLoader {
	
	private final NotificationRepository notificationRepository;
	private final NotificationCategoryRepository notificationCategoryRepository;
	
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public NoSQLDatabaseLoader(NotificationRepository notificationRepository,
			NotificationCategoryRepository notificationCategoryRepository) {
		super();
		this.notificationRepository = notificationRepository;
		this.notificationCategoryRepository = notificationCategoryRepository;
	}
	
	//@PostConstruct
	private void initDatabase(){
		
		log.info("deleting in notificationRepository");
		notificationRepository.deleteAll();
		
		Notification notif = new Notification();
		
		NotificationCategory notifCat = new NotificationCategory();
		notifCat.setTitle("Delivered");
		notificationCategoryRepository.save(notifCat);
		
		notif.setCategory(notifCat);
		notif.setPostedOn(Calendar.getInstance().getTime());
		notif.setBody("The order was shipped to Elmond Street, No 15");
		notif.setTitle("Order - 503931084 - New Status:" + notif.getCategory().getTitle());
		log.info("adding NotificationCategory and Notification");
		notificationRepository.save(notif);
		
	}
	
	
	public NotificationRepository getNotificationRepository() {
		return notificationRepository;
	}

	public NotificationCategoryRepository getNotificationCategoryRepository() {
		return notificationCategoryRepository;
	}
	
	
}
