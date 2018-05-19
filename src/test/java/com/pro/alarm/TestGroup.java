package com.pro.alarm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(
		{
			DataSourceTest.class
			, MyBatisTest.class
			, TestUtlz.class
			, TestAlarmDao.class
			, TestAlarmService.class
		})
public class TestGroup {
	

}
