package com.pro.alarm;

import static org.junit.Assert.assertEquals;

import org.apache.ibatis.type.TypeException;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUtlz {

	public static Logger log = LoggerFactory.getLogger(TestUtlz.class);

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void test0001_IsNull_strNull() throws Exception {
		String strNull = null;
		assertEquals(".....Utlz.isNull(strNull) is error", true, Utlz.isNull(strNull));
	}
	
	@Test
	public void test0002_IsNull_strSpace() throws Exception {
		String strSpace = " ";
		assertEquals(".....Utlz.isNull(strSpace) is error", true, Utlz.isNull(strSpace));
	}
	
	@Test
	public void test0003_IsNull_strNullText() throws Exception {
		String strNullText = "";
		assertEquals(".....Utlz.isNull(strNullText) is error" , true, Utlz.isNull(strNullText));
	}
	
	@Test
	public void test0004_IsNull_dateNull() throws Exception {
		String dateNull = "00010101";
		assertEquals(".....Utlz.isNull(dateNull) is error", true, Utlz.isNull(dateNull));
	}
	
	@Test
	public void test0005_IsNull_dttmNull() throws Exception {
		String dttmNull = "00010101 000000.000000";
		assertEquals(".....Utlz.isNull(dttmNull) is error", true, Utlz.isNull(dttmNull));
	}
	
	@Test
	public void test0006_isNull_WrongType() {
		Object obj = this.getClass();
		try {
			Utlz.isNull(obj);
		} catch (Exception e) {
			assertEquals(".....Utlz.isNull() has TypeException error.",TypeException.class, e.getClass());
		}
	}
	
	@Test
	public void test0007_isNull_notNull() throws Exception {
		String notNull = "notNull";
		assertEquals(".....Utlz.isNull(notNull) is error", false, Utlz.isNull(notNull));
	}
	
	@Test
	public void test0008_compareDate_date1Big() throws Exception {
		String date1 = "20180428";
		String date2 = "20180101";
		assertEquals(".....Utlz.compareDate(date1Big) is error", 1, Utlz.compareDate(date1, date2));
	}
	
	@Test
	public void test0009_compareDate_date2Big() throws Exception {
		String date1 = "20180101";
		String date2 = "20180428";
		assertEquals(".....Utlz.compareDate(date1Big) is error", -1, Utlz.compareDate(date1, date2));
	}
	
	@Test
	public void test0010_compareDate_dateEqul() throws Exception {
		String date1 = "20180101";
		String date2 = "20180101";
		assertEquals(".....Utlz.compareDate(date1Big) is error", 0, Utlz.compareDate(date1, date2));
	}
	
	@Test
	public void test0011_compareDate_WrongType(){
		String date1 = "";
		String date2 = "";
		try {
			Utlz.compareDate(date1, date2);
		} catch (Exception e) {
			assertEquals(".....Utlz.compareDate() has TypeException error.",TypeException.class, e.getClass());
		}
	}
	
	@Test
	public void test0012_isNum_Number() throws Exception {
		String strNum = "12.345";
		assertEquals(".....Utlz.isNum(strNum)::strNum is number but check failed.", true, Utlz.isNum(strNum));
	}
	
	@Test
	public void test0013_isNum_notNumber() throws Exception {
		String strNum1 = "12axi231";
		String strNum2 = "12.5458.2";
		assertEquals(".....Utlz.isNum(strNum)::strNum is'nt number but check success.", false, Utlz.isNum(strNum1));
		assertEquals(".....Utlz.isNum(strNum)::strNum is'nt number but check success.", false, Utlz.isNum(strNum2));
	}
	
	@Test
	public void test0014_compareDttm_BigDttm1() throws Exception {
		String fDttm1 = "20180501 121001.000000";
		String fDttm2 = "20180501 153000.000000";
		assertEquals(".....Utlz.compareDttm(a<b):일시비교 오류", -1, Utlz.compareDttm(fDttm1, fDttm2));
		fDttm1 = "20180501 121001.000000";
		fDttm2 = "20180501 121001.000000";
		assertEquals(".....Utlz.compareDttm(a=b):일시비교 오류", 0, Utlz.compareDttm(fDttm1, fDttm2));
		fDttm1 = "20180501 153000.000000";
		fDttm2 = "20180501 121001.000000";
		assertEquals(".....Utlz.compareDttm(a>b):일시비교 오류", 1, Utlz.compareDttm(fDttm1, fDttm2));
	}
	
	@Test
	public void test0015_diffDate_BigB() throws Exception {
		String sStdt = "20180101";
		String sEddt = "20180201";
		int nDiffDate = Utlz.diffDate(sStdt, sEddt);
		assertEquals(".....Utlz.diffDate(a<b):일자차이 오류",31,nDiffDate);
		nDiffDate = Utlz.diffDate(sEddt, sStdt);
		assertEquals(".....Utlz.diffDate(a>b):일자차이 오류",-31,nDiffDate);
	}
	
	@Test
	public void test0016_isDate_success() throws Exception {
		String date = "20180501";
		boolean rslt = Utlz.isDate(date);
		assertEquals(".....Utlz.isDate():입력하신 값은 일자가 아닙니다.", true, rslt);
	}
	
	@Test
	public void test0017_isDate_null() throws Exception {
		String date = null;
		thrown.expect(AlarmException.class);
		thrown.expectMessage("일자가 입력되지 않았습니다.");
		Utlz.isDate(date);
	}
	
	@Test
	public void test0018_isDate_minErr() throws Exception {
		String date = "00010103";
		boolean rslt = Utlz.isDate(date);
		assertEquals(".....Utlz.isDate():최소값 오류체크 실패", false, rslt);
	}
	
	@Test
	public void test0019_isDate_maxErr() throws Exception {
		String date = "99991333";
		boolean rslt = Utlz.isDate(date);
		assertEquals(".....Utlz.isDate():최대값 오류체크 실패", false, rslt);
	}
	
	@Test
	public void test0018_calDate() throws Exception {
		String date = "20180513";
		int diff = -30;
		String rsltDate = Utlz.calDate(date, diff);
		assertEquals(".....Utlz.calDate():계산일자 오류", "20180413", rsltDate);
	}

}
