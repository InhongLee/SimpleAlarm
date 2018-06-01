package com.pro.alarm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.pro.alarm.AlarmException;
import com.pro.alarm.Utlz;
import com.pro.alarm.dao.AlarmDao;
import com.pro.alarm.dto.JobSchInfoDto;

@Service
public class RestServiceImpl {

	private static final Logger log = LoggerFactory.getLogger(RestServiceImpl.class);
	
	@Inject
	private AlarmDao dao;
	
	@SuppressWarnings("unchecked")
	public void srchJobSch(Model model) throws Exception {
		Map<String, Object> map = model.asMap();
		Map<String, Object> req = (Map<String, Object>) map.get("req");
		Map<String, Object> daoMap = new HashMap<>();
		
		if(Utlz.isNull	(req.get("DEVER_ID"))) throw new AlarmException("개발자ID는 필수입력항목입니다.");
		if(!Utlz.isDate	(req.get("STDT"	).toString())) throw new AlarmException("조회시작일자는 필수입력사항입니다.");
		if(!Utlz.isDate	(req.get("EDDT"	).toString())) throw new AlarmException("조회종료일자는 필수입력사항입니다.");
		if(Utlz.diffDate(req.get("STDT").toString(), req.get("EDDT").toString()) > 31) {
			throw new AlarmException("조회기간는 한달이내로 선택하셔야 합니다.");
		}
		
		//초기화
		List<JobSchInfoDto> jobScheduleDtos = new ArrayList<>();
		//조회조건 set
		daoMap.put("dever_id"	, req.get("DEVER_ID"	));
		daoMap.put("stdt"		, req.get("STDT"		));
		daoMap.put("eddt"		, req.get("EDDT"		));
		if(!Utlz.isNull(req.get("CUST_ID"		))) daoMap.put("cust_id"		, req.get("CUST_ID"		));
		if(!Utlz.isNull(req.get("JOB_CD"		))) daoMap.put("job_cd"			, req.get("JOB_CD"			));
		if(!Utlz.isNull(req.get("DELAY_CHK"	))) {
			daoMap.put("delay_chk"		, req.get("DELAY_CHK"		));
			if("Y".equals(req.get("DELAY_CHK"))) {
				daoMap.remove("stdt");
				daoMap.remove("eddt");
			}
		}
		if("ASC".equals(req.get("ORDERBY_TYPE"))
		|| "DESC".equals(req.get("ORDERBY_TYPE"))) {
			if("CUST_ID".equals(req.get("ORDERBY_COL"))
			|| "SET_TM".equals(req.get("ORDERBY_COL"))
			|| "JOB_CD".equals(req.get("ORDERBY_COL"))
			|| "CHK_YN".equals(req.get("ORDERBY_COL"))) {
				daoMap.put("orderby_type"	, req.get("ORDERBY_TYPE"	));
				daoMap.put("orderby_col"	, req.get("ORDERBY_COL"	));
			}
		}
		//Execution			
		jobScheduleDtos = dao.srchJobSchLst(daoMap);
		//조회결과 후처리
		model.addAttribute("daoMap", daoMap);
		model.addAttribute("jobScheduleDtos", jobScheduleDtos);
	}
}
