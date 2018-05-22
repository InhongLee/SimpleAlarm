package com.pro.alarm.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsMapContaining.hasKey;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.ExtendedModelMap;

import com.pro.alarm.control.AlarmControl;
import com.pro.alarm.control.AlarmControlImpl;
import com.pro.alarm.service.AlarmService;

public class Test0200_mngAlarmPost {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Mock
	public AlarmService service;
	
	@InjectMocks
	public AlarmControl control;
	
	private MockHttpServletRequest req;
	private MockHttpServletResponse res;
	private ExtendedModelMap model;
	
	
	@Before
	public void setUp() {
		req = new MockHttpServletRequest();
		res = new MockHttpServletResponse();
		model = new ExtendedModelMap();
		control = new AlarmControlImpl();
	}
	
	@SuppressWarnings("restriction")
	@Test
	public void test0200_mngAlarmPost() throws Exception {
		//Expect
		MockitoAnnotations.initMocks(this);
		doNothing().when(service).insJobSch(any(ExtendedModelMap.class));
		
		//Execute
		String uri = control.mngAlarmPost(req, model);
		
		//test
		verify(service, times(1)).insJobSch(any(ExtendedModelMap.class));
		assertThat(uri, is(equalTo("mngAlarm")));
		assertThat(model, hasKey("req"));
	}
}
