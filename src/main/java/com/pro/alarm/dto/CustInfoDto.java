package com.pro.alarm.dto;

public class CustInfoDto {

	private String	DEVER_ID     ;//개발자ID
	private String	DEVER_PW     ;//개발자PW
	private String	LST_CONN_TS  ;//최종접속일시
	private String	LST_CONN_IP  ;//최종접속IP
	private String	DEL_YN       ;//삭제여부
	private String	FST_PC_TS    ;//최초처리일시
	private int		LUPD_CNT     ;//최종수정수
	
	public String getDEVER_ID() {
		return DEVER_ID;
	}
	public void setDEVER_ID(String dEVER_ID) {
		DEVER_ID = dEVER_ID;
	}
	public String getDEVER_PW() {
		return DEVER_PW;
	}
	public void setDEVER_PW(String dEVER_PW) {
		DEVER_PW = dEVER_PW;
	}
	public String getLST_CONN_TS() {
		return LST_CONN_TS;
	}
	public void setLST_CONN_TS(String lST_CONN_TS) {
		LST_CONN_TS = lST_CONN_TS;
	}
	public String getLST_CONN_IP() {
		return LST_CONN_IP;
	}
	public void setLST_CONN_IP(String lST_CONN_IP) {
		LST_CONN_IP = lST_CONN_IP;
	}
	public String getDEL_YN() {
		return DEL_YN;
	}
	public void setDEL_YN(String dEL_YN) {
		DEL_YN = dEL_YN;
	}
	public String getFST_PC_TS() {
		return FST_PC_TS;
	}
	public void setFST_PC_TS(String fST_PC_TS) {
		FST_PC_TS = fST_PC_TS;
	}
	public int getLUPD_CNT() {
		return LUPD_CNT;
	}
	public void setLUPD_CNT(int lUPD_CNT) {
		LUPD_CNT = lUPD_CNT;
	}

}
