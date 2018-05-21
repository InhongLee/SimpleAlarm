package com.pro.alarm.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
		{
			Test1200_srchJobSch.class
			,Test1210_srchJobSch_orderBy.class
			,Test1220_insJobSch.class
			,Test1230_updJobSch.class
			,Test1240_delJobSch.class
		})
public class TestAlarmService {

}
