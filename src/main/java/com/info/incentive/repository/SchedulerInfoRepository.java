package com.info.incentive.repository;

import com.info.incentive.model.SchedulerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerInfoRepository extends JpaRepository<SchedulerInfo, Long> {
    SchedulerInfo findBySchedulerName(String schedulerName);

}
