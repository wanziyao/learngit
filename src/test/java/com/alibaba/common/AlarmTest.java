package com.alibaba.common;

import org.junit.Test;

import com.alibaba.common.alarm.AlarmMessage;
import com.alibaba.common.alarm.MailAlarmService;

public class AlarmTest {

    @Test
    public void testEmail() {
        MailAlarmService alarm = new MailAlarmService();
        alarm.setEmailHost("smtp.163.com");
        alarm.setEmailUsername("test@163.com");
        alarm.setEmailPassword("test");
        alarm.start();

        AlarmMessage message = new AlarmMessage("this is ljh test; next line", "test@163.com");
        alarm.sendAlarm(message);
    }
}
