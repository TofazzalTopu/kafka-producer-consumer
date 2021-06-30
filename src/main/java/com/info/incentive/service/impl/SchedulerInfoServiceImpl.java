package com.info.incentive.service.impl;

import com.info.incentive.model.SchedulerInfo;
import com.info.incentive.repository.SchedulerInfoRepository;
import com.info.incentive.service.SchedulerInfoService;
import org.springframework.stereotype.Service;

/**
 * @author Tofazzal
 */

@Service
public class SchedulerInfoServiceImpl implements SchedulerInfoService {

    private final SchedulerInfoRepository schedulerInfoRepository;

    public SchedulerInfoServiceImpl(SchedulerInfoRepository schedulerInfoRepository) {
        this.schedulerInfoRepository = schedulerInfoRepository;
    }

    @Override
    public SchedulerInfo save(String schedulerName, boolean isRunning) {
        SchedulerInfo info = new SchedulerInfo();
        info.setSchedulerName(schedulerName);
        info.setRunning(isRunning);
        return schedulerInfoRepository.save(info);
    }

    @Override
    public SchedulerInfo update(String schedulerName, boolean isRunning) {
        SchedulerInfo info = schedulerInfoRepository.findBySchedulerName(schedulerName);
        info.setRunning(isRunning);
        return schedulerInfoRepository.save(info);
    }

    @Override
    public SchedulerInfo findById(String schedulerName) {
        return null;
    }

    @Override
    public SchedulerInfo findBySchedulerName(String schedulerName) {
        return schedulerInfoRepository.findBySchedulerName(schedulerName);
    }
}
