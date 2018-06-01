package com.pro.alarm;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.pro.alarm.controller.TestAlarmController;
import com.pro.alarm.controller.TestMSS010_00S;
import com.pro.alarm.dao.TestAlarmDao;
import com.pro.alarm.service.TestAlarmService;

@RunWith(Suite.class)
@SuiteClasses(
		{
			DataSourceTest.class
			, MyBatisTest.class
			, TestUtlz.class
			, TestAlarmDao.class
			, TestAlarmService.class
			, TestAlarmController.class
		})
public class TestGroup {
	

}
