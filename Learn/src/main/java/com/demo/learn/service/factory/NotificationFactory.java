package com.demo.learn.service.factory;

import org.springframework.stereotype.Component;

public interface NotificationFactory {

	public NotificationHandler getService(String type);
}
