package com.pro.alarm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.pro.alarm.service.TestInsJobSch;
import com.pro.alarm.service.TestSrchJobSch;

@RunWith(Suite.class)
@SuiteClasses(
		{
			TestSrchJobSch.class
			,TestInsJobSch.class
			//,TestUpdJobSch.class
		})
public class TestAlarmService {

}
