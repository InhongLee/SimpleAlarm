<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<nav class="navbar navbar-default">
	
	<div class="container-fluid" id="headerSrch">
		
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
					<input type="text" class="form-control datamap" id="header_deverId" data-key="DEVER_ID" placeholder="개발자ID">
					<div class="input-group">
						<div class="input-group-addon"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></div>
						<input type="text" class="form-control datamap" id="header_custId" data-key="CUST_ID" placeholder="사용자ID">
					</div>
					<input type="date" class="form-control datamap" id="header_stdt" data-key="STDT" placeholder="조회시작일" max="9999-12-31">
					~
					<input type="date" class="form-control datamap" id="header_eddt" data-key="EDDT" placeholder="조회종료일" max="9999-12-31">
					<input type="text" class="form-control datamap" id="header_jobCd" data-key="JOB_CD" placeholder="작업내용">
					<label class="checkbox-inline">
						<input type="checkbox" class="datamap" name="" id="header_delayChk" data-key="DELAY_CHK" data-type="checkbox" value="Y">지연여부
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
var ds_headerSrch = 
	{"DEVER_ID"	: ''
	,"CUST_ID"	: ''
	,"STDT"		: ''
	,"EDDT"		: ''
	,"JOB_CD"	: ''
	,"DELAY_CHK": ''};
var arr_jobSchLst = new Array();

/****************************************************/
/*	header_onload[최초로드]							*/
/****************************************************/
$(document).ready(function() {
	
});
/****************************************************/
/*	frame_init[초기화]								*/
/****************************************************/
function fn_initFrame() {
	gfn_clearDs(ds_headerSrch);
	arr_jobSchLst.length 	= 0;
}
/****************************************************/
/*	transaction[서버호출]							*/
/****************************************************/
function fn_srch() {
	if(!fn_validation()) return false;
	fn_initFrame();
	var jqxhr =$.ajax({
		beforeSend: fn_setDsSrch(),
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
		function(index,value) {
			arr_jobSchLst.push(gfn_cloneDs(value));		
		});
	fn_setList();
}
/****************************************************/
/*	action_event[액션이벤트]							*/
/****************************************************/
$('#header_srch').click(fn_srch);	
$('#header_clear').click(fn_initFrame);

/****************************************************/
/*	user_def_function[사용자정의함수]					*/
/****************************************************/
function fn_validation() {
	if($('#header_deverId'	).val() == '')	{alert("개발자ID는 필수입력항목입니다."	); return false;}
	if($('#header_stdt'		).val() == '') 	{alert("조회시작일은 필수입력항목입니다."	); return false;}
	if($('#header_eddt'		).val() == '') 	{alert("조회종료일은 필수입력항목입니다."	); return false;}
	if(gfn_diffDate($('#header_stdt').val(),$('#header_eddt').val()) > 31) {alert("검색기간은 1달 이내로 선택하세요."); return false;}
	if(gfn_diffDate($('#header_stdt').val(),$('#header_eddt').val()) < 0 ) {alert("종료일이 시작일보다 빠를수 없습니다."); return false;}
	return true;
}

function fn_setDsSrch() {
	ds_headerSrch = gfn_autoMapOut(ds_headerSrch, 'headerSrch');
}

function fn_setList() {
	var idx = 0;
	var strMsg = "";
		strMsg 	+= "<tr class='active'><th>사용자ID</th><th>설정시간</th><th>작업내용</th><th>확인여부</th></tr>";
	for(var i=0; i<arr_jobSchLst.length; i++){
		strMsg	+= "<tr id='MSS010_t01_tr'"+idx+">";
		strMsg	+= "<td>" + arr_jobSchLst[i]["cust_id"] + "</a></td>";
		strMsg	+= "<td>" + gfn_getTime(arr_jobSchLst[i]["set_tm"]) + "</td>";
		strMsg	+= "<td>" + arr_jobSchLst[i]["job_cd"] + "</td>";
		strMsg	+= "<td>" + arr_jobSchLst[i]["chk_yn"] + "</td>";
		strMsg	+= "</tr>";
	}
	$('#MSS010_00M_t01').html(strMsg);
}
</script>
</html>