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
public class Test1220_insJobSch {
	
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
			{null		,null		,null	,null		},
			{"testDever",null		,null	,null		},
			{"testDever","testCust"	,null	,null		},
			{"testDever","testCust"	,"0800"	,null		},
			{"testDever","testCust"	,"0800"	,"testJob"	}
		});
	}
	
	@Parameter		public String DEVER_ID;
	@Parameter(1)	public String CUST_ID;
	@Parameter(2)	public String SET_TM;
	@Parameter(3)	public String JOB_CD;
	
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
	public void test1220_insJobSch() throws Exception {
		MockitoAnnotations.initMocks(this);
		req.setParameter("DEVER_ID"	, DEVER_ID	);
		req.setParameter("CUST_ID"	, CUST_ID	);
		req.setParameter("SET_TM"	, SET_TM	);
		req.setParameter("JOB_CD"	, JOB_CD	);
		model.addAttribute("req"	, req		);
		//기대정의
		doReturn(1).when(alarmDao).insJobSch(any(JobSchInfoDto.class));

		if(Utlz.isNull(DEVER_ID)) { 
			thrown.expect(AlarmException.class);
			thrown.expectMessage("개발자ID는 필수입력항목입니다.");
		} else if(Utlz.isNull(CUST_ID)) {
			thrown.expect(AlarmException.class);
			thrown.expectMessage("고객ID는 필수입력항목입니다.");
		} else if(Utlz.isNull(SET_TM)) {
			thrown.expect(AlarmException.class);
			thrown.expectMessage("설정시간은 필수입력항목입니다.");
		} else if(Utlz.isNull(JOB_CD)) {
			thrown.expect(AlarmException.class);
			thrown.expectMessage("작업코드는 필수입력항목입니다.");
		}
		service.insJobSch(model);
		int insCnt = (int) model.get("insCnt");
		assertEquals("입력에 실패하였습니다.",1,insCnt);
		
	}
	
}
