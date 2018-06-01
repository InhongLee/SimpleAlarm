package com.pro.alarm.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import com.pro.alarm.dao.AlarmDao;
import com.pro.alarm.dto.JobSchInfoDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:test-context.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class TestAlarmDao {

	private static Logger log = LoggerFactory.getLogger(TestAlarmDao.class);
	
	@Inject
	private AlarmDao alarmDao;
	
	private JobSchInfoDto dto;
	
	@BeforeTransaction
	public void beforeTransaction() throws Exception {
		this.dto = new JobSchInfoDto();
		//insert
		this.dto.setDever_id("testDever"	);
		this.dto.setCust_id	("testCust"		);
		this.dto.setSet_tm	("00010101 0101");
		this.dto.setJob_cd	("testJobCd"	);
		this.dto.setLupd_cnt(1 				);		
		alarmDao.ins_job_sch(this.dto);
	}
	
	@Test
	public void TEST1000_CRUD_JOB_SCH() throws Exception {
		JobSchInfoDto dto = new JobSchInfoDto();
		//insert
		dto.setDever_id	("amaco78"		);
		dto.setCust_id	("aaa999"		);
		dto.setSet_tm	("00010101 1300");
		dto.setJob_cd	("testJobCd"	);
		assertEquals(".....Table JOB_SCH insert error occured.", 1, alarmDao.insJobSch(dto));
		//select
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		dto.setLupd_cnt(1);
		jobScheduleDtos = alarmDao.select_job_sch(dto);
		int selectCnt = jobScheduleDtos.size();
		assertEquals(".....Table JOB_SCH select error occured.", 1, selectCnt);
		//update
		assertEquals(".....Table JOB_SCH update error occured.", 1, alarmDao.updJobSch(dto));
		//delete
		assertEquals(".....Table JOB_SCH delete error occured.", 1, alarmDao.delJobSch(dto));
		
		log.info("■■■■■ TBL JOB_SCH CRUD unit test passed.");
	}
	
	@Test
	public void test1001_srchDataChgYnN() throws Exception {
		//select
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		this.dto.setLupd_cnt(1);
		jobScheduleDtos = alarmDao.select_job_sch(this.dto);
		//srchDataChgYn
		int srchCntY = alarmDao.srchDataChgYn(jobScheduleDtos.get(0));
		assertEquals("■■■■■ 일정테이블 데이터변경여부 체크로직[TS001/srchDataChgYn] case:데이터미변경시.....test failed",1,srchCntY);
		log.info("■■■■■ 일정테이블 데이터변경여부 체크로직[TS001/srchDataChgYn] case:데이터미변경시.....test success");
	}
	
	@Test
	public void test1002_srchDataChgYnY() throws Exception {
		//select
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		this.dto.setLupd_cnt(1);
		jobScheduleDtos = alarmDao.select_job_sch(this.dto);
		//update
		this.dto.setDel_yn("Y");
		alarmDao.updJobSch(dto);
		this.dto.setLupd_cnt(jobScheduleDtos.get(0).getLupd_cnt()+1);
		alarmDao.updJobSch(this.dto);
		//srchDataChgYn
		int srchCntN = alarmDao.srchDataChgYn(jobScheduleDtos.get(0));
		assertEquals("■■■■■ 일정테이블 데이터변경여부 체크로직[TS001/srchDataChgYn] case:데이터변경시.....test failed",0,srchCntN);
		log.info("■■■■■ 일정테이블 데이터변경여부 체크로직[TS001/srchDataChgYn] case:데이터변경시.....test success");
	}
	
	@Test
	public void test1003_srchJobChkYnN() throws Exception {
		int srchCnt = alarmDao.srchJobChkYn(this.dto);
		assertEquals("■■■■■ 일정테이블 일정확인여부 체크로직[TS001/srchJobChkYn] case:일정미확인시.....test failed",1,srchCnt);
		log.info("■■■■■ 일정테이블 일정확인여부 체크로직[TS001/srchJobChkYn] case:일정미확인시.....test success");
	}
	
	@Test
	public void test1004_srchJobChkYnY() throws Exception {
		this.dto.setChk_yn("Y");
		alarmDao.updJobSch(this.dto);
		int srchCnt = alarmDao.srchJobChkYn(this.dto);
		assertEquals("■■■■■ 일정테이블 일정확인여부 체크로직[TS001/srchJobChkYn] case:일정확인시.....test failed",0,srchCnt);
		log.info("■■■■■ 일정테이블 일정확인여부 체크로직[TS001/srchJobChkYn] case:일정확인시.....test success");
	}
	
	@Test
	public void test1101_srchJobSchLst() throws Exception {
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		Map<String, Object> daoMap = new HashMap<>();
		daoMap.put("dever_id", "testDever");
		daoMap.put("cust_id", "testCust");
		jobScheduleDtos = alarmDao.srchJobSchLst(daoMap);
		int srchCnt = jobScheduleDtos.size();
		assertNotEquals("■■■■■ 일정테이블 조회로직[TS001/srchJobSchLst] ..... failed",0,srchCnt);
		log.info("■■■■■ 일정테이블 조회로직[TS001/srchJobSchLst] ..... success");
	}
	
	@AfterTransaction
	public void afterTransaction() throws Exception {
		this.dto.setLupd_cnt(1);
		alarmDao.delJobSch(this.dto);
	}
}
