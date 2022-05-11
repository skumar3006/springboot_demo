package com.demo.learn.service.factory;

import org.springframework.stereotype.Service;

@Service("sms")
public class SMSNotfication extends NotiticationSerivice implements NotificationHandler {

	@Override
	public void getName() {
		
		System.out.println("SMSNotfication is called");
		
	}

}
