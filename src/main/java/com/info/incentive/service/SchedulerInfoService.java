package com.info.incentive.service;

import com.info.incentive.model.SchedulerInfo;

public interface SchedulerInfoService {
    SchedulerInfo save(String schedulerName, boolean isRunning);
    SchedulerInfo update(String schedulerName, boolean isRunning);
    SchedulerInfo findById(String schedulerName);
    SchedulerInfo findBySchedulerName(String schedulerName);

}
