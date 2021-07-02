package com.itc.notification.service;

import com.itc.notification.model.SchedulerInfo;

public interface SchedulerInfoService {
    SchedulerInfo save(String schedulerName, boolean isRunning);
    SchedulerInfo update(String schedulerName, boolean isRunning);
    SchedulerInfo findById(String schedulerName);
    SchedulerInfo findBySchedulerName(String schedulerName);

}
