package com.pro.alarm.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsMapContaining.hasKey;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ExtendedModelMap;

import com.pro.alarm.control.AlarmControl;
import com.pro.alarm.control.AlarmControlImpl;
import com.pro.alarm.service.AlarmService;

public class Test0110_home {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Mock
	public AlarmService service;
	
	@InjectMocks
	public AlarmControl control;
	
	private MockHttpServletRequest req;
	//private MockHttpServletResponse res;
	private ExtendedModelMap model;
	
	
	@Before
	public void setUp() {
		req = new MockHttpServletRequest();
		//res = new MockHttpServletResponse();
		model = new ExtendedModelMap();
		control = new AlarmControlImpl();
	}
	
	@SuppressWarnings("restriction")
	@Test
	public void test0110_home() throws Exception {
		MockitoAnnotations.initMocks(this);
		Locale locale = new Locale.Builder().setLanguage("ko").setRegion("KR").build();
		
		String uri = control.home(req, locale, model);
		
		assertThat(uri, is(equalTo("home")));
		assertThat(model, hasKey("DEVER_ID"));
		assertThat(model, hasKey("CUST_ID"));
	}
}
