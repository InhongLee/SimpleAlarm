/**
 * Alarm 공통 method
 * 
 * gfn_diffDate(stdt, eddt) 		: 시작일과 종료일 사이의 기간을 일자로 반환하는 함수
 * gfn_getTime(time) 				: 입력받은 변수가 시간이면 time형식을 변환하여 반환하는 함수
 * gfn_autoMapping(dataSet, divId) 	: JSON데이터를 선택한 div에 자동매핑하는 함수
 * gfn_clearDs(dataset)				: 선택된 JSON의 value를 초기화
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


function gfn_cloneDs(fromDs) {
	var toDs = new Array();
	for( var key in fromDs ) {
		var value = fromDs[key];
		eval("toDs." + key + " = value;");
	}
	return toDs;
}

/**
 * json을 div의 tag value로 automapping하는 함수
 * mapping이 필요한 tag의 class에 'datamap' 을 추가한다.
 * datamap 된 tag에 data-key 속성을 추가하여 json의 key를 대입한다.
 * 만약 후처리가 필요한 type이라면 data-type 속성을 추가한다.
 * @param dataSet
 * @param divId
 * @returns
 */
function gfn_autoMapping(dataSet, divId) {
	for( var key in dataSet ) {
		var value 		= dataSet[key];
		var selector 	= "div[id='" + divId + "'] .datamap[data-key='" + key + "']";
		var $type 		= $(selector).attr("data-type");
		switch($type) {
			case "checkbox"	: eval("fn_set_" + key + "(value);"); break;
			case "time"		: $(selector).val(gfn_getTime(value));break;
			default			: $(selector).val(value)			; break;
		}
	}
}

function gfn_autoMapOut(dataSet, divId) {
	for( var key in dataSet ) {
		var selector 	= "div[id='" + divId + "'] .datamap[data-key='" + key + "']";
		var $type		= $(selector).attr("data-type");
		var value		= "";
		switch($type) {
			case "checkbox":
				$(selector).each(function(){
					value += this.checked ? "1" : "0";
				});
				break;
			default:
				value = $(selector).val();
				break;
		}
		if(value != null) dataSet[key] = value;
	}
	return dataSet;
}

function gfn_clearDs(dataSet) {
	for( var key in dataSet ) {
		dataSet[key] = '';
	} 
}





