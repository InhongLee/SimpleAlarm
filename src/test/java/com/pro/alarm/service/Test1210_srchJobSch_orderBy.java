package com.pro.alarm.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.pro.alarm.dao.AlarmDao;
import com.pro.alarm.dto.JobSchInfoDto;
/**
 * 일정 테이블 정렬조건 unit test
 * @author amaco78
 *
 */
@RunWith(Parameterized.class)
public class Test1210_srchJobSch_orderBy {

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
			{null	,null		},
			{"ASC"	,"CUST_ID"	},
			{"ASC"	,"SET_TM"	},
			{"ASC"	,"JOB_CD"	},
			{"ASC"	,"CHK_YN"	},
			{"DESC"	,"CUST_ID"	},
			{"DESC"	,"SET_TM"	},
			{"DESC"	,"JOB_CD"	},
			{"DESC"	,"CHK_YN"	}
		});
	}
	
	@Parameter		public String orderByType;
	@Parameter(1)	public String orderByCol;
	
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
	
	@SuppressWarnings("unchecked")
	@Test
	public void test1205_srchJobSch_orderByCustId() throws Exception {
		MockitoAnnotations.initMocks(this);
		req.setParameter("ORDERBY_TYPE", orderByType);
		req.setParameter("ORDERBY_COL", orderByCol);
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
		assertEquals("정렬조건이 선택되었으나 반영에 실패하였습니다.", orderByType, daoMap.get("ORDERBY_TYPE"));
		assertEquals("정렬필드가 선택되었으나 반영에 실패하였습니다.", orderByCol, daoMap.get("ORDERBY_COL"));
	}

}
