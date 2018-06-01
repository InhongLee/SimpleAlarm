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