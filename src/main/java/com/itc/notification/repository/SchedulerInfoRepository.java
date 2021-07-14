package com.itc.notification.repository;

import com.itc.notification.model.SchedulerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerInfoRepository extends JpaRepository<SchedulerInfo, Long> {
    SchedulerInfo findBySchedulerName(String schedulerName);

}
