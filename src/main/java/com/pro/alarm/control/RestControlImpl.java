package com.pro.alarm.control;


import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pro.alarm.AlarmCode;
import com.pro.alarm.dto.JobSchInfoDto;
import com.pro.alarm.service.RestServiceImpl;

@RestController
public class RestControlImpl implements AlarmCode{

	private static final Logger log = LoggerFactory.getLogger(RestControlImpl.class); 
	
	@Inject
	private RestServiceImpl service;
	
	@SuppressWarnings("unchecked")
	@PostMapping(value=MSS010_00S,produces="application/json;charset=utf-8")
	@ResponseBody
	public ResponseEntity<List<JobSchInfoDto>> MSS010_00S(@RequestBody Map<String,Object> voHeaderSrch, Model model) {
		log.info("MSS010_00S");
		log.info(voHeaderSrch.toString());
		ResponseEntity<List<JobSchInfoDto>> entity = null;
		try {
			model.addAttribute("req", voHeaderSrch);
			service.srchJobSch(model);
			Map<String,Object> modelMap = model.asMap();
			
			entity = new ResponseEntity<List<JobSchInfoDto>>((List<JobSchInfoDto>) modelMap.get("jobScheduleDtos"), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<JobSchInfoDto>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
