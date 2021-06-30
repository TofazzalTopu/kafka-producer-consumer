package com.info.incentive.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "scheduler_info")
public class SchedulerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String schedulerName;
    private boolean isRunning;
}
