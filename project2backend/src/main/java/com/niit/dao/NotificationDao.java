package com.niit.dao;

import java.util.List;

import com.niit.models.Notification;

public interface NotificationDao {
	void addNotification(Notification notification);
	List<Notification>  getAllNotification(String email);
	Notification getNotification(int notificationId);
	void updateNotification(int notificationId);

}
