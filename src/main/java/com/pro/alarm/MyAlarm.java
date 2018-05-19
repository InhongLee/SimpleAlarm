package com.pro.alarm;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyAlarm {

	private static final Logger logger = LoggerFactory.getLogger(MyAlarm.class);
	
	@Scheduled(cron="0 0/1 * * * *")
	public void myAlarm() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String date = dateFormat.format(new Date());
		logger.info(date);
	}
}
