package com.pro.alarm.service;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ExtendedModelMap;

import com.pro.alarm.AlarmException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:test-context.xml"})
@DirtiesContext
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class TestInsJobSch {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Inject
	private AlarmService alarmService;
	
	private MockHttpServletRequest req;
	//private MockHttpServletResponse res;
	private ExtendedModelMap model;
	
	@BeforeTransaction
	private void beforeTransactional() throws Exception {
		req = new MockHttpServletRequest();
		model = new ExtendedModelMap();
		req.setParameter("DEVER_ID"	, "testDever"	);
		req.setParameter("CUST_ID"	, "testCust"	);
		req.setParameter("SET_TM"	, "0800"		);
		req.setParameter("JOB_CD"	, "testJob"		);
	}
	
	@Test
	public void test1220_01deverIdChk() throws Exception {
		req.removeParameter("DEVER_ID");
		model.addAttribute("req", req);
		thrown.expect(AlarmException.class);
		thrown.expectMessage("개발자ID는 필수입력항목입니다.");
		alarmService.insJobSch(model);
	}
	
	@Test
	public void test1220_02custIdChk() throws Exception {
		req.removeParameter("CUST_ID");
		model.addAttribute("req", req);
		thrown.expect(AlarmException.class);
		thrown.expectMessage("고객ID는 필수입력항목입니다.");
		alarmService.insJobSch(model);
	}
	
	@Test
	public void test1220_03setTmChk() throws Exception {
		req.removeParameter("SET_TM");
		model.addAttribute("req", req);
		thrown.expect(AlarmException.class);
		thrown.expectMessage("설정시간은 필수입력항목입니다.");
		alarmService.insJobSch(model);
	}
	
	@Test
	public void test1220_04insJobSch() throws Exception {
		model.addAttribute("req", req);
		alarmService.insJobSch(model);
		int insCnt = (int) model.get("insCnt");
		assertEquals("입력에 실패하였습니다.", 1, insCnt);
	}
}
