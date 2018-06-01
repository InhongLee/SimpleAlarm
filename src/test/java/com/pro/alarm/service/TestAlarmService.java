package com.pro.alarm.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.pro.alarm.controller.TestMSS010_00S;

@RunWith(Suite.class)
@SuiteClasses(
		{
			Test1200_srchJobSch.class
			,Test1210_srchJobSch_orderBy.class
			,Test1220_insJobSch.class
			,Test1230_updJobSch.class
			,Test1240_delJobSch.class
			,Test1210R_srchJobSch_orderBy.class
			,TestMSS010_00S.class
		})
public class TestAlarmService {

}
