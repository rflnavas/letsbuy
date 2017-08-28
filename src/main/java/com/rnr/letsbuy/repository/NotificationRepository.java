package com.rnr.letsbuy.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import com.rnr.letsbuy.document.Notification;

public interface NotificationRepository extends CrudRepository<Notification, BigInteger>{

}
