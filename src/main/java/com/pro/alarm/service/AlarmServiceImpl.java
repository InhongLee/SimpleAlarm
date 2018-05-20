package com.pro.alarm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.pro.alarm.AlarmException;
import com.pro.alarm.Utlz;
import com.pro.alarm.dao.AlarmDao;
import com.pro.alarm.dto.JobSchInfoDto;

@Service
public class AlarmServiceImpl extends Utlz implements AlarmService{

	
	@Inject
	private AlarmDao alarmDao;

	@Override
	public void srchJobSch(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		Map<String, Object> daoMap = new HashMap<>();
		
		//입력값 검증
		//1.DEVER_ID 필수입력
		String deverId = req.getParameter("DEVER_ID");
		if(Utlz.isNull(deverId)) {
			throw new AlarmException("개발자ID는 필수입력항목입니다.");
		}
		//2.STDT~EDDT가 한달이내의 값 체크
		if(Utlz.isNull(req.getParameter("STDT"))) throw new AlarmException("조회시작일자는 필수입력사항입니다.");
		if(Utlz.isNull(req.getParameter("EDDT"))) throw new AlarmException("조회종료일자는 필수입력사항입니다.");
		if(Utlz.diffDate(req.getParameter("STDT"), req.getParameter("EDDT")) > 31) {
			throw new AlarmException("조회기간는 한달이내로 선택하셔야 합니다.");
		}
		
		//초기화
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		//조회조건 set
		daoMap.put("DEVER_ID"	, req.getParameter("DEVER_ID"	));
		daoMap.put("STDT"		, req.getParameter("STDT"		));
		daoMap.put("EDDT"		, req.getParameter("EDDT"		));
		if(!Utlz.isNull(req.getParameter("CUST_ID"		))) daoMap.put("CUST_ID"		, req.getParameter("CUST_ID"		));
		if(!Utlz.isNull(req.getParameter("JOB_CD"		))) daoMap.put("JOB_CD"			, req.getParameter("JOB_CD"			));
		if(!Utlz.isNull(req.getParameter("DELAY_CHK"	))) {
			daoMap.put("DELAY_CHK"		, req.getParameter("DELAY_CHK"		));
			if("Y".equals(req.getParameter("DELAY_CHK"))) {
				daoMap.remove("STDT");
				daoMap.remove("EDDT");
			}
		}
		if("ASC".equals(req.getParameter("ORDERBY_TYPE"))
		|| "DESC".equals(req.getParameter("ORDERBY_TYPE"))) {
			if("CUST_ID".equals(req.getParameter("ORDERBY_COL"))
			|| "SET_TM".equals(req.getParameter("ORDERBY_COL"))
			|| "JOB_CD".equals(req.getParameter("ORDERBY_COL"))
			|| "CHK_YN".equals(req.getParameter("ORDERBY_COL"))) {
				daoMap.put("ORDERBY_TYPE"	, req.getParameter("ORDERBY_TYPE"	));
				daoMap.put("ORDERBY_COL"	, req.getParameter("ORDERBY_COL"	));
			}
		}
		System.out.println(daoMap.toString());
		System.out.println(alarmDao.srchJobSchLst(daoMap).toString());
		//Execution			
		jobScheduleDtos = alarmDao.srchJobSchLst(daoMap);
		//조회결과 후처리
		model.addAttribute("daoMap", daoMap);
		model.addAttribute("jobScheduleDtos", jobScheduleDtos);
	}

 	@Override
	public void insJobSch(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		if(Utlz.isNull(req.getParameter("DEVER_ID"	))) throw new AlarmException("개발자ID는 필수입력항목입니다.");
		if(Utlz.isNull(req.getParameter("CUST_ID"	))) throw new AlarmException("고객ID는 필수입력항목입니다.");
		if(Utlz.isNull(req.getParameter("SET_TM"	))) throw new AlarmException("설정시간은 필수입력항목입니다.");
		if(Utlz.isNull(req.getParameter("JOB_CD"	))) throw new AlarmException("작업코드는 필수입력항목입니다.");
		
		JobSchInfoDto dto = new JobSchInfoDto();
		dto.setDEVER_ID(req.getParameter("DEVER_ID"));
		dto.setCUST_ID(req.getParameter("CUST_ID"));
		String setTm = "00010101 " + req.getParameter("SET_TM").replace(":", "");
		dto.setSET_TM(setTm);
		dto.setJOB_CD(req.getParameter("JOB_CD"));
		
		int insCnt = alarmDao.insJobSch(dto);
		
		model.addAttribute("jobSchInfoDto", dto);
		model.addAttribute("insCnt", insCnt);
	}

	@Override
	public void updJobSch(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		if(Utlz.isNull(req.getParameter("DEVER_ID"	))) throw new AlarmException("개발자ID는 필수입력항목입니다."	);
		if(Utlz.isNull(req.getParameter("CUST_ID"	))) throw new AlarmException("고객ID는 필수입력항목입니다."	);
		if(Utlz.isNull(req.getParameter("SQNO"		))) throw new AlarmException("일련번호는 필수입력항목입니다."	);
		if(Utlz.isNull(req.getParameter("LUPD_CNT"	))) throw new AlarmException("최종수정수는 필수입력항목입니다.");
		
		JobSchInfoDto dto 	= new JobSchInfoDto();
		String 	DEVER_ID	= req.getParameter("DEVER_ID");
		String 	CUST_ID 	= req.getParameter("CUST_ID");
		int 	SQNO		= Integer.valueOf(req.getParameter("SQNO"));
		int 	LUPD_CNT	= Integer.valueOf(req.getParameter("LUPD_CNT"));
		String	SET_TM		= !Utlz.isNull(req.getParameter("SET_TM"		)) ? req.getParameter("SET_TM"		) : null;
		String	JOB_CD		= !Utlz.isNull(req.getParameter("JOB_CD"		)) ? req.getParameter("JOB_CD"		) : null;
		String	CHK_YN		= !Utlz.isNull(req.getParameter("CHK_YN"		)) ? req.getParameter("CHK_YN"		) : null;
		String	LST_CHK_TS	= !Utlz.isNull(req.getParameter("LST_CHK_TS"	)) ? req.getParameter("LST_CHK_TS"	) : null;
		String	WK_ITV_RULE	= !Utlz.isNull(req.getParameter("WK_ITV_RULE"	)) ? req.getParameter("WK_ITV_RULE"	) : null;
		String	ST_MTH		= !Utlz.isNull(req.getParameter("ST_MTH"		)) ? req.getParameter("ST_MTH"		) : null;
		String	ED_MTH		= !Utlz.isNull(req.getParameter("ED_MTH"		)) ? req.getParameter("ED_MTH"		) : null;
		String	DEL_YN		= !Utlz.isNull(req.getParameter("DEL_YN"		)) ? req.getParameter("DEL_YN"		) : null;
		dto.setDEVER_ID		(DEVER_ID	);
		dto.setCUST_ID		(CUST_ID 	);
		dto.setSQNO			(SQNO		);
		dto.setLUPD_CNT		(LUPD_CNT	);
		dto.setSET_TM		(SET_TM		);
		dto.setJOB_CD		(JOB_CD		);
		dto.setCHK_YN		(CHK_YN		);
		dto.setLST_CHK_TS	(LST_CHK_TS	);
		dto.setWK_ITV_RULE	(WK_ITV_RULE);
		dto.setST_MTH		(ST_MTH		);
		dto.setED_MTH		(ED_MTH		);
		dto.setDEL_YN		(DEL_YN		);
		if(Utlz.isNull(SET_TM	)
				&&Utlz.isNull(JOB_CD	)
				&&Utlz.isNull(CHK_YN	)
				&&Utlz.isNull(LST_CHK_TS)
				&&Utlz.isNull(WK_ITV_RULE)
				&&Utlz.isNull(ST_MTH	)
				&&Utlz.isNull(ED_MTH	)
				&&Utlz.isNull(DEL_YN	)) {
			throw new AlarmException("수정할 정보가 없습니다.");
		}
		int dataChkCnt = alarmDao.srchDataChgYn(dto);
		if(dataChkCnt == 0) throw new AlarmException("조회후 데이터가 변경되었습니다. 재 조회 하십시오.");
		
		int jobChkCnt = alarmDao.srchJobChkYn(dto);
		if(jobChkCnt == 0) throw new AlarmException("일정확인이 완료된 건은 변경/삭제가 불가능합니다.");
		
		int updCnt = alarmDao.updJobSch(dto);
		
		model.addAttribute("jobSchInfoDto", dto);
		model.addAttribute("updCnt", updCnt);
	}
	
	@Override
	public void del_job_sch(Model model) throws Exception {
		Map<String, Object> map = model.asMap(); 
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		//input variable validation
		boolean validCheck = true;
		//condition.1	apiUser == null > return;
		if(Utlz.isNull(req.getParameter("apiUser"))) validCheck = false;
		//condition.2	apiUser is exist but appUser is not exist > return false;
		if(Utlz.isNull(req.getParameter("appUser"))) validCheck = false;
		//condition.3	apiUser, appUser are exist but sqno is not exist > return false;
		if(Utlz.isNull(req.getParameter("sqno"))) validCheck = false;
		//condition.4	sqno is not integer > return false;
		if(!Utlz.isNum(req.getParameter("sqno"))) validCheck = false;
		
		if(validCheck) {
			JobSchInfoDto dto = new JobSchInfoDto();
			dto.setDEVER_ID(req.getParameter("apiUser"));
			dto.setCUST_ID(req.getParameter("appUser"));
			int nSqno = Integer.valueOf(req.getParameter("sqno"));
			dto.setSQNO(nSqno);
			
			int delCnt = alarmDao.delJobSch(dto);
			
			model.addAttribute("delCnt", delCnt);
		}
	}
}
