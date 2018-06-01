package com.pro.alarm;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.type.TypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utlz {
	/**
	 * 공통함수 목록
	 * 
	 * isNull(Object)
	 * 		Object[String,Number,Data]가 null이거나 ''값일 경우 true를 반환
	 * isDate(String date)
	 * 		입력받은 문자열이 Date로 변환 가능한지 체크하여 가능하면 true 불가능하면 false를 반환
	 * compareDate(String a,String b)
	 * 		두 일자(String "20180412")를 비교하여 a>b 이면 1, a=b 이면 0, a<b 이면 -1을 반환
	 * compareDttm(String a,String b)
	 * 		두 일시(String "20180501 020201.000000"을 비교하여 a>b 이면 1, a=b 이면 0, a<b 이면 -1을 반환
	 * isNum(String)
	 * 		문자열이 숫자(정수, 실수)인지 아닌지 판별한다.
	 * diffDate(String a,String b)
	 * 		두 일자 사이의 차이값을 반환 a=20180101,b=20180110 이면 반환값은 9 (반대일 경우 음수로)
	 * calDate(String date, int diff)
	 * 		지정된 일자로 부터 diff 만큼 더한 일자를 String으로 반환
	 */
	public static Logger log = LoggerFactory.getLogger(Utlz.class);
	
	/**
	 * Object[String,Number,Data]가 null이거나 ''값일 경우 true를 반환
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static boolean isNull(Object obj) throws Exception {
		
		if (obj == null) return true;
		String type = obj.getClass().getTypeName();
		switch(type)
		{
		case "java.lang.String":
			String str = ((String)obj).trim();
			if("".equals(str)) return true;
			else if("00010101".equals(str)) return true;
			else if("00010101 000000.000000".equals(str)) return true;
			break;

		default:
			throw new TypeException("잘못된 타입을 매개변수로 사용하셨습니다. Object의 type을 다시 확인하십시오.");
		}
		return false;
	}

	public static boolean isDate(String date) throws Exception {
		if(Utlz.isNull(date)) throw new AlarmException("일자가 입력되지 않았습니다.");
		date = date.replaceAll("[^0-9]", "");
		boolean rslt = false;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date minDate = new Date(0L);
		String sMinDate = format.format(minDate);
		String sMaxDate = "99991231";
		if(Utlz.compareDate(date, sMinDate) > 0 && Utlz.compareDate(date, sMaxDate) < 0) rslt = true;
		return rslt;
	}
	
	/**
	 * 두 일자를 비교하여 다음값을 반환한다.
	 * date1 > date2 	then return 1
	 * date1 == date2 	then return 0
	 * date1 < date2 	then return -1
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception
	 */
	public static int compareDate(String date1, String date2) throws Exception {
		if(Utlz.isNull(date1) || Utlz.isNull(date2)) {
			throw new TypeException("잘못된 타입을 매개변수로 사용하셨습니다. Object의 type을 다시 확인하십시오.");
		}
		SimpleDateFormat parse = new SimpleDateFormat("yyyyMMdd");
		Date pDate1 = parse.parse(date1);
		Date pDate2 = parse.parse(date2);
		if(pDate1.compareTo(pDate2) > 0) return 1;
		else if (pDate1.compareTo(pDate2) == 0) return 0;
		else return -1;
	}

	/**
	 * 두 일시(String "20180501 020201.000000"을 비교하여 a>b 이면 1, a=b 이면 0, a<b 이면 -1을 반환
	 * @param timestamp1
	 * @param timestamp2
	 * @return
	 * @throws Exception
	 */
	public static int compareDttm(String timestamp1, String timestamp2) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd hhmmss.SSSSSS");
		Timestamp fDttm1 = new Timestamp(format.parse(timestamp1).getTime());
		Timestamp fDttm2 = new Timestamp(format.parse(timestamp2).getTime());
		if(fDttm1.compareTo(fDttm2) > 0) return 1;
		else if (fDttm1.compareTo(fDttm2) == 0) return 0;
		else return -1;
	}
	
	/**
	 * 문자열이 숫자(정수, 실수)인지 아닌지 판별한다.
	 * @param strNum
	 * @return true:숫자 false:문자열 or null
	 * @throws Exception
	 */
	public static boolean isNum(String strNum) throws Exception {
		char tempCh;
		int dotCount = 0; //실수일 경우 .의 개수를 체크하는 변수
		boolean result = true;
		
		if(Utlz.isNull(strNum)) return false;
		
		for(int i=0; i<strNum.length(); i++) {
			tempCh = strNum.charAt(i); //입력받은 문자열을 문자단위로 검사
			//ASCI 코드 값이 48~57사이면 0과 9사이의 문자이다.
			if((int)tempCh < 48 || (int)tempCh > 57) {
				//만약 0~9사이의 문자가 아닌 tempCh가 .도 아니거나
				//.의 개수가 이미 1개 이상이라면 그 문자열은 숫자가 아니다.
				if(tempCh != '.' || dotCount > 0) {
					result = false;
					break;
				} else {
					dotCount++;
				}
			}
		}
		
		return result;
	}

	/**
	 * 두 일자 사이의 차이값을 반환 a=20180101,b=20180110 이면 반환값은 9 (반대일 경우 음수로)
	 * @param sStdt
	 * @param sEddt
	 * @return nDiffDate
	 * @throws Exception
	 */
	public static int diffDate(String sStdt, String sEddt) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date dStdt = format.parse(sStdt);
		Date dEddt = format.parse(sEddt);
		long lStdt = dStdt.getTime();
		long lEddt = dEddt.getTime();
		long lDiff = lEddt - lStdt;
		int  nDiffDate = (int) (lDiff/(1000*60*60*24));
		return nDiffDate;
	}
	
	/**
	 * 지정된 일자로 부터 diff 만큼 더한 일자를 String으로 반환
	 * @param date
	 * @param diff
	 * @return
	 * @throws Exception
	 */
	public static String calDate(String date, int diff) throws Exception {
		String rsltDate = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date setDate = format.parse(date);
		long setTime = setDate.getTime();
		long getTime = setTime + (long)diff*(1000*60*60*24);
		Date getDate = new Date(getTime);
		rsltDate = format.format(getDate);
		return rsltDate;
	}
	
}
