<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>Home</title>
</head>
<body>
<div id="div_alarm"></div>
</body>
<script>
	/*
	apiUser : alarm API를 사용하는 웹개발자(관리자)
	appUser : alarm API를 사용하는 웹페이지의 고객(개인용 웹페이지일 경우 옵션)
	*/
	var tick	= 10000;
	var apiUser = '<c:out value="${apiUser}"/>';
	var appUser = '<c:out value="${appUser}"/>';
	var data 	= 	{
					apiUser	: 	apiUser,
					appUser	: 	appUser,
					tick	:	tick
					}

	var fn_tick = function() {
		$.ajax({
			url		: 	'http://localhost:8081/alarm/tick',
			data	: 	data,
			method	: 	'post',
			success	: 	function(response){
							$('#div_alarm').html(response);
							if($("#tick").html() == null){//서버에서 tick을 response하는지 check
								console.log('.....callback:unregistered user');
							} else {
								tick = $("#tick").html();//서버에서 tick을 response하면 재접속 간격을 변경
								setTimeout(fn_tick, tick);//서버 접속요청 함수를 재귀호출	
							}
						},
			error	:	function(response){
							console.log('.....callback:error');
							$('#div_alarm').html('Server trasaction failed.');
						}
		});
	}
	
	setTimeout(fn_tick, tick);
	
</script>

</html>
