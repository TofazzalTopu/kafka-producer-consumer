package com.itc.notification.service.impl.scheduler;

import com.itc.notification.model.SchedulerInfo;
import com.itc.notification.repository.SchedulerInfoRepository;
import com.itc.notification.service.SchedulerInfoService;
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
