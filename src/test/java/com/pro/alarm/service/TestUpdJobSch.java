package com.pro.alarm.service;

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
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ExtendedModelMap;

import com.pro.alarm.AlarmException;
import com.pro.alarm.dao.AlarmDao;
import com.pro.alarm.dto.JobSchInfoDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:test-context.xml"})
@DirtiesContext
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class TestUpdJobSch {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Inject
	private AlarmService alarmService;
	@Inject
	private AlarmDao alarmDao;
	
	private MockHttpServletRequest req;
	//private MockHttpServletResponse res;
	private ExtendedModelMap model;
	private JobSchInfoDto dto;
	
	@BeforeTransaction
	private void beforeTransactional() throws Exception {
		this.dto = new JobSchInfoDto();
		this.dto.setDEVER_ID("testDever"	);
		this.dto.setCUST_ID	("testCust"		);
		this.dto.setSET_TM	("00010101 0000");
		this.dto.setJOB_CD	("testJobCd"	);
		alarmDao.insJobSch(this.dto);
	
		model = new ExtendedModelMap();
		req = new MockHttpServletRequest();
		req.setParameter("DEVER_ID"	, "testDever"	);
		req.setParameter("CUST_ID"	, "testCust"	);
		req.setParameter("SQNO"		, "1"			);
		req.setParameter("LUPD_CNT"	, "1"			);
		req.setParameter("SET_TM"	, "0800"		);
		req.setParameter("JOB_CD"	, "testJob"		);
	}
	
	@Test
	public void test1230_01deverIdChk() throws Exception {
		req.removeParameter("DEVER_ID");
		model.addAttribute("req", req);
		thrown.expect(AlarmException.class);
		thrown.expectMessage("개발자ID는 필수조건입니다.");
		alarmService.updJobSch(model);
	}
	
	@Test
	public void test1230_02custIdChk() throws Exception {
		req.removeParameter("CUST_ID");
		model.addAttribute("req", req);
		thrown.expect(AlarmException.class);
		thrown.expectMessage("고객ID는 필수조건입니다.");
		alarmService.updJobSch(model);
	}
	
	@Test
	public void test1230_03sqnoChk() throws Exception {
		req.removeParameter("SQNO");
		model.addAttribute("req", req);
		thrown.expect(AlarmException.class);
		thrown.expectMessage("일련번호는 필수조건입니다.");
		alarmService.updJobSch(model);
	}
	
	@Test
	public void test1230_04lupdCntChk() throws Exception {
		req.removeParameter("LUPD_CNT");
		model.addAttribute("req", req);
		thrown.expect(AlarmException.class);
		thrown.expectMessage("최종수정수는 필수조건입니다.");
		alarmService.updJobSch(model);
	}
	
	@Test
	public void test1230_05srchDataChgYn() throws Exception {
		req.setParameter("LUPD_CNT", "2");
		model.addAttribute("req", req);
		thrown.expect(AlarmException.class);
		thrown.expectMessage("조회후 데이터가 변경되었습니다. 재 조회 하십시오.");
		alarmService.updJobSch(model);
	}
	
	@Test
	public void test1230_06srchJobChkYn() throws Exception {
		model.addAttribute("req", req);
		thrown.expect(AlarmException.class);
		thrown.expectMessage("일정확인이 완료된 건은 변경/삭제가 불가능합니다.");
		alarmService.updJobSch(model);
	}
	
	@AfterTransaction
	public void afterTransaction() throws Exception {
		this.dto.setLUPD_CNT(1);
		alarmDao.delJobSch(this.dto);
	}
}
