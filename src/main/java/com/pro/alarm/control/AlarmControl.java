package com.pro.alarm.control;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.pro.alarm.AlarmCode;

public interface AlarmControl extends AlarmCode {

	public String home(HttpServletRequest req, Locale locale, Model model) throws Exception;
	
	public String tick(HttpServletRequest req, Model model) throws Exception;
	
	public String mngAlarm(HttpServletRequest req, Model model) throws Exception;

	public String mngAlarmPost(HttpServletRequest req, Model model) throws Exception;
	
	/**************************************************************************************/
	/**
	 * 일정관리자 화면 초기화면
	 * @param req
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String MSS010_00M(HttpServletRequest req, Model model) throws Exception;
	
}
