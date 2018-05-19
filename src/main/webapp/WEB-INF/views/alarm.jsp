<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border='1'>
	<c:if test="${empty apiUser }">
		Unregistered user.
	</c:if>
	<c:if test="${not empty apiUser }">
		<tr>
			<c:if test="${empty appUser }">
				<td>apiUser</td>
				<td><c:out value="${apiUser }"/></td>
			</c:if>
			<c:if test="${not empty appUser }">
				<td>appUser</td>
				<td><c:out value="${appUser }"/></td>
			</c:if>
		</tr>
		<tr>
			<td>taskId</td>
			<td><c:out value="${taskId }"/></td>
		</tr>
		<tr>
			<td>setTime</td>
			<td><c:out value="${setTime }"/></td>
		</tr>
		<tr>
			<td>tick</td>
			<td id="tick"><c:out value="${tick }"/></td>
		</tr>
	</c:if>
</table>
<c:if test="${not empty taskId }">
	<div id="check">
		<audio id="beep" src="resources/analog-watch-alarm_daniel-simion.ogg" autoplay loop></audio>
		<form id="div_alarm" action="">
			<input type="button" value="확인">
			<input type="button" value="다시알림">
		</form>
	</div>
</c:if>
</body>
<script type="text/javascript">
	var beep = document.getElementById('beep');
	$(document).ready(function(){
		$('#div_alarm input[value="확인"]').on('click', function(){
			beep.pause();
			beep.currentTime = 0;
			return false;
		});
	
	});
</script>
</html>