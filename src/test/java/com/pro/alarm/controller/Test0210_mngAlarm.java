package com.pro.alarm.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

public class Test0210_mngAlarm {

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
	
	@Test
	public void test0210_mngAlarm() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		String uri = control.mngAlarm(req, model);
		
		assertThat(uri, is(equalTo("mngAlarm")));
	}
}
