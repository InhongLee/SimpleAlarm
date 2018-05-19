package com.pro.alarm.dao;

import java.util.List;
import java.util.Map;

import com.pro.alarm.dto.JobSchInfoDto;

public interface AlarmDao {

	//CRUD unit-test
	/**
	 * TABLE JOB_SCH BASIC SELECT SQL
	 * @param dto
	 * @return List<JobScheduleDto>
	 */
	public List<JobSchInfoDto> select_job_sch(JobSchInfoDto dto);
	
	public int ins_job_sch(JobSchInfoDto dto);
	
	/**
	 * 조회 이후 데이터 변경 여부 체크
	 * @param dto [JobSchInfoDto:일정]
	 * @return srchCnt [0:데이터 변경됨, 1:데이터 변경되지 않음]
	 */
	public int srchDataChgYn(JobSchInfoDto dto);
	
	/**
	 * 일정확인 여부 체크
	 * @param dto [JobSchInfoDto:일정]
	 * @return [0:CHK_YN='Y', 1:CHK_YN='N']
	 */
	public int srchJobChkYn(JobSchInfoDto dto);
	
	public List<JobSchInfoDto> srchJobSchLst(Map<String, Object> daoMap);
	
	public int insJobSch(JobSchInfoDto dto);
	
	public int updJobSch(JobSchInfoDto dto);
	
	public int delJobSch(JobSchInfoDto dto);
	
}
