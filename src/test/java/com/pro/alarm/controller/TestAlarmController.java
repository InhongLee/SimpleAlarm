package com.pro.alarm.controller;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.ExtendedModelMap;

import com.pro.alarm.control.AlarmControl;
import com.pro.alarm.control.AlarmControlImpl;
import com.pro.alarm.service.AlarmService;

@RunWith(Parameterized.class)
public class TestAlarmController {

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
			
		});
	}

	@Parameter		public String DEVER_ID	;
	@Parameter(1)	public String CUST_ID	;
	@Parameter(2)	public String SET_TM	;
	@Parameter(3)	public String JOB_CD	;
	
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
	
	public void test0100_tick() throws Exception {
		
	}
}
