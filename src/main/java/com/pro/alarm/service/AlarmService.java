package com.pro.alarm.service;

import org.springframework.ui.Model;

public interface AlarmService {

	public void srchJobSch(Model model) throws Exception;

	public void insJobSch(Model model) throws Exception;
	
	public void updJobSch(Model model) throws Exception;
	
	public void del_job_sch(Model model) throws Exception;
}
