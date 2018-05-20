package com.pro.alarm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.pro.alarm.service.Test1200_srchJobSch;
import com.pro.alarm.service.Test1210_srchJobSch_orderBy;
import com.pro.alarm.service.Test1220_insJobSch;
import com.pro.alarm.service.Test1230_updJobSch;
import com.pro.alarm.service.Test1240_delJobSch;

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
