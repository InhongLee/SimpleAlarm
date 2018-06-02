/**
 * Alarm 공통 method
 */

/**
 * 기간의 범위를 일자로 반환
 */
function gfn_diffDate(stdt, eddt) {
	var nStdt = Date.parse(stdt);
	var nEddt = Date.parse(eddt);
	var nDiff = nEddt - nStdt;
	var nDiffDate = nDiff/(1000*60*60*24);
	return nDiffDate;
}

/**
 * 입력받은 변수가 시간형식이면 input tag time형식에 맞춰 type변환하여 반환하고 시간형식이 아니면 "00:00:00"을 반환
 * @param time
 * @returns
 */
function gfn_getTime(time) {
	var isTime = true;
	var rslt = "00:00:00";
	var sTime = time.replace(/[^0-9]/g,"");
	if(sTime.length != 6) isTime = false;
	var nHours 	= sTime.substr(0,2);
	var nMinute = sTime.substr(2,2);
	var nSecond = sTime.substr(4,2);
	if(nHours  < 0 || nHours  > 23) isTime  = false;
	if(nMinute < 0 || nMinute > 59) isTime  = false;
	if(nSecond < 0 || nSecond > 59) isTiems = false;
	if(isTime == true) rslt = nHours+":"+nMinute+":"+nSecond;
	return rslt;
}
