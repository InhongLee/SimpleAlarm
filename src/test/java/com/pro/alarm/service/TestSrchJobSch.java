package com.pro.alarm.service;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.pro.alarm.Utlz;
import com.pro.alarm.dao.AlarmDao;
import com.pro.alarm.dto.JobSchInfoDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:test-context.xml"})
@DirtiesContext
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class TestSrchJobSch {
	
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
	public void setUp() throws Exception {
		this.dto = new JobSchInfoDto();
		this.dto.setDEVER_ID("testDever"	);
		this.dto.setCUST_ID	("testCust"		);
		this.dto.setSET_TM	("00010101 0000");
		this.dto.setJOB_CD	("testJobCd"	);
		alarmDao.insJobSch(this.dto);
		
		req = new MockHttpServletRequest();
		model = new ExtendedModelMap();
		req.setParameter("DEVER_ID", "testDever");
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String sCurDt = format.format(new Date());
		String sStdt = Utlz.calDate(sCurDt, -1);
		String sEddt = Utlz.calDate(sCurDt, 1);
		req.setParameter("STDT", sStdt);
		req.setParameter("EDDT", sEddt);
		req.setParameter("DELAY_CHK", "N");
	}
	
	@Test
	public void test1201_srchJobSch_deverIdChk() throws Exception {
		req.removeParameter("DEVER_ID");
		model.addAttribute("req",req);
		thrown.expect(AlarmException.class);
		thrown.expectMessage("개발자ID는 필수입력항목입니다.");
		alarmService.srchJobSch(model);
	}
	
	@Test
	public void test1202_srchJobSch_diffDate() throws Exception {
		req.setParameter("STDT", "20180101");
		model.addAttribute("req",req);
		thrown.expect(AlarmException.class);
		thrown.expectMessage("조회기간는 한달이내로 선택하셔야 합니다.");
		alarmService.srchJobSch(model);
	}
	

	/**
	 * 고객ID와 조회기간을 조건으로 일정테이블 조회
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void test1203_srchJobSch_success() throws Exception {
		model.addAttribute("req", req);
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		alarmService.srchJobSch(model);
		jobScheduleDtos = (List<JobSchInfoDto>) model.get("jobScheduleDtos");
		assertEquals("조회를 실패하였습니다.", 1, jobScheduleDtos.size());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test1204_srchJobSch_delayChk() throws Exception {
		req.setParameter("DELAY_CHK", "Y");
		model.addAttribute("req", req);
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		alarmService.srchJobSch(model);
		jobScheduleDtos = (List<JobSchInfoDto>) model.get("jobScheduleDtos");
		assertEquals("조회를 실패하였습니다.", 1, jobScheduleDtos.size());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test1205_srchJobSch_orderByCustId() throws Exception {
		req.setParameter("ORDERBY_TYPE", "ASC");
		req.setParameter("ORDERBY_COL", "CUST_ID");
		model.addAttribute("req", req);
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		alarmService.srchJobSch(model);
		jobScheduleDtos = (List<JobSchInfoDto>) model.get("jobScheduleDtos");
		assertEquals("조회를 실패하였습니다.", 1, jobScheduleDtos.size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void test1206_srchJobSch_orderBySetTm() throws Exception {
		req.setParameter("ORDERBY_TYPE", "ASC");
		req.setParameter("ORDERBY_COL", "SET_TM");
		model.addAttribute("req", req);
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		alarmService.srchJobSch(model);
		jobScheduleDtos = (List<JobSchInfoDto>) model.get("jobScheduleDtos");
		assertEquals("조회를 실패하였습니다.", 1, jobScheduleDtos.size());
	}
	@SuppressWarnings("unchecked")
	@Test
	public void test1207_srchJobSch_orderByJobCd() throws Exception {
		req.setParameter("ORDERBY_TYPE", "ASC");
		req.setParameter("ORDERBY_COL", "JOB_CD");
		model.addAttribute("req", req);
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		alarmService.srchJobSch(model);
		jobScheduleDtos = (List<JobSchInfoDto>) model.get("jobScheduleDtos");
		assertEquals("조회를 실패하였습니다.", 1, jobScheduleDtos.size());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test1208_srchJobSch_orderByChkYn() throws Exception {
		req.setParameter("ORDERBY_TYPE", "ASC");
		req.setParameter("ORDERBY_COL", "CHK_YN");
		model.addAttribute("req", req);
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		alarmService.srchJobSch(model);
		jobScheduleDtos = (List<JobSchInfoDto>) model.get("jobScheduleDtos");
		assertEquals("조회를 실패하였습니다.", 1, jobScheduleDtos.size());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test1209_srchJobSch_orderByCustId() throws Exception {
		req.setParameter("ORDERBY_TYPE", "DESC");
		req.setParameter("ORDERBY_COL", "CUST_ID");
		model.addAttribute("req", req);
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		alarmService.srchJobSch(model);
		jobScheduleDtos = (List<JobSchInfoDto>) model.get("jobScheduleDtos");
		assertEquals("조회를 실패하였습니다.", 1, jobScheduleDtos.size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void test1210_srchJobSch_orderBySetTm() throws Exception {
		req.setParameter("ORDERBY_TYPE", "DESC");
		req.setParameter("ORDERBY_COL", "SET_TM");
		model.addAttribute("req", req);
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		alarmService.srchJobSch(model);
		jobScheduleDtos = (List<JobSchInfoDto>) model.get("jobScheduleDtos");
		assertEquals("조회를 실패하였습니다.", 1, jobScheduleDtos.size());
	}
	@SuppressWarnings("unchecked")
	@Test
	public void test1211_srchJobSch_orderByJobCd() throws Exception {
		req.setParameter("ORDERBY_TYPE", "DESC");
		req.setParameter("ORDERBY_COL", "JOB_CD");
		model.addAttribute("req", req);
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		alarmService.srchJobSch(model);
		jobScheduleDtos = (List<JobSchInfoDto>) model.get("jobScheduleDtos");
		assertEquals("조회를 실패하였습니다.", 1, jobScheduleDtos.size());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test1212_srchJobSch_orderByChkYn() throws Exception {
		req.setParameter("ORDERBY_TYPE", "DESC");
		req.setParameter("ORDERBY_COL", "CHK_YN");
		model.addAttribute("req", req);
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		alarmService.srchJobSch(model);
		jobScheduleDtos = (List<JobSchInfoDto>) model.get("jobScheduleDtos");
		assertEquals("조회를 실패하였습니다.", 1, jobScheduleDtos.size());
	}
	
	//orderby_type은 존재하지만 orderby_col이 부재한 경우
	@SuppressWarnings("unchecked")
	@Test
	public void test1213_srchJobSch_orderByTypeOnly() throws Exception {
		req.setParameter("ORDERBY_TYPE", "ASC");
		model.addAttribute("req", req);
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		alarmService.srchJobSch(model);
		jobScheduleDtos = (List<JobSchInfoDto>) model.get("jobScheduleDtos");
		assertEquals("조회를 실패하였습니다.", 1, jobScheduleDtos.size());
	}
	
	//orderby_col은 존재하지만 orderby_type이 부재한 경우
	@SuppressWarnings("unchecked")
	@Test
	public void test1214_srchJobSch_orderByColOnly() throws Exception {
		req.setParameter("ORDERBY_COL", "CUST_ID");
		model.addAttribute("req", req);
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		alarmService.srchJobSch(model);
		jobScheduleDtos = (List<JobSchInfoDto>) model.get("jobScheduleDtos");
		assertEquals("조회를 실패하였습니다.", 1, jobScheduleDtos.size());
	}
	
	//조회조건 cust_id, job_cd 포함할 경우
	@SuppressWarnings("unchecked")
	@Test
	public void test1215_srchJobSch_srchFiler() throws Exception {
		req.setParameter("CUST_ID", "testCust");
		req.removeParameter("DELAY_CHK");
		req.setParameter("JOB_CD", "testJob");
		model.addAttribute("req", req);
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		alarmService.srchJobSch(model);
		jobScheduleDtos = (List<JobSchInfoDto>) model.get("jobScheduleDtos");
		assertEquals("조회를 실패하였습니다.", 1, jobScheduleDtos.size());
	}
	
	@Test
	public void test1216_srchJobSchStdtChk() throws Exception {
		req.removeParameter("STDT");
		model.addAttribute("req", req);
		thrown.expect(AlarmException.class);
		thrown.expectMessage("조회시작일자는 필수입력사항입니다.");
		alarmService.srchJobSch(model);
	}
	
	@Test
	public void test1217_srchJobSchEddtChk() throws Exception {
		req.removeParameter("EDDT");
		model.addAttribute("req", req);
		thrown.expect(AlarmException.class);
		thrown.expectMessage("조회종료일자는 필수입력사항입니다.");
		alarmService.srchJobSch(model);
	}
	
	@AfterTransaction
	public void afterTransaction() throws Exception {
		this.dto.setLUPD_CNT(1);
		alarmDao.delJobSch(this.dto);
	}
}
