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
<script>
/****************************************************/
/*	def_valiable[변수선언]							*/
/****************************************************/
var selectRow = '';
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
$('table[id="MSS010_00M_t01"] tr').click(function(event){
	var $tr = $(this);
	var $td = $tr.children();
});
/****************************************************/
/*	user_def_function[사용자정의함수]					*/
/****************************************************/
</script>
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
			<div class="panel panel-info">
				<table class="table table-bordered table-hover text-center">
					<tr class="active"><th class="col-md-5">변경 전</th><th class="col-md-2">변경 항목</th><th class="col-md-5">변경 후</th></tr>
					<tr><td><input type="text" class="form-control" id="MSS010_p02_preCustId" 	disabled></td><td>사용자ID</td><td><input type="text" class="form-control" id="MSS010_p02_posteCustId"></td></tr>
					<tr><td><input type="time" class="form-control" id="MSS010_p02_preSetTm" 	disabled></td><td>설정시간</td><td><input type="time" class="form-control" id="MSS010_p02_postSetTm"></td></tr>
					<tr><td><input type="text" class="form-control" id="MSS010_p02_preJobCd" 	disabled></td><td>작업내용</td><td><input type="text" class="form-control" id="MSS010_p02_postJobCd"></td></tr>
					<tr><td><input type="text" class="form-control" id="MSS010_p02_preChkYn" 	disabled></td><td>확인여부</td><td><input type="text" class="form-control" id="MSS010_p02_postChkYn"></td></tr>
					<tr><td><input type="text" class="form-control" id="MSS010_p02_preLstChkTs" disabled></td><td>확인일시</td><td><input type="text" class="form-control" id="MSS010_p02_postLstChkTs"></td></tr>
					<tr><td><table class="table table-bordered">
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
								<div class="col-md-5"><input type="number" class="form-control" id="MSS010_p02_preStMth" placeholder="시작 월" disabled></div>
								<div class="col-md-2">~</div>
								<div class="col-md-5"><input type="number" class="form-control" id="MSS010_p02_preEdMth" placeholder="종료 월" disabled></div>
							</div></td>
						<td>월간규칙</td>
						<td><div class="row">
								<div class="col-md-5"><input type="number" class="form-control" id="MSS010_p02_postStMth" placeholder="시작 월" min="1" max="12" step="1"></div>
								<div class="col-md-2">~</div>
								<div class="col-md-5"><input type="number" class="form-control" id="MSS010_p02_postEdMth" placeholder="종료 월" min="1" max="12" step="1"></div>
							</div></td>
					</tr>
					<tr><td><input type="text" class="form-control" id="MSS010_p02_FstPcTs" disabled></td><td>최초작성일시</td><td><input type="text" class="form-control" id="MSS010_p02_FstPcTs" ></td></tr>
					<tr><td><input type="text" class="form-control" id="MSS010_p02_PcTs" disabled></td><td>수정일시</td><td><button type="button" class="btn btn-primary">수정이력조회</button></td></tr>
				</table>
			</div>
			<div class="text-right">
				<button type="button" class="btn btn-primary">신규작업</button>
				<button type="button" class="btn btn-primary">작업등록</button>
				<button type="button" class="btn btn-primary">작업삭제</button>
			</div>
		</div>
	</div>
</div>
</body>

</html>