
SELECT 	  DEVER_ID 	   												/*개발자ID*/
				, CUST_ID	  							 					/*고객ID  */
				, SQNO		   												/*일련번호  */
				, TO_CHAR(SET_TM,'HH24MISS') AS SET_TM	   					/*설정시간  */
				, JOB_CD	   												/*작업코드  */
				, CHK_YN	   												/*확인여부  */
				, TO_CHAR(LST_CHK_TS,'YYYYMMDD HH24MISS.FF6') AS LST_CHK_TS /*최종확인일시*/
				, WK_ITV_RULE                                               /*주간규칙*/
				, ST_MTH                                                    /*시작월*/
				, ED_MTH                                                    /*종료월*/
				, DEL_YN	   												/*삭제여부  */
				, TO_CHAR(FST_PC_TS,'YYYYMMDD HH24MISS.FF6') AS FST_PC_TS	/*최초처리일시*/
				, TO_CHAR(PC_TS,'YYYYMMDD HH24MISS.FF6') AS PC_TS			/*처리일시*/
				, LUPD_CNT	   												/*최종수정수 */
		FROM	TS001_JOB_SCH
		WHERE	DEVER_ID 	= 'testDever'
		 
		AND		CUST_ID 	= 'testCust'
		 
		 
		AND		PC_TS BETWEEN TO_TIMESTAMP(Regexp_Replace('2018-05-01','[^0-9]',''),'YYYYMMDD') AND TO_TIMESTAMP(Regexp_Replace('2018-05-25','[^0-9]',''),'YYYYMMDD')
        ;
        
    SELECT 	  DEVER_ID 	   												/*개발자ID*/
				, CUST_ID	  							 					/*고객ID  */
				, SQNO		   												/*일련번호  */
				, TO_CHAR(SET_TM,'HH24MISS') AS SET_TM	   					/*설정시간  */
				, JOB_CD	   												/*작업코드  */
				, CHK_YN	   												/*확인여부  */
				, TO_CHAR(LST_CHK_TS,'YYYYMMDD HH24MISS.FF6') AS LST_CHK_TS /*최종확인일시*/
				, WK_ITV_RULE                                               /*주간규칙*/
				, ST_MTH                                                    /*시작월*/
				, ED_MTH                                                    /*종료월*/
				, DEL_YN	   												/*삭제여부  */
				, TO_CHAR(FST_PC_TS,'YYYYMMDD HH24MISS.FF6') AS FST_PC_TS	/*최초처리일시*/
				, TO_CHAR(PC_TS,'YYYYMMDD HH24MISS.FF6') AS PC_TS			/*처리일시*/
				, LUPD_CNT	   												/*최종수정수 */
		FROM	TS001_JOB_SCH
		WHERE	DEVER_ID 	= 'testDever'
		 
		AND		CUST_ID 	= 'testCust'
		 
		 
		 
		AND		CHK_YN		= 'N'
		AND		TO_CHAR(SET_TM,'HH24MISS') < TO_CHAR(SYSTIMESTAMP,'HH24MISS')
		AND		DEL_YN 		= 'N';