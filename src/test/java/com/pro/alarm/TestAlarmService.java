package com.pro.alarm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.pro.alarm.service.Test1200_srchJobSch;
import com.pro.alarm.service.TestInsJobSch;
import com.pro.alarm.service.TestUpdJobSch;

@RunWith(Suite.class)
@SuiteClasses(
		{
			Test1200_srchJobSch.class
			,TestInsJobSch.class
			//,TestUpdJobSch.class
		})
public class TestAlarmService {

}
