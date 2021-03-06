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
		
		if(Utlz.isNull	(req.getParameter("DEVER_ID"))) throw new AlarmException("개발자ID는 필수입력항목입니다.");
		if(Utlz.isNull	(req.getParameter("STDT"	))) throw new AlarmException("조회시작일자는 필수입력사항입니다.");
		if(Utlz.isNull	(req.getParameter("EDDT"	))) throw new AlarmException("조회종료일자는 필수입력사항입니다.");
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
		dto.setDever_id(req.getParameter("DEVER_ID"));
		dto.setCust_id(req.getParameter("CUST_ID"));
		String setTm = "00010101 " + req.getParameter("SET_TM").replace(":", "");
		dto.setSet_tm(setTm);
		dto.setJob_cd(req.getParameter("JOB_CD"));
		
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
		if(!Utlz.isNum(req.getParameter("SQNO"		))) throw new AlarmException("일련번호는 숫자여야 합니다.."	);
		if(!Utlz.isNum(req.getParameter("LUPD_CNT"	))) throw new AlarmException("최종수정수는 숫자여야 합니다.");
		
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
		dto.setDever_id		(DEVER_ID	);
		dto.setCust_id		(CUST_ID 	);
		dto.setSqno			(SQNO		);
		dto.setLupd_cnt		(LUPD_CNT	);
		dto.setSet_tm		(SET_TM		);
		dto.setJob_cd		(JOB_CD		);
		dto.setChk_yn		(CHK_YN		);
		dto.setLst_chk_ts	(LST_CHK_TS	);
		dto.setWk_itv_rule	(WK_ITV_RULE);
		dto.setSt_mth		(ST_MTH		);
		dto.setEd_mth		(ED_MTH		);
		dto.setDel_yn		(DEL_YN		);
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
	public void delJobSch(Model model) throws Exception {
		Map<String, Object> map = model.asMap(); 
		HttpServletRequest req = (HttpServletRequest) map.get("req");
		
		if(Utlz.isNull(req.getParameter("DEVER_ID"	))) throw new AlarmException("개발자ID는 필수입력항목입니다."	);	
		if(Utlz.isNull(req.getParameter("CUST_ID"	))) throw new AlarmException("고객ID는 필수입력항목입니다."	);
		if(!Utlz.isNum(req.getParameter("SQNO"		))) throw new AlarmException("일련번호는 숫자여야 합니다."		);
		
		JobSchInfoDto dto = new JobSchInfoDto();
		dto.setDever_id(req.getParameter("DEVER_ID"));
		dto.setCust_id(req.getParameter("CUST_ID"));
		int nSqno = Integer.valueOf(req.getParameter("SQNO"));
		dto.setSqno(nSqno);
		
		int delCnt = alarmDao.delJobSch(dto);
		
		model.addAttribute("jobSchInfoDto", dto);
		model.addAttribute("delCnt", delCnt);
	}
}
