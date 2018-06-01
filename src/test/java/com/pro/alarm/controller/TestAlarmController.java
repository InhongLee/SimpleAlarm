package com.pro.alarm.controller;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	Test0100_tick.class,
	Test0110_home.class,
	Test0200_mngAlarmPost.class,
	Test0210_mngAlarm.class,
	TestMSS010_00S.class
})
public class TestAlarmController {
}
