<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	<form action="/alarm/mngAlarm" method="post">
		<label>개발자ID</label><input name="apiUser"	type="text">
		<label>사용자ID</label><input name="appUser"	type="text">
		<label>설정시간</label><input name="setTm"		type="time">
		<label>알람내용</label><input name="jobCd"		type="text">
		<fieldset>
			<legend>요일 선택[주간]</legend>
			<label><input name="weekBase"	type="checkbox"	value="mon">월</label>
			<label><input name="weekBase"	type="checkbox"	value="tue">화</label>
			<label><input name="weekBase"	type="checkbox"	value="wed">수</label>
			<label><input name="weekBase"	type="checkbox"	value="thu">목</label>
			<label><input name="weekBase"	type="checkbox"	value="fri">금</label>
			<label><input name="weekBase"	type="checkbox"	value="sat">토</label>
			<label><input name="weekBase"	type="checkbox"	value="sun">일</label>
		</fieldset>
		<fieldset>
			<legend>일자 선택[월간]</legend>
			<label><input name="monthBase"	type="date"	></label>
		</fieldset>
		<input type="submit" value="등록">
		<input type="reset" value="초기화">
		<input type="button" value="메뉴화면" onclick="fn_goHome()">
	</form>
</div>
</body>
<script>
	
</script>
</html>