package com.pro.alarm.dto;

public class JobSchInfoDto {

	private String dever_id		;//개발자ID
	private String cust_id		;//고객ID
	private int	   sqno			;//일련번호
	private String set_tm		;//일정
	private String job_cd		;//작업코드
	private String chk_yn		;//확인여부
	private String lst_chk_ts	;//최종확인일시
	private String wk_itv_rule	;//주간규칙
	private String st_mth		;//시작월
	private String ed_mth		;//종료월
	private String del_yn		;//삭제여부
	private String pc_ts		;//처리일시
	private String fst_pc_ts	;//최초처리일시
	private int	   lupd_cnt		;//최종수정수
	
	public String getDever_id() {
		return dever_id;
	}
	public void setDever_id(String dever_id) {
		this.dever_id = dever_id;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public int getSqno() {
		return sqno;
	}
	public void setSqno(int sqno) {
		this.sqno = sqno;
	}
	public String getSet_tm() {
		return set_tm;
	}
	public void setSet_tm(String set_tm) {
		this.set_tm = set_tm;
	}
	public String getJob_cd() {
		return job_cd;
	}
	public void setJob_cd(String job_cd) {
		this.job_cd = job_cd;
	}
	public String getChk_yn() {
		return chk_yn;
	}
	public void setChk_yn(String chk_yn) {
		this.chk_yn = chk_yn;
	}
	public String getLst_chk_ts() {
		return lst_chk_ts;
	}
	public void setLst_chk_ts(String lst_chk_ts) {
		this.lst_chk_ts = lst_chk_ts;
	}
	public String getWk_itv_rule() {
		return wk_itv_rule;
	}
	public void setWk_itv_rule(String wk_itv_rule) {
		this.wk_itv_rule = wk_itv_rule;
	}
	public String getSt_mth() {
		return st_mth;
	}
	public void setSt_mth(String st_mth) {
		this.st_mth = st_mth;
	}
	public String getEd_mth() {
		return ed_mth;
	}
	public void setEd_mth(String ed_mth) {
		this.ed_mth = ed_mth;
	}
	public String getDel_yn() {
		return del_yn;
	}
	public void setDel_yn(String del_yn) {
		this.del_yn = del_yn;
	}
	public String getPc_ts() {
		return pc_ts;
	}
	public void setPc_ts(String pc_ts) {
		this.pc_ts = pc_ts;
	}
	public String getFst_pc_ts() {
		return fst_pc_ts;
	}
	public void setFst_pc_ts(String fst_pc_ts) {
		this.fst_pc_ts = fst_pc_ts;
	}
	public int getLupd_cnt() {
		return lupd_cnt;
	}
	public void setLupd_cnt(int lupd_cnt) {
		this.lupd_cnt = lupd_cnt;
	}

}
