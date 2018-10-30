<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	.bodycontainer 	{max-height: 600px; width: 100%; margin: 0; overflow-y: auto;}
	.table-scrollabe{margin: 0; padding: 0;}
</style>
<%@ include file = "setting.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container-fluid">
	<jsp:include page="header.jsp" flush="false"/>	
	<div class="row">
		
		<div class="col-md-6">
			<div class="panel panel-info">
				<div class="table-responsive">
					<div class="bodycontainer scrollable">
						<table class="table table-bordered table-hover table-scrollable" id="MSS010_00M_t01">
							<tr class="active">
								<th>사용자ID</th>
								<th>설정시간</th>
								<th>작업내용</th>
								<th>확인여부</th>
							</tr>
							<tr>
								<td colspan="4">조건을 선택후 SEARCH 버튼를 누르면 해당 작업목록이 표시됩니다.</td>
							</tr>
						</table>						
					</div>						
				</div>
			</div>
		</div>
		
		<div class="col-md-6">
			<div class="panel panel-info" id="MSS010_00M_d02_jobSchDetl">
				<table class="table table-bordered table-hover text-center">
					<tr class="active"><th class="col-md-5">변경 전</th><th class="col-md-2">변경 항목</th><th class="col-md-5">변경 후</th></tr>
					<tr>
						<td><input type="text" class="form-control datamap" data-key="cust_id" disabled></td>
						<td>사용자ID</td>
						<td><input type="text" class="form-control datamap" data-key="cust_id"></td>
					</tr>
					<tr>
						<td><input type="time" class="form-control datamap" data-key="set_tm" data-type="time" disabled></td>
						<td>설정시간</td>
						<td><input type="time" class="form-control datamap" data-key="set_tm" data-type="time"></td>
					</tr>
					<tr>
						<td><input type="text" class="form-control datamap" data-key="job_cd" disabled></td>
						<td>작업내용</td>
						<td><input type="text" class="form-control datamap" data-key="job_cd" ></td>
					</tr>
					<tr>
						<td><input type="text" class="form-control datamap" data-key="chk_yn" disabled></td>
						<td>확인여부</td>
						<td><input type="text" class="form-control datamap" data-key="chk_yn" ></td>
					</tr>
					<tr>
						<td><input type="text" class="form-control datamap" data-key="lst_chk_ts" disabled></td>
						<td>확인일시</td>
						<td><input type="text" class="form-control datamap" data-key="lst_chk_ts"></td>
					</tr>
					<tr class="datamap" data-key="wk_itv_rule" data-type="checkbox">
						<td><table class="table table-bordered">
								<tr><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th><th>일</th></tr>
								<tr>
									<td><input type="checkbox" id="MSS010_p02_preWkItvRule" value="day1" disabled></td>
									<td><input type="checkbox" id="MSS010_p02_preWkItvRule" value="day2" disabled></td>
									<td><input type="checkbox" id="MSS010_p02_preWkItvRule" value="day3" disabled></td>
									<td><input type="checkbox" id="MSS010_p02_preWkItvRule" value="day4" disabled></td>
									<td><input type="checkbox" id="MSS010_p02_preWkItvRule" value="day5" disabled></td>
									<td><input type="checkbox" id="MSS010_p02_preWkItvRule" value="day6" disabled></td>
									<td><input type="checkbox" id="MSS010_p02_preWkItvRule" value="day7" disabled></td>
								</tr>
							</table></td>
						<td>주간규칙</td>
						<td><table class="table table-bordered">
								<tr><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th><th>일</th></tr>
								<tr>
									<td><input type="checkbox" id="MSS010_p02_postWkItvRule" value="day1"></td>
									<td><input type="checkbox" id="MSS010_p02_postWkItvRule" value="day2"></td>
									<td><input type="checkbox" id="MSS010_p02_postWkItvRule" value="day3"></td>
									<td><input type="checkbox" id="MSS010_p02_postWkItvRule" value="day4"></td>
									<td><input type="checkbox" id="MSS010_p02_postWkItvRule" value="day5"></td>
									<td><input type="checkbox" id="MSS010_p02_postWkItvRule" value="day6"></td>
									<td><input type="checkbox" id="MSS010_p02_postWkItvRule" value="day7"></td>
								</tr>
							</table></td>
					</tr>
					<tr><td><div class="row">
								<div class="col-md-5"><input type="number" class="form-control datamap" data-key="st_mth" placeholder="시작 월" disabled></div>
								<div class="col-md-2">~</div>
								<div class="col-md-5"><input type="number" class="form-control datamap" data-key="ed_mth" placeholder="종료 월" disabled></div>
							</div></td>
						<td>월간규칙</td>
						<td><div class="row">
								<div class="col-md-5"><input type="number" class="form-control datamap" data-key="st_mth" placeholder="시작 월" min="1" max="12" step="1"></div>
								<div class="col-md-2">~</div>
								<div class="col-md-5"><input type="number" class="form-control datamap" data-key="ed_mth" placeholder="종료 월" min="1" max="12" step="1"></div>
							</div></td>
					</tr>
					<tr><td><input type="text" class="form-control datamap" data-key="fst_pc_ts" disabled></td><td>최초작성일시</td><td></td></tr>
					<tr><td><input type="text" class="form-control datamap" data-key="pc_ts" disabled></td><td>수정일시</td><td><button type="button" class="btn btn-primary">수정이력조회</button></td></tr>
				</table>
			</div>
			<div class="text-right">
				<button type="button" class="btn btn-primary" id="MSS010_00M_t01_btnNew">신규작업</button>
				<button type="button" class="btn btn-primary">작업등록</button>
				<button type="button" class="btn btn-primary">작업삭제</button>
			</div>
		</div>
	</div>
</div>
</body>
<script>
/****************************************************/
/*	def_valiable[변수선언]							*/
/****************************************************/
var fv_rowIdx = '';
/****************************************************/
/*	header_onload[최초로드]							*/
/****************************************************/

/****************************************************/
/*	frame_init[초기화]								*/
/****************************************************/

/****************************************************/
/*	transaction[서버호출]							*/
/****************************************************/

/****************************************************/
/*	callback[서버반환]								*/
/****************************************************/

/****************************************************/
/*	action_event[액션이벤트]							*/
/****************************************************/
$('table[id="MSS010_00M_t01"]').on('click','tr',function(event){
	var $index = $('tr').index(this)-1;
	if($index >= 0) {
		fv_rowIdx = $index;
		var dataSet = arr_jobSchLst[$index];
		var divId	= "MSS010_00M_d02_jobSchDetl";
		gfn_autoMapping(dataSet,divId);
	}
});
/****************************************************/
/*	user_def_function[사용자정의함수]					*/
/****************************************************/
/**
 * 입력받는 7자리 2진수를 주간규칙 테이블의 체크박스에 setting하는 함수
 * @param prepost [pre:변경전, post:변경후, all:변경전후]
 * @param rule [1111111] > 월화수목금토일
 * @return rslt [false:세팅실패, true:세팅성공]
 */
function fn_set_wk_itv_rule(rule,prepost){
	 var rslt = true;
	 //세팅위치 확인
	if(prepost == 'all' || prepost == null) {
		var rslt1 = fn_set_wk_itv_rule(rule,'pre');
		var rslt2 = fn_set_wk_itv_rule(rule,'post');
		rslt = (rslt1 && rslt2) ? true : false;
	} else if(prepost != 'pre' && prepost != 'post') {
		rslt = false;
	}
	//7자리 2진수로 된 문자열 체크
	var nRule = rule.replace(/[^0-1]/g,""); 
	if(nRule.length != 7) rslt = false;
	//순환문을 돌면서 월~일 체크박스에 입력받은 문자열의 이진수를 하나씩 set [0:unchecked,1:checked]
	if(rslt == true && prepost != 'all') {
		for(var i=1; i <= nRule.length; i++) {
			var checkId = "#MSS010_p02_"+prepost+"WkItvRule";
			var check = nRule.substr(i-1,1) == 1 ? true : false;
			$(checkId+"[value='day"+i+"']").prop("checked",check);
		}
	}
	return rslt;
 }

</script>
</html>