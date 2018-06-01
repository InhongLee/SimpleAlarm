<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<nav class="navbar navbar-default">
	
	<div class="container-fluid">
		
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-navbar-collape-f1200">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">SimpleAlarm</a>
		</div>
		
		<div class="collapse navbar-collapse" id="#bs-navbar-collape-f1200">
			<form class="navbar-form navbar-left" id="header_form_01" role="search">
				<div class="form-group">
					<input type="text" class="form-control" id="header_deverId" placeholder="개발자ID">
					<div class="input-group">
						<div class="input-group-addon"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></div>
						<input type="text" class="form-control" id="header_custId" placeholder="사용자ID">
					</div>
					<input type="date" class="form-control" id="header_stdt" placeholder="조회시작일">
					~
					<input type="date" class="form-control" id="header_eddt" placeholder="조회종료일">
					<input type="text" class="form-control" id="header_jobCd" placeholder="작업내용">
					<label class="checkbox-inline">
						<input type="checkbox" id="header_delayChk" value="Y">지연여부
					</label>
					<button type="button" class="btn btn-default" id="header_clear">화면지움</button>
					<button type="button" class="btn btn-primary" id="header_srch" >SEARCH</button>
				</div>
			</form>
		</div>
		
	</div>
</nav>
</body>

<script>
/****************************************************/
/*	def_valiable[변수선언]							*/
/****************************************************/
var header_deverId, header_custId, header_stdt, header_eddt, header_jobCd, header_delayChk;
var fv_deverId, fv_custId, fv_sqno, fv_setTm, fv_jobCd, fv_chkYn, fv_lstChkTs, fv_wkItvRule, fv_stMth, fv_edMth, fv_delYn, fv_fstPcTs, fv_pcTs, fv_lupdCnt;
var ds_headerSrch;
var ds_jobSch;
var arr_jobSchLst = new Array();
var ds_frame = {};
/****************************************************/
/*	header_onload[최초로드]							*/
/****************************************************/
$(document).ready(function() {
	
});
/****************************************************/
/*	frame_init[초기화]								*/
/****************************************************/
function fn_initFrame() {
	ds_headerSrch = {};
	ds_jobSch = {};
	arr_jobSchLst.length = 0;
}
/****************************************************/
/*	transaction[서버호출]							*/
/****************************************************/
function fn_headerSrch() {
	if(!fn_validation()) return false;
	fn_initFrame();
	var jqxhr =$.ajax({
		beforeSend: fn_setHeaderDs(),
		url: "MSS010_00S",
		method: "POST",
		contentType: "application/json;charset=UTF-8",
		data: JSON.stringify(ds_headerSrch),
		dataType: "json",
		success: function(data){
			fn_callback(data);
		}
	});
	return false;
}
/****************************************************/
/*	callback[서버반환]								*/
/****************************************************/
function fn_callback(data) {
	$(data).each(
		function() {
			fv_deverId 	= this.dever_id   ;
			fv_custId   = this.cust_id    ;
			fv_sqno     = this.sqno       ;
			fv_setTm    = this.set_tm     ;
			fv_jobCd    = this.job_cd     ;
			fv_chkYn    = this.chk_yn     ;
			fv_lstChkTs = this.lst_chk_ts ;
			fv_wkItvRule= this.wk_itv_rule;
			fv_stMth    = this.st_mth     ;
			fv_edMth    = this.ed_mth     ;
			fv_delYn    = this.del_yn     ;
			fv_fstPcTs  = this.fst_pc_ts  ;
			fv_pcTs     = this.pc_ts      ;
			fv_lupdCnt  = this.lupd_cnt   ;
			ds_jobSch    =  {dever_id	: fv_deverId	
							,cust_id    : fv_custId     
							,sqno       : fv_sqno       
							,set_tm     : fv_setTm      
							,job_cd     : fv_jobCd      
							,chk_yn     : fv_chkYn      
							,lst_chk_ts : fv_lstChkTs   
							,wk_itv_rule: fv_wkItvRule  
							,st_mth     : fv_stMth      
							,ed_mth     : fv_edMth      
							,del_yn     : fv_delYn      
							,fst_pc_ts  : fv_fstPcTs    
							,pc_ts      : fv_pcTs       
							,lupd_cnt   : fv_lupdCnt	};
			arr_jobSchLst.push(ds_jobSch);
			
		});
	fn_setList();
}
/****************************************************/
/*	action_event[액션이벤트]							*/
/****************************************************/
$('#header_srch').click(fn_headerSrch);	


/****************************************************/
/*	user_def_function[사용자정의함수]					*/
/****************************************************/
function fn_validation() {
	if($('#header_deverId'	).val() == '')	{alert("개발자ID는 필수입력항목입니다."	); return false;}
	if($('#header_stdt'		).val() == '') 	{alert("조회시작일은 필수입력항목입니다."	); return false;}
	if($('#header_eddt'		).val() == '') 	{alert("조회종료일은 필수입력항목입니다."	); return false;}
	/* if($('#header_custId'	).val() == '') 	{alert("사용자ID는 필수입력항목입니다."	); return false;}
	if($('#header_jobCd'	).val() == '') 	{alert("작업내용은 필수입력항목입니다."	); return false;} */
	return true;
}

function fn_setHeaderDs() {
	header_deverId	= $('#header_deverId'	).val();
	header_custId   = $('#header_custId'	).val();
	header_stdt     = $('#header_stdt'		).val();
	header_eddt     = $('#header_eddt'		).val();
	header_jobCd    = $('#header_jobCd'		).val();
	header_delayChk = $('#header_delayChk'	).prop("checked") ? "Y" : "N";
	ds_headerSrch = {"DEVER_ID"	: header_deverId
					,"CUST_ID"	: header_custId
					,"STDT"		: header_stdt
					,"EDDT"		: header_eddt
					,"JOB_CD"	: header_jobCd
					,"DELAY_CHK": header_delayChk};
}

function fn_setList() {
	var idx = 0;
	var strMsg = "";
		strMsg 	+= "<tr class='active'><th>사용자ID</th><th>설정시간</th><th>작업내용</th><th>확인여부</th></tr>";
	for(var i=0; i<arr_jobSchLst.length; i++){
		strMsg	+= "<tr id='MSS010_t01_tr'"+idx+">";
		strMsg	+= "<td>" + arr_jobSchLst[i]["cust_id"] + "</td>";
		strMsg	+= "<td>" + arr_jobSchLst[i]["set_tm"] + "</td>";
		strMsg	+= "<td>" + arr_jobSchLst[i]["job_cd"] + "</td>";
		strMsg	+= "<td>" + arr_jobSchLst[i]["chk_yn"] + "</td>";
		strMsg	+= "</tr>";
		
	}
	$('#MSS010_00M_t01').html(strMsg);
}
</script>
</html>