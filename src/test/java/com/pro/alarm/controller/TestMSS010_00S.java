package com.pro.alarm.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ExtendedModelMap;

import com.pro.alarm.control.RestControlImpl;
import com.pro.alarm.dto.JobSchInfoDto;
import com.pro.alarm.service.RestServiceImpl;

public class TestMSS010_00S {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Mock
	public RestServiceImpl service;
	
	@InjectMocks
	public RestControlImpl control;
	
	private ExtendedModelMap model;
	
	@Before
	public void setUp() {
		control = new RestControlImpl();
		model = new ExtendedModelMap();
	}
	
	@Test
	public void testMSS010_00S_srchJobSch_ok() throws Exception{
		MockitoAnnotations.initMocks(this);
		Map<String,Object> voHeaderSrch = new HashMap<>();
		model.addAttribute("req", voHeaderSrch);
		doNothing().when(service).srchJobSch(any(ExtendedModelMap.class));

		ResponseEntity<List<JobSchInfoDto>> rsltDtos = null; 
		rsltDtos = (ResponseEntity<List<JobSchInfoDto>>) control.MSS010_00S(voHeaderSrch, model);
		
		verify(service, times(1)).srchJobSch(any(ExtendedModelMap.class));
		assertThat(rsltDtos.getStatusCode(), is(HttpStatus.OK));
	}
	
	@Test
	public void testMSS010_00S_srchJobSch_badRequest() throws Exception{
		MockitoAnnotations.initMocks(this);
		Map<String,Object> voHeaderSrch = new HashMap<>();
		model.addAttribute("req", voHeaderSrch);
		doThrow(new Exception()).when(service).srchJobSch(any(ExtendedModelMap.class));
		
		
		ResponseEntity<List<JobSchInfoDto>> rsltDtos = null;
		rsltDtos = (ResponseEntity<List<JobSchInfoDto>>) control.MSS010_00S(voHeaderSrch, model);
		
		verify(service, times(1)).srchJobSch(any(ExtendedModelMap.class));
		assertThat(rsltDtos.getStatusCode(), is(HttpStatus.BAD_REQUEST));
	}
}
