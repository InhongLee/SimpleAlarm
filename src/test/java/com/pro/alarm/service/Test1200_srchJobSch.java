package com.pro.alarm.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ExtendedModelMap;

import com.pro.alarm.AlarmException;
import com.pro.alarm.dao.AlarmDao;
import com.pro.alarm.dto.JobSchInfoDto;

public class Test1200_srchJobSch {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Mock
	private AlarmDao alarmDao;
	@InjectMocks
	private AlarmService service;
	
	private MockHttpServletRequest req;
	//private MockHttpServletResponse res;
	private ExtendedModelMap model;
	
	@Before
	public void setUp() throws Exception {
		service = new AlarmServiceImpl();
		req = new MockHttpServletRequest();
		model = new ExtendedModelMap();
		req.setParameter("DEVER_ID", "testDever");
		req.setParameter("STDT", "20180501");
		req.setParameter("EDDT", "20180520");
		req.setParameter("DELAY_CHK", "N");
	}
	
	@Test
	public void test1201_srchJobSch_deverIdChk() throws Exception {
		req.removeParameter("DEVER_ID");
		model.addAttribute("req",req);
		
		thrown.expect(AlarmException.class);
		thrown.expectMessage("개발자ID는 필수입력항목입니다.");
		service.srchJobSch(model);
	}
	
	@Test
	public void test1202_srchJobSch_diffDate() throws Exception {
		req.setParameter("STDT", "20180101");
		model.addAttribute("req",req);
		thrown.expect(AlarmException.class);
		thrown.expectMessage("조회기간는 한달이내로 선택하셔야 합니다.");
		service.srchJobSch(model);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test1203_srchJobSch_success() throws Exception {
		MockitoAnnotations.initMocks(this);
		model.addAttribute("req", req);
		//기대정의
		JobSchInfoDto dto = new JobSchInfoDto();
		dto.setDEVER_ID("testDever");
		dto.setCUST_ID("testCust");
		List<JobSchInfoDto> dtos = new ArrayList<JobSchInfoDto>();
		dtos.add(dto);
		doReturn(dtos).when(alarmDao).srchJobSchLst(anyMap());
		
		service.srchJobSch(model);
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		jobScheduleDtos = (List<JobSchInfoDto>) model.get("jobScheduleDtos");
		assertEquals("조회를 실패하였습니다.", 1, jobScheduleDtos.size());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test1204_srchJobSch_delayChk() throws Exception {
		MockitoAnnotations.initMocks(this);
		req.setParameter("DELAY_CHK", "Y");
		model.addAttribute("req", req);
		//기대정의
		JobSchInfoDto dto = new JobSchInfoDto();
		dto.setDEVER_ID("testDever");
		dto.setCUST_ID("testCust");
		List<JobSchInfoDto> dtos = new ArrayList<JobSchInfoDto>();
		dtos.add(dto);
		doReturn(dtos).when(alarmDao).srchJobSchLst(anyMap());
		
		service.srchJobSch(model);
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		jobScheduleDtos = (List<JobSchInfoDto>) model.get("jobScheduleDtos");
		Map<String, Object> daoMap = new HashMap<>();
		daoMap = (Map<String, Object>) model.get("daoMap");
		assertEquals("조회를 실패하였습니다.", 1, jobScheduleDtos.size());
		assertNull("지연여부를 선택하였으나 시작일자 초기화에 실패하였습니다.",daoMap.get("STDT"));
		assertNull("지연여부를 선택하였으나 종료일자 초기화에 실패하였습니다.",daoMap.get("EDDT"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test1205_srchJobSch_orderByCustId() throws Exception {
		MockitoAnnotations.initMocks(this);
		req.setParameter("ORDERBY_TYPE", "ASC");
		req.setParameter("ORDERBY_COL", "CUST_ID");
		model.addAttribute("req", req);
		//기대정의
		JobSchInfoDto dto = new JobSchInfoDto();
		dto.setDEVER_ID("testDever");
		dto.setCUST_ID("testCust");
		List<JobSchInfoDto> dtos = new ArrayList<JobSchInfoDto>();
		dtos.add(dto);
		doReturn(dtos).when(alarmDao).srchJobSchLst(anyMap());
				
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		service.srchJobSch(model);
		jobScheduleDtos = (List<JobSchInfoDto>) model.get("jobScheduleDtos");
		Map<String, Object> daoMap = new HashMap<>();
		daoMap = (Map<String, Object>) model.get("daoMap");
		assertEquals("조회를 실패하였습니다.", 1, jobScheduleDtos.size());
		assertEquals("정렬조건이 선택되었으나 반영에 실패하였습니다.", "ASC", daoMap.get("ORDERBY_TYPE"));
		assertEquals("정렬필드가 선택되었으나 반영에 실패하였습니다.", "CUST_ID", daoMap.get("ORDERBY_COL"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test1206_srchJobSch_orderBySetTm() throws Exception {
		MockitoAnnotations.initMocks(this);
		req.setParameter("ORDERBY_TYPE", "ASC");
		req.setParameter("ORDERBY_COL", "SET_TM");
		model.addAttribute("req", req);
		//기대정의
		JobSchInfoDto dto = new JobSchInfoDto();
		dto.setDEVER_ID("testDever");
		dto.setCUST_ID("testCust");
		List<JobSchInfoDto> dtos = new ArrayList<JobSchInfoDto>();
		dtos.add(dto);
		doReturn(dtos).when(alarmDao).srchJobSchLst(anyMap());
				
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		service.srchJobSch(model);
		jobScheduleDtos = (List<JobSchInfoDto>) model.get("jobScheduleDtos");
		Map<String, Object> daoMap = new HashMap<>();
		daoMap = (Map<String, Object>) model.get("daoMap");
		assertEquals("조회를 실패하였습니다.", 1, jobScheduleDtos.size());
		assertEquals("정렬조건이 선택되었으나 반영에 실패하였습니다.", "ASC", daoMap.get("ORDERBY_TYPE"));
		assertEquals("정렬필드가 선택되었으나 반영에 실패하였습니다.", "SET_TM", daoMap.get("ORDERBY_COL"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test1207_srchJobSch_orderByJobCd() throws Exception {
		MockitoAnnotations.initMocks(this);
		req.setParameter("ORDERBY_TYPE", "ASC");
		req.setParameter("ORDERBY_COL", "JOB_CD");
		model.addAttribute("req", req);
		//기대정의
		JobSchInfoDto dto = new JobSchInfoDto();
		dto.setDEVER_ID("testDever");
		dto.setCUST_ID("testCust");
		List<JobSchInfoDto> dtos = new ArrayList<JobSchInfoDto>();
		dtos.add(dto);
		doReturn(dtos).when(alarmDao).srchJobSchLst(anyMap());
				
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		service.srchJobSch(model);
		jobScheduleDtos = (List<JobSchInfoDto>) model.get("jobScheduleDtos");
		Map<String, Object> daoMap = new HashMap<>();
		daoMap = (Map<String, Object>) model.get("daoMap");
		assertEquals("조회를 실패하였습니다.", 1, jobScheduleDtos.size());
		assertEquals("정렬조건이 선택되었으나 반영에 실패하였습니다.", "ASC", daoMap.get("ORDERBY_TYPE"));
		assertEquals("정렬필드가 선택되었으나 반영에 실패하였습니다.", "JOB_CD", daoMap.get("ORDERBY_COL"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test1208_srchJobSch_orderByChkYn() throws Exception {
		MockitoAnnotations.initMocks(this);
		req.setParameter("ORDERBY_TYPE", "DESC");
		req.setParameter("ORDERBY_COL", "CHK_YN");
		model.addAttribute("req", req);
		//기대정의
		JobSchInfoDto dto = new JobSchInfoDto();
		dto.setDEVER_ID("testDever");
		dto.setCUST_ID("testCust");
		List<JobSchInfoDto> dtos = new ArrayList<JobSchInfoDto>();
		dtos.add(dto);
		doReturn(dtos).when(alarmDao).srchJobSchLst(anyMap());
				
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		service.srchJobSch(model);
		jobScheduleDtos = (List<JobSchInfoDto>) model.get("jobScheduleDtos");
		Map<String, Object> daoMap = new HashMap<>();
		daoMap = (Map<String, Object>) model.get("daoMap");
		assertEquals("조회를 실패하였습니다.", 1, jobScheduleDtos.size());
		assertEquals("정렬조건이 선택되었으나 반영에 실패하였습니다.", "DESC", daoMap.get("ORDERBY_TYPE"));
		assertEquals("정렬필드가 선택되었으나 반영에 실패하였습니다.", "CHK_YN", daoMap.get("ORDERBY_COL"));
	}
}
