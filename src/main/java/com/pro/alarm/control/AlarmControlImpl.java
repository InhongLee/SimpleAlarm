package com.pro.alarm.control;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pro.alarm.service.AlarmService;

@Controller
public class AlarmControlImpl implements AlarmControl{

	private static final Logger log = LoggerFactory.getLogger(AlarmControlImpl.class);
	@Inject
	private AlarmService alarmService;

	@Override
	@GetMapping(value="home")
	public String home(HttpServletRequest req, Locale locale, Model model) throws Exception {
		log.info("Welcome home! The client locale is {}.", locale);
		String DEVER_ID = req.getParameter("DEVER_ID");
		String CUST_ID = req.getParameter("CUST_ID");
		model.addAttribute("DEVER_ID", DEVER_ID);
		model.addAttribute("CUST_ID", CUST_ID);
		return "home";
	}

	@Override
	@PostMapping(value="tick")
	public String tick(HttpServletRequest req, Model model) throws Exception {
		log.info(".....ticked");
		model.addAttribute("req", req);
		alarmService.srchJobSch(model);
		return "alarm";
	}

	@Override
	@GetMapping(value="mngAlarm")
	public String mngAlarm(HttpServletRequest req, Model model) throws Exception {
		log.info(".....mngAlarm");
		return "mngAlarm";
	}

	@Override
	@PostMapping(value="mngAlarm")
	public String mngAlarmPost(HttpServletRequest req, Model model) throws Exception {
		log.info(".....mngAlarmPost");
		model.addAttribute("req", req);
		alarmService.insJobSch(model);
		return "mngAlarm";
	}

	@Override
	@GetMapping(value=MSS010_00M)
	public String MSS010_00M(HttpServletRequest req, Model model) throws Exception {
		log.info(".....MSS010_00M");
		return MSS010_00M;
	}
	
}
