package com.pro.alarm.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.pro.alarm.dto.JobSchInfoDto;

@Repository
public class AlarmDaoImpl implements AlarmDao {

	@Inject
	private SqlSession sqlSession;

	@Override
	public List<JobSchInfoDto> select_job_sch(JobSchInfoDto dto) {
		AlarmDao dao = sqlSession.getMapper(AlarmDao.class);
		return dao.select_job_sch(dto);
	}

	@Override
	public int ins_job_sch(JobSchInfoDto dto) {
		AlarmDao dao = sqlSession.getMapper(AlarmDao.class);
		return dao.ins_job_sch(dto);
	}
	
	@Override
	public int srchDataChgYn(JobSchInfoDto dto) {
		AlarmDao dao = sqlSession.getMapper(AlarmDao.class);
		return dao.srchDataChgYn(dto);
	}

	@Override
	public int srchJobChkYn(JobSchInfoDto dto) {
		AlarmDao dao = sqlSession.getMapper(AlarmDao.class);
		return dao.srchJobChkYn(dto);
	}

	@Override
	public List<JobSchInfoDto> srchJobSchLst(Map<String, Object> daoMap) {
		AlarmDao dao = sqlSession.getMapper(AlarmDao.class);
		return dao.srchJobSchLst(daoMap);
	}

	@Override
	public int insJobSch(JobSchInfoDto dto) {
		AlarmDao dao = sqlSession.getMapper(AlarmDao.class);
		return dao.insJobSch(dto);
	}

	@Override
	public int updJobSch(JobSchInfoDto dto) {
		AlarmDao dao = sqlSession.getMapper(AlarmDao.class);
		return dao.updJobSch(dto);
	}

	@Override
	public int delJobSch(JobSchInfoDto dto) {
		AlarmDao dao = sqlSession.getMapper(AlarmDao.class);
		return dao.delJobSch(dto);
	}



	
}
