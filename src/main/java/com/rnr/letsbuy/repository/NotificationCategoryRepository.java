package com.rnr.letsbuy.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import com.rnr.letsbuy.document.NotificationCategory;

public interface NotificationCategoryRepository extends CrudRepository<NotificationCategory, BigInteger>{

}
