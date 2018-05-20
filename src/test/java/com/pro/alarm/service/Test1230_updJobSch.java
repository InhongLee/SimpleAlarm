package com.pro.alarm.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ExtendedModelMap;

import com.pro.alarm.AlarmException;
import com.pro.alarm.Utlz;
import com.pro.alarm.dao.AlarmDao;
import com.pro.alarm.dto.JobSchInfoDto;

@RunWith(Parameterized.class)
public class Test1230_updJobSch {

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
			{null		, null		, null	, null	, null		, null		, null, null						, null		, null, null, null, 0, 0, 0},
			{"testDever", null		, null	, null	, null		, null		, null, null						, null		, null, null, null, 0, 0, 0},
			{"testDever", "testCust", null	, null	, null		, null		, null, null						, null		, null, null, null, 0, 0, 0},
			{"testDever", "testCust", "1"	, null	, null		, null		, null, null						, null		, null, null, null, 0, 0, 0},
			{"testDever", "testCust", "1"	, "1"	, null		, null		, null, null						, null		, null, null, null, 1, 1, 0},
			{"testDever", "testCust", "1"	, "1"	, "1200"	, null		, null, null						, null		, null, null, null, 1, 1, 1},
			{"testDever", "testCust", "1"	, "1"	, null		, "updJobCd", null, null						, null		, null, null, null, 1, 1, 1},
			{"testDever", "testCust", "1"	, "1"	, null		, null		, "Y" , "20180520 081050.000000"	, null		, null, null, null, 1, 0, 0},
			{"testDever", "testCust", "1"	, "1"	, null		, null		, null, null						, "1111000"	, null, null, null, 1, 1, 1},
			{"testDever", "testCust", "1"	, "1"	, null		, null		, null, null						, null		, "03", null, null, 1, 1, 1},
			{"testDever", "testCust", "1"	, "1"	, null		, null		, null, null						, null		, null, "10", null, 1, 1, 1},
			{"testDever", "testCust", "1"	, "1"	, null		, null		, null, null						, null		, null, null, "Y" , 0, 0, 0}
		});
	}
	
	@Parameter		public String 	DEVER_ID	;
	@Parameter(1)	public String 	CUST_ID		;
	@Parameter(2)	public String 	SQNO		;
	@Parameter(3)	public String 	LUPD_CNT	;
	@Parameter(4)	public String	SET_TM		;
	@Parameter(5)	public String	JOB_CD		;
	@Parameter(6)	public String	CHK_YN		;
	@Parameter(7)	public String	LST_CHK_TS	;
	@Parameter(8)	public String	WK_ITV_RULE	;
	@Parameter(9)	public String	ST_MTH		;
	@Parameter(10)	public String	ED_MTH		;
	@Parameter(11)	public String	DEL_YN		;
	@Parameter(12)	public int		dataChkCnt	;
	@Parameter(13)	public int		jobChkCnt	;
	@Parameter(14)	public int		updCnt		;
	
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
	}
	
	@Test
	public void test1230_updJobSch() throws Exception {
		MockitoAnnotations.initMocks(this);
		req.setParameter("DEVER_ID"		, DEVER_ID		);
		req.setParameter("CUST_ID"		, CUST_ID		);
		req.setParameter("SQNO"			, SQNO			);
		req.setParameter("LUPD_CNT"		, LUPD_CNT		);
		req.setParameter("SET_TM"		, SET_TM		);
		req.setParameter("JOB_CD"		, JOB_CD		);
		req.setParameter("CHK_YN"		, CHK_YN		);
		req.setParameter("LST_CHK_TS"	, LST_CHK_TS	);
		req.setParameter("WK_ITV_RULE"	, WK_ITV_RULE	);
		req.setParameter("ST_MTH"		, ST_MTH		);
		req.setParameter("ED_MTH"		, ED_MTH		);
		req.setParameter("DEL_YN"		, DEL_YN		);
		model.addAttribute("req", req);
		
		doReturn(updCnt).when(alarmDao).updJobSch(any(JobSchInfoDto.class));
		doReturn(dataChkCnt).when(alarmDao).srchDataChgYn(any(JobSchInfoDto.class));
		doReturn(jobChkCnt).when(alarmDao).srchJobChkYn(any(JobSchInfoDto.class));
		
		if(Utlz.isNull(DEVER_ID)) { 
			thrown.expect(AlarmException.class);
			thrown.expectMessage("개발자ID는 필수입력항목입니다.");
		} else if(Utlz.isNull(CUST_ID)) {
			thrown.expect(AlarmException.class);
			thrown.expectMessage("고객ID는 필수입력항목입니다.");
		} else if(!Utlz.isNum(SQNO)) {
			thrown.expect(AlarmException.class);
			thrown.expectMessage("일련번호는 숫자여야 합니다.");
		} else if(!Utlz.isNum(LUPD_CNT)) {
			thrown.expect(AlarmException.class);
			thrown.expectMessage("최종수정수는 숫자여야 합니다.");
		} else if(Utlz.isNull(SET_TM	)
				&&Utlz.isNull(JOB_CD	)
				&&Utlz.isNull(CHK_YN	)
				&&Utlz.isNull(LST_CHK_TS)
				&&Utlz.isNull(WK_ITV_RULE)
				&&Utlz.isNull(ST_MTH	)
				&&Utlz.isNull(ED_MTH	)
				&&Utlz.isNull(DEL_YN	)) {
			thrown.expect(AlarmException.class);
			thrown.expectMessage("수정할 정보가 없습니다.");
		} else if(dataChkCnt == 0) {
			thrown.expect(AlarmException.class);
			thrown.expectMessage("조회후 데이터가 변경되었습니다. 재 조회 하십시오.");
		} else if(jobChkCnt == 0) {
			thrown.expect(AlarmException.class);
			thrown.expectMessage("일정확인이 완료된 건은 변경/삭제가 불가능합니다.");
		}
		service.updJobSch(model);
		int updCnt = (int) model.get("updCnt");
		assertEquals("수정이 실패하였습니다.",1,updCnt);
	}
}
