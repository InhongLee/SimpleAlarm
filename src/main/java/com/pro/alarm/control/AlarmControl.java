package com.pro.alarm.control;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface AlarmControl {

	public String home(HttpServletRequest req, Locale locale, Model model) throws Exception;
	
	public String tick(HttpServletRequest req, Model model) throws Exception;
	
	public String mngAlarm(HttpServletRequest req, Model model) throws Exception;

	public String mngAlarmPost(HttpServletRequest req, Model model) throws Exception;
}
